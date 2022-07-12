package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.repository.MemberMstRepository;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMstServiceImpl memberMstServiceImpl;

    private final MemberRepository memberRepository;
    private final MemberMstRepository memberMstRepository;

    /**
     * 회원 정보 수정
     * @param memberUpdate 변경할 회원 정보 객체
     */
    @Transactional
    @Override
    public void update(MemberMstDto.UPDATE memberUpdate) {

        MemberEntity memberEntity = MemberThreadLocal.get();

        MemberMstEntity memberMstEntity = memberEntity.getMemberMstEntity();

        if(!memberMstServiceImpl.checkPhone(new Phone(memberUpdate.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        if(!memberMstServiceImpl.checkEmail(memberUpdate.getEmail())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_EMAIL);
        }

        memberMstEntity.updateMemberMst(memberUpdate);
        memberEntity.update(memberMstEntity);

        memberMstRepository.save(memberMstEntity);
        memberRepository.save(memberEntity);
    }
}
