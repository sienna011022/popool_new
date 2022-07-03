package kr.co.popool.bblmember.service;

import kr.co.popool.bblmember.domain.dto.MemberMstDto;
import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import kr.co.popool.bblmember.domain.shared.enums.Gender;
import kr.co.popool.bblmember.domain.shared.enums.MemberRank;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.error.exception.BadRequestException;
import kr.co.popool.bblmember.error.exception.DuplicatedException;
import kr.co.popool.bblmember.error.model.ErrorCode;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import kr.co.popool.bblmember.repository.MemberMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMstServiceImpl implements MemberMstService{

    private final MemberMstRepository memberMstRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 회원가입
     * @param create 회원가입하기 위한 회원의 정보
     * @Exception DuplicatedException : 아이디가 이미 존재할 경우 회원가입을 진행할 수 없다는 예외.
     */
    @Override
    public void signUp(MemberMstDto.CREATE create) {

        if(!checkIdentity(create.getIdentity())){
            throw new DuplicatedException(ErrorCode.DUPLICATED_ID);
        }
        if(!create.getPassword().equals(create.getCheckPassword())){
            throw new BadRequestException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        //TODO : 전화번호 중복 확인 ?

        //TODO : 회원 가입 시, 회원 종류마다 다르게.

        //TODO : 회원 권한은 ROLE_MEMBER만 가능하도록? (ROLE_ADMIN은 개발자들만 가능해야 한다.)

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

        MemberMstEntity memberMstEntity2 = memberMstRepository.save(memberMstEntity);

        //TODO : 기업 회원일 시, 추가 정보 입력이 있어야 한다.
    }

    /**
     * 로그인
     * @param login : 로그인하기 위한 아이디 패스워드
     * @return : 회원의 AccessToken과 RefreshToken을 담은 객체
     * @Exception BadRequestException : 아이디 혹은 비밀번호가 틀렸을 경우 발생하는 예외.
     */
    @Transactional
    @Override
    public MemberMstDto.TOKEN login(MemberMstDto.LOGIN login) {

        MemberMstEntity memberMstEntity = memberMstRepository.findByIdentity(login.getIdentity())
                .orElseThrow(() -> new BadRequestException("아이디나 비밀번호를 다시 확인해주세요"));

        if(!passwordEncoder.matches(login.getPassword(), memberMstEntity.getPassword())){
            throw new BadRequestException("아이디나 비밀번호를 다시 확인해주세요");
        }

        String[] tokens = generateToken(memberMstEntity);
        memberMstEntity.updateRefreshToken(tokens[1]);

        return new MemberMstDto.TOKEN(tokens[0], tokens[1]);
    }

    /**
     * 아이디 중복 체크
     * @param identity : 중복 체크할 아이디
     * @return : 중복된 아이디가 있다면 false, 없다면 true
     */
    @Override
    public Boolean checkIdentity(String identity) {
        return !memberMstRepository.existsByIdentity(identity);
    }

    private String[] generateToken(MemberMstEntity memberMstEntity){
        String accessToken = jwtProvider.createAccessToken(memberMstEntity.getIdentity()
                , memberMstEntity.getMemberRole(), memberMstEntity.getName());
        String refreshToken = jwtProvider.createRefreshToken(memberMstEntity.getIdentity()
                , memberMstEntity.getMemberRole(), memberMstEntity.getName());

        return new String[]{accessToken, refreshToken};
    }
}
