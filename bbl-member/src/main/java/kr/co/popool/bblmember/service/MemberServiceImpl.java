package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    /**
     * 회원 정보 수정
     * @param memberUpdate 변경할 회원 정보 객체
     */
    @Transactional
    @Override
    public void update(MemberMstDto.UPDATE memberUpdate) {

        MemberEntity memberEntity = MemberThreadLocal.get();

        //TODO : 전화번호 중복, 이메일 중복 체크

        MemberMstEntity memberMstEntity = memberEntity.getMemberMstEntity();
        memberMstEntity.updateMemberMst(memberUpdate);
        memberEntity.update(memberMstEntity);

        memberRepository.save(memberEntity);
    }
}
