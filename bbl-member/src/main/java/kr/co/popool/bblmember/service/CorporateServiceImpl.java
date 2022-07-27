package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.BusinessLogicException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.repository.CorporateRepository;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorporateServiceImpl implements CorporateService{

    private final MemberRepository memberRepository;
    private final CorporateRepository corporateRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberServiceImpl memberService;

    /**
     * 기업 회원가입
     * @param create_corporate 회원가입하기 위한 회원의 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void corporateSignUp(CorporateDto.CREATE_CORPORATE create_corporate) {

        if(!memberService.checkIdentity(create_corporate.getIdentity())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }

        if(!create_corporate.getPassword().equals(create_corporate.getCheckPassword())){
            throw new BadRequestException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        if(!memberService.checkPhone(new Phone(create_corporate.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        //TODO : 회원 권한은 관리자가 아니라면 모두 ROLE_MEMBER으로 자동 설정, 기업 회원 가입이기 CORPORATE_MEMBER 자동 설정.
        CorporateEntity corporateEntity = CorporateEntity.builder()
                .ceoName(create_corporate.getCeoName())
                .businessName(create_corporate.getBusinessName())
                .businessNumber(create_corporate.getBusinessNumber())
                .build();

        MemberEntity memberEntity = MemberEntity.builder()
                .identity(create_corporate.getIdentity())
                .password(passwordEncoder.encode(create_corporate.getPassword()))
                .name(create_corporate.getName())
                .birth(create_corporate.getBirth())
                .phone(new Phone(create_corporate.getPhone()))
                .gender(Gender.of(create_corporate.getGender()))
                .memberRank(MemberRank.of(create_corporate.getMemberRank()))
                .memberRole(MemberRole.of(create_corporate.getMemberRole()))
                .corporateEntity(corporateEntity)
                .build();

        corporateRepository.save(corporateEntity);
        memberRepository.save(memberEntity);
    }

    /**
     * 기업 정보 수정
     * @param update_corporate : 변경할 데이터
     */
    @Override
    public void corporateUpdate(CorporateDto.UPDATE_CORPORATE update_corporate) {

        CorporateEntity corporateEntity = memberRepository.findByCorporateEntity(MemberThreadLocal.get().getCorporateEntity())
                .orElseThrow(() -> new BusinessLogicException(ErrorCode.WRONG_CORPORATE));

        corporateEntity.corporateUpdate(update_corporate);
        corporateRepository.save(corporateEntity);
    }

    /**
     * 기업 정보 조회
     * @return : 기업 정보
     * @Exception WRONG_CORPORATE: 기업 회원이 아닙니다.
     */
    @Override
    public CorporateDto.READ_CORPORATE getCorporate() {

        CorporateEntity corporateEntity = memberRepository.findByCorporateEntity(MemberThreadLocal.get().getCorporateEntity())
                .orElseThrow(() -> new BusinessLogicException(ErrorCode.WRONG_CORPORATE));

        CorporateDto.READ_CORPORATE read_corporate = CorporateDto.READ_CORPORATE.builder()
                .businessName(corporateEntity.getBusinessName())
                .businessNumber(corporateEntity.getBusinessNumber())
                .ceoName(corporateEntity.getCeoName())
                .build();

        return read_corporate;
    }
}
