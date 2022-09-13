package kr.co.popool.bblpayment.service.item;

import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblpayment.domain.dto.item.SubscribeDTO;
import kr.co.popool.bblpayment.domain.entity.item.SubscribeEntity;
import kr.co.popool.bblpayment.repository.item.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SubscribeServiceImpl implements SubscribeService{

    private final SubscribeRepository subscribeRepository;

    @Override
    @Transactional
    public void createSubscribe(SubscribeDTO.CREATE createDTO) throws Exception {

        SubscribeEntity newSubscribe = SubscribeEntity.builder()
                .name(createDTO.getName())
                .price(createDTO.getPrice())
                .payDatePerMonth(LocalDate.parse(createDTO.getPayDatePerMonth()))
                .build();

        subscribeRepository.save(newSubscribe);
    }

    @Override
    @Transactional
    public void updateSubscribe(SubscribeDTO.UPDATE updateDTO) throws Exception {

        SubscribeEntity findSubscribe = subscribeRepository.findById(updateDTO.getSubscribeId()).orElseThrow(() -> new NotFoundException("Subscribe"));
        findSubscribe.update(updateDTO);
    }

    @Override
    public SubscribeDTO.DETAIL readSubscribeDetail(Long subscribeId) throws Exception {

        SubscribeEntity findSubscribe = subscribeRepository.findById(subscribeId).orElseThrow(() -> new NotFoundException("Subscribe"));

        return SubscribeDTO.DETAIL.builder()
                .subscribeId(findSubscribe.getId())
                .name(findSubscribe.getName())
                .price(findSubscribe.getPrice())
                .payDatePerMonth(findSubscribe.getPayDatePerMonth().toString())
                .build();
    }

    @Override
    @Transactional
    public void deleteSubscribe(Long subscribeId) throws Exception {
        SubscribeEntity findSubscribe = subscribeRepository.findById(subscribeId).orElseThrow(() -> new NotFoundException("Subscribe"));
        findSubscribe.deleted();
    }
}
