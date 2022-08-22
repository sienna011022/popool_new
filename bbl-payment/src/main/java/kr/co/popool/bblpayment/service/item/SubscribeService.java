package kr.co.popool.bblpayment.service.item;

import kr.co.popool.bblpayment.domain.dto.item.SubscribeDTO;

public interface SubscribeService {

    public void createSubscribe(SubscribeDTO.CREATE create) throws Exception;
    public void updateSubscribe(SubscribeDTO.UPDATE update) throws Exception;
    public SubscribeDTO.DETAIL readSubscribeDetail(Long subscribeId) throws Exception;
    public void deleteSubscribe(Long subscribeId) throws Exception;
}
