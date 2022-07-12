package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.infra.interceptor.CorporateThreadLocal;
import kr.co.popool.bblmember.repository.CorporateRepository;
import kr.co.popool.bblmember.repository.MemberMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CorporateServiceImpl implements CorporateService{

    private final MemberMstServiceImpl memberMstServiceImpl;

    private final CorporateRepository corporateRepository;
    private final MemberMstRepository memberMstRepository;

    /**
     * 기업 회원 정보 수정
     * @param corporateUpdate 변경할 회원 정보 객체
     */
    @Transactional
    @Override
    public void update(MemberMstDto.UPDATE corporateUpdate) {

        CorporateEntity corporateEntity = CorporateThreadLocal.get();

        MemberMstEntity memberMstEntity = corporateEntity.getMemberMstEntity();

        if(!memberMstServiceImpl.checkPhone(new Phone(corporateUpdate.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        if(!memberMstServiceImpl.checkEmail(corporateUpdate.getEmail())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_EMAIL);
        }

        memberMstEntity.updateMemberMst(corporateUpdate);
        corporateEntity.update(memberMstEntity);

        memberMstRepository.save(memberMstEntity);
        corporateRepository.save(corporateEntity);

    }
}
