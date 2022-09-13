package kr.co.popool.bblpayment.service.payment;

import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblpayment.domain.dto.payment.KakaoPayDTO;
import kr.co.popool.bblpayment.domain.entity.item.*;
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
public class KakaoPayService {

    private final KakaoPayClient kakaoPayClient;
    private final KakaoPayLogRepository kakaoPayLogRepository;
    private final ItemRepository<ItemMstEntity> itemRepository;
    private final InventoryRepository inventoryRepository;

    private final String ADMIN_KEY = "KakaoAK c3c1fcb48b30dfb68e37449cc31dffa3";

    private final String CID = "TC0ONETIME";

    private final String APPROVAL_URL = "http://localhost:8080/payments/kakao/success";
    private final String FAIL_URL = "http://localhost:8080/payments/kakao/fail";
    private final String CANCEL_URL = "http://localhost:8080/payments/kakao/cancel";

    @Transactional
    public void requestPayment(KakaoPayDTO.ORDER orderDTO) throws Exception {

        ItemMstEntity orderItem = itemRepository.findById(Long.parseLong(orderDTO.getItem_id())).orElseThrow(() -> new NotFoundException("item"));

        KakaoPayLogEntity kakaoPayLog = KakaoPayLogEntity.builder()
                .memberId(Long.parseLong(orderDTO.getPartner_user_id()))
                .item(orderItem)
                .itemName(orderDTO.getItem_name())
                .totalAmount(Integer.parseInt(orderDTO.getTotal_amount()))
                .quantity(Integer.parseInt(orderDTO.getQuantity()))
                .build();

        KakaoPayLogEntity savedKakaoPayLog = kakaoPayLogRepository.save(kakaoPayLog);

        KakaoPayDTO.READY_REQUEST requestDTO = KakaoPayDTO.READY_REQUEST.builder()
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

        KakaoPayDTO.READY_RESPONSE responseDTO = kakaoPayClient.requestPayForReady(ADMIN_KEY, requestDTO);

        savedKakaoPayLog.setTid(responseDTO.getTid());
    }

    @Transactional
    public void successPayment(Long kakaoPayLogId, String pgToken) throws Exception {

        KakaoPayLogEntity findKakaoPayLog = kakaoPayLogRepository.findById(kakaoPayLogId).orElseThrow(() -> new NotFoundException("KakaoPaymentLog"));

        KakaoPayDTO.APPROVAL_REQUEST requestDTO = KakaoPayDTO.APPROVAL_REQUEST.builder()
                .cid(CID)
                .tid(findKakaoPayLog.getTid())
                .partner_user_id(String.valueOf(findKakaoPayLog.getMemberId()))
                .partner_order_id(String.valueOf(findKakaoPayLog.getId()))
                .pg_token(pgToken)
                .build();

        kakaoPayClient.requestPayForApproval(ADMIN_KEY, requestDTO);

        reflectToMemberInventory(findKakaoPayLog.getMemberId(), findKakaoPayLog.getItem());

        findKakaoPayLog.changePaymentStatusToSuccess();
    }

    private void reflectToMemberInventory(Long memberId, ItemMstEntity purchasedItem) {

        InventoryEntity buyerInventory = inventoryRepository.findInventoryEntityByMemberId(memberId).orElseThrow(() -> new NotFoundException("inventory"));

        if (purchasedItem instanceof CouponEntity) {
            buyerInventory.addCoupon(((CouponEntity) purchasedItem).getAmount());
            return;
        }

        if (purchasedItem instanceof PeriodCouponEntity) {
            buyerInventory.addPeriod(((PeriodCouponEntity) purchasedItem).getPeriod());
            return;
        }
    }
}
