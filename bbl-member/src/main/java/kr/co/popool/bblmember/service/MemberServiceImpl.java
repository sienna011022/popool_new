package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.MemberDto;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.repository.MemberMstRepository;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMstRepository memberMstRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMstServiceImpl memberMstService;

    /**
     * 일반 회원가입
     * @param create 회원가입하기 위한 회원의 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void signUp(MemberDto.CREATE create) {

        if(!memberMstService.checkIdentity(create.getIdentity())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }

        if(!create.getPassword().equals(create.getCheckPassword())){
            throw new BadRequestException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        if(!memberMstService.checkPhone(new Phone(create.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        //TODO : 회원 권한은 관리자가 아니라면 모두 ROLE_MEMBER으로 자동 설정, 기업 회원 가입이기 NORMAR_MEMBER 자동 설정.

        MemberMstEntity memberMstEntity = MemberMstEntity.builder()
                .identity(create.getIdentity())
                .password(passwordEncoder.encode(create.getPassword()))
                .name(create.getName())
                .birth(create.getBirth())
                .phone(new Phone(create.getPhone()))
                .gender(Gender.of(create.getGender()))
                .memberRank(MemberRank.of(create.getMemberRank()))
                .memberRole(MemberRole.of(create.getMemberRole()))
                .build();

        MemberEntity memberEntity = MemberEntity.builder()
                .memberMstEntity(memberMstEntity)
                .build();

        memberMstRepository.save(memberMstEntity);
        memberRepository.save(memberEntity);
    }

    @Override
    public void paymentAgreeUpdate() {
        MemberEntity memberEntity = MemberThreadLocal.get();

        if(memberEntity.getPaymentAgree_yn().equals("N")){
            memberEntity.agree();
        }
        if (memberEntity.getPaymentAgree_yn().equals("Y")){
            memberEntity.disagree();
        }

        memberEntity.updateUseMember(memberEntity.getId());
        memberRepository.save(memberEntity);
    }

}
