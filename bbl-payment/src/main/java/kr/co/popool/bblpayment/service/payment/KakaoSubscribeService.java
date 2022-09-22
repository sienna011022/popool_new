package kr.co.popool.bblpayment.service.payment;

import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblpayment.domain.dto.payment.KakaoSubscribeDTO;
import kr.co.popool.bblpayment.domain.entity.item.InventoryEntity;
import kr.co.popool.bblpayment.domain.entity.item.ItemMstEntity;
import kr.co.popool.bblpayment.domain.entity.item.SubscribeEntity;
import kr.co.popool.bblpayment.domain.entity.payment.KakaoPayLogEntity;
import kr.co.popool.bblpayment.repository.inventory.InventoryRepository;
import kr.co.popool.bblpayment.repository.item.ItemRepository;
import kr.co.popool.bblpayment.repository.payment.KakaoPayLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class KakaoSubscribeService {

    private final KakaoPayClient kakaoPayClient;
    private final KakaoPayLogRepository kakaoPayLogRepository;
    private final ItemRepository<ItemMstEntity> itemRepository;
    private final InventoryRepository inventoryRepository;

    private final String ADMIN_KEY = "KakaoAK c3c1fcb48b30dfb68e37449cc31dffa3";

    private final String CID = "TCSUBSCRIP";

    private final String APPROVAL_URL = "http://localhost:8080/payments/kakao/subscription/success";
    private final String FAIL_URL = "http://localhost:8080/payments/kakao/subscription/fail";
    private final String CANCEL_URL = "http://localhost:8080/payments/kakao/subscription/cancel";


    @Transactional
    public void requestSubscription(KakaoSubscribeDTO.FIRST_ORDER orderDTO) {

        ItemMstEntity orderItem = itemRepository.findById(Long.parseLong(orderDTO.getItem_id())).orElseThrow(() -> new NotFoundException("item"));

        KakaoPayLogEntity kakaoPayLog = KakaoPayLogEntity.builder()
                .memberId(Long.parseLong(orderDTO.getPartner_user_id()))
                .item(orderItem)
                .itemName(orderDTO.getItem_name())
                .totalAmount(Integer.parseInt(orderDTO.getTotal_amount()))
                .quantity(Integer.parseInt(orderDTO.getQuantity()))
                .build();

        KakaoPayLogEntity savedKakaoPayLog = kakaoPayLogRepository.save(kakaoPayLog);

        KakaoSubscribeDTO.FIRST_READY_REQUEST requestDTO = KakaoSubscribeDTO.FIRST_READY_REQUEST.builder()
                .cid(CID)
                .partner_user_id(String.valueOf(savedKakaoPayLog.getMemberId()))
                .partner_order_id(String.valueOf(savedKakaoPayLog.getId()))
                .item_name(savedKakaoPayLog.getItemName())
                .quantity(String.valueOf(savedKakaoPayLog.getQuantity()))
                .tax_free_amount(String.valueOf(savedKakaoPayLog.getTaxFreeAmount()))
                .total_amount(String.valueOf(savedKakaoPayLog.getTotalAmount()))
                .approval_url(APPROVAL_URL + "/" + savedKakaoPayLog.getId())
                .fail_url(FAIL_URL + "/" + savedKakaoPayLog.getId())
                .cancel_url(CANCEL_URL + "/" + savedKakaoPayLog.getId())
                .build();

        KakaoSubscribeDTO.FIRST_READY_RESPONSE responseDTO = kakaoPayClient.requestFirstSubscribeForReady(ADMIN_KEY, requestDTO);

        savedKakaoPayLog.setTid(responseDTO.getTid());
    }

    @Transactional
    public void successSubscription(Long kakaoPayLogId, String pgToken) throws Exception {

        KakaoPayLogEntity findKakaoPayLog = kakaoPayLogRepository.findById(kakaoPayLogId).orElseThrow(() -> new NotFoundException("KakaoPaymentLog"));

        KakaoSubscribeDTO.FIRST_APPROVAL_REQUEST requestDTO = KakaoSubscribeDTO.FIRST_APPROVAL_REQUEST.builder()
                .cid(CID)
                .tid(findKakaoPayLog.getTid())
                .partner_user_id(String.valueOf(findKakaoPayLog.getMemberId()))
                .partner_order_id(String.valueOf(findKakaoPayLog.getId()))
                .pg_token(pgToken)
                .build();

        KakaoSubscribeDTO.FIRST_APPROVAL_RESPONSE responseDTO = kakaoPayClient.requestFirstSubscribeForApproval(ADMIN_KEY, requestDTO);

        InventoryEntity memberInventory = inventoryRepository.findInventoryEntityByMemberId(findKakaoPayLog.getMemberId()).orElseThrow(() -> new NotFoundException("inventory"));

        memberInventory.doSubscription((SubscribeEntity) findKakaoPayLog.getItem(), responseDTO.getSid());

        findKakaoPayLog.setSid(responseDTO.getSid());
        findKakaoPayLog.changePaymentStatusToSuccess();
    }

}
