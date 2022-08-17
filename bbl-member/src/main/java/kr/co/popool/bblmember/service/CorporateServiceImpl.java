package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BusinessLogicException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.dto.QueryDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.repository.CorporateRepository;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param createCorporate 회원가입하기 위한 회원의 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void corporateSignUp(CorporateDto.CREATE_CORPORATE createCorporate) {

        memberService.checkSignUp(createCorporate);

        //TODO : 회원 권한은 관리자가 아니라면 모두 ROLE_MEMBER으로 자동 설정, 기업 회원 가입이기 CORPORATE_MEMBER 자동 설정.

        CorporateEntity corporateEntity = CorporateEntity.of(createCorporate);

        MemberEntity memberEntity = MemberEntity.of(createCorporate.getCreate(), passwordEncoder, corporateEntity);

        corporateRepository.save(corporateEntity);
        memberRepository.save(memberEntity);
    }

    /**
     * 기업 정보 수정
     * @param updateCorporate : 변경할 데이터
     */
    @Override
    public void corporateUpdate(CorporateDto.UPDATE_CORPORATE updateCorporate) {

        MemberEntity memberEntity = MemberThreadLocal.get();
        CorporateEntity corporateEntity = corporateRepository.findById(memberEntity.getCorporateEntity().getId())
                .orElseThrow(() -> new BusinessLogicException(ErrorCode.WRONG_CORPORATE));

        corporateEntity.corporateUpdate(updateCorporate);
        corporateRepository.save(corporateEntity);
    }

    /**
     * 기업 정보 조회
     * @return : 기업 정보
     * @Exception WRONG_CORPORATE: 기업 회원이 아닙니다.
     */
    @Override
    public CorporateDto.READ_CORPORATE getCorporate() {

        QueryDto.CORPORATE_INFO corporateInfo = corporateRepository.findDtoByCorporateInfo(MemberThreadLocal.get())
                .orElseThrow(() -> new BusinessLogicException(ErrorCode.WRONG_CORPORATE));

        return CorporateDto.READ_CORPORATE.builder()
                .businessName(corporateInfo.getBusinessName())
                .businessNumber(corporateInfo.getBusinessNumber())
                .ceoName(corporateInfo.getCeoName())
                .build();
    }
}
