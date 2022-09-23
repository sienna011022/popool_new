package kr.co.popool.bblpayment.service.payment.batch.subscription;

import kr.co.popool.bblpayment.domain.dto.payment.KakaoSubscribeDTO;
import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
import kr.co.popool.bblpayment.domain.entity.payment.KakaoPayLogEntity;
import kr.co.popool.bblpayment.repository.item.ItemRepository;
import kr.co.popool.bblpayment.repository.payment.KakaoPayLogRepository;
import kr.co.popool.bblpayment.service.payment.KakaoPayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
@Configuration
public class PaySubscriptionMembersJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final KakaoPayClient kakaoPayClient;
    private final KakaoPayLogRepository kakaoPayLogRepository;
    private final ItemRepository<ItemMstEntity> itemRepository;

    private final int chunkSize = 10;
    private final String ADMIN_KEY = "KakaoAK c3c1fcb48b30dfb68e37449cc31dffa3";

    @Bean
    public Job paySubscriptionMembersJob() {
        return jobBuilderFactory.get("paySubscriptionMembersJob")
                .start(paySubscriptionMembersStep())
                .build();
    }

    @Bean
    public Step paySubscriptionMembersStep() {
        return stepBuilderFactory.get("paySubscriptionMembersStep")
                .<KakaoSubscribeDTO.SUBSCRIPTION_PAYMENT_REQUEST, KakaoPayLogEntity> chunk(chunkSize)
                .reader(paySubscriptionMembersReader())
                .processor(paySubscriptionMembersProcessor())
                .build();
    }

    @Bean
    public JpaCursorItemReader<KakaoSubscribeDTO.SUBSCRIPTION_PAYMENT_REQUEST> paySubscriptionMembersReader() {
        return new JpaCursorItemReaderBuilder<KakaoSubscribeDTO.SUBSCRIPTION_PAYMENT_REQUEST>()
                .name("paySubscriptionMembersReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(
                        "select" +
                        " new kr.co.popool.bblpayment.domain.dto.payment.KakaoSubscribeDTO.SUBSCRIPTION_PAYMENT_REQUEST(i.sid, 'null', i.memberId, s.name, '1', s.price, '0', '0')" +
                        " from InventoryEntity i " +
                        " join fetch i.subscribe s" +
                        " where i.sid is not null"
                )
                .build();
    }

    @Bean
    public ItemProcessor<KakaoSubscribeDTO.SUBSCRIPTION_PAYMENT_REQUEST, KakaoPayLogEntity> paySubscriptionMembersProcessor() {
        return request -> {
            ItemMstEntity item = itemRepository.findById(Long.valueOf(request.getItemId())).orElseThrow(() -> new NotFoundException("item"));

            KakaoPayLogEntity kakaoPayLog = KakaoPayLogEntity.builder()
                    .quantity(Integer.valueOf(request.getQuantity()))
                    .totalAmount(Integer.valueOf(request.getTotal_amount()))
                    .itemName(request.getItem_name())
                    .memberId(Long.valueOf(request.getPartner_user_id()))
                    .item(item)
                    .build();

            KakaoPayLogEntity savedKakaoPayLog = kakaoPayLogRepository.save(kakaoPayLog);
            request.setPartner_order_id(String.valueOf(savedKakaoPayLog.getId()));

            KakaoSubscribeDTO.SUBSCRIPTION_PAYMENT_RESPONSE response = kakaoPayClient.requestSubscriptionPayment(ADMIN_KEY, request);

            savedKakaoPayLog.setSid(response.getSid());
            savedKakaoPayLog.changePaymentStatusToSuccess();

            return savedKakaoPayLog;
        };
    }
}
