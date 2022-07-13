package kr.co.popool.bblmember.service;

import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblcommon.error.exception.DuplicatedException;
import kr.co.popool.bblcommon.error.model.ErrorCode;
import kr.co.popool.bblmember.domain.dto.CorporateDto;
import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.repository.CorporateRepository;
import kr.co.popool.bblmember.repository.MemberMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CorporateServiceImpl implements CorporateService{

    private final MemberMstRepository memberMstRepository;
    private final CorporateRepository corporateRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMstServiceImpl memberMstService;

    /**
     * 기업 회원가입
     * @param create_corporate 회원가입하기 위한 회원의 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void corporateSignUp(CorporateDto.CREATE_CORPORATE create_corporate) {

        if(!memberMstService.checkIdentity(create_corporate.getIdentity())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }

        if(!create_corporate.getPassword().equals(create_corporate.getCheckPassword())){
            throw new BadRequestException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        if(!memberMstService.checkPhone(new Phone(create_corporate.getPhone()))){
            throw new DuplicatedException(ErrorCode.DUPLICATED_PHONE);
        }

        //TODO : 회원 권한은 관리자가 아니라면 모두 ROLE_MEMBER으로 자동 설정, 기업 회원 가입이기 CORPORATE_MEMBER 자동 설정.

        MemberMstEntity memberMstEntity = MemberMstEntity.builder()
                .identity(create_corporate.getIdentity())
                .password(passwordEncoder.encode(create_corporate.getPassword()))
                .name(create_corporate.getName())
                .birth(create_corporate.getBirth())
                .phone(new Phone(create_corporate.getPhone()))
                .gender(Gender.of(create_corporate.getGender()))
                .memberRank(MemberRank.of(create_corporate.getMemberRank()))
                .memberRole(MemberRole.of(create_corporate.getMemberRole()))
                .build();

        CorporateEntity corporateEntity = CorporateEntity.builder()
                .ceoName(create_corporate.getCeoName())
                .businessName(create_corporate.getBusinessName())
                .businessNumber(create_corporate.getBusinessNumber())
                .memberMstEntity(memberMstEntity)
                .build();

        memberMstRepository.save(memberMstEntity);
        corporateRepository.save(corporateEntity);
    }

}
