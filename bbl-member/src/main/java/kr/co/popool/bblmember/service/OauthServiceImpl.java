package kr.co.popool.bblmember.service;

import com.google.gson.Gson;
import kr.co.popool.bblcommon.error.exception.BadRequestException;
import kr.co.popool.bblmember.domain.dto.OauthDto;
import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.OauthRequest;
import kr.co.popool.bblmember.domain.shared.OauthRequestFactory;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import kr.co.popool.bblmember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final OauthRequestFactory oauthRequestFactory;
    private final RestTemplate restTemplate;
    private final Gson gson;
    private final MemberServiceImpl memberService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    @Override
    public void saveAdditionalMemberInfo(OauthDto.CREATE create) {
        memberService.checkSignUp(create);

        MemberEntity memberEntity = MemberEntity.of(create, passwordEncoder, null);

        memberEntity.saveOauth(create.getEmail(), create.getProvider(), create.getName());

        memberRepository.save(memberEntity);
    }

    @Override
    public void saveAdditionalCorporateInfo(OauthDto.CREATE_CORPORATE create) {
        memberService.checkSignUp(create.getCreate());

        CorporateEntity corporateEntity = CorporateEntity.of(create);

        MemberEntity memberEntity = MemberEntity.of(create.getCreate(), passwordEncoder, corporateEntity);

        memberEntity.saveOauth(create.getCreate().getEmail(),
                create.getCreate().getProvider(),
                create.getCreate().getName());

        memberRepository.save(memberEntity);
    }


    @Override
    @Transactional
    public OauthDto.TOKEN_READ login(OauthDto.LOGIN login) {
        OauthDto.TOKEN_INFO token_info = getAccessToken(login);
        OauthDto.PROFILE profile = getProfile(token_info.getAccessToken(), login.getProvider());

        Optional<MemberEntity> memberEntity = memberRepository.findByEmailAndProviderAndName(profile.getEmail(), login.getProvider(), profile.getName());

        if (memberEntity.isPresent()) {
            MemberEntity member = memberEntity.get();

            OauthDto.TOKEN_READ token_read = generateToken(member.getIdentity(), member.getName(), false);

            redisService.createData(member.getIdentity(), token_read.getRefreshToken(), jwtProvider.getRefreshExpire());
            memberRepository.save(member);

            return token_read;
        } else {
            return OauthDto.TOKEN_READ.builder()
                    .isFirst(true)
                    .build();
        }
    }

    private OauthDto.TOKEN_READ generateToken(String identity, String name, boolean isFirst) {
        String accessToken = jwtProvider.createAccessToken(identity, MemberRole.ROLE_MEMBER, name);
        String refreshToken = jwtProvider.createRefreshToken(identity, MemberRole.ROLE_MEMBER, name);

        return OauthDto.TOKEN_READ.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isFirst(isFirst)
                .build();
    }

    private HttpHeaders setHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }

    @Override
    public OauthDto.TOKEN_INFO getAccessToken(OauthDto.LOGIN login) {
        HttpHeaders httpHeaders = setHeaders();

        OauthRequest oauthRequest = oauthRequestFactory.getRequest(login.getCode(), login.getProvider());
        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(oauthRequest.getMap(), httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(oauthRequest.getUrl(), request, String.class);

        try {
            if (response.getStatusCode() == HttpStatus.OK) {
                return gson.fromJson(response.getBody(), OauthDto.TOKEN_INFO.class);
            }
        } catch (Exception e) {
            throw new BadRequestException("토큰을 가져올 수 없습니다.");
        }

        throw new BadRequestException("토큰을 가져올 수 없습니다.");
    }

    @Override
    public OauthDto.PROFILE getProfile(String accessToken, String provider) {
        HttpHeaders httpHeaders = setHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        String profileUrl = oauthRequestFactory.getUrlProfile(provider);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(profileUrl, request, String.class);

        try {
            if(response.getStatusCode() == HttpStatus.OK){
                return checkProfile(response, provider);
            }
        }catch (HttpClientErrorException | HttpServerErrorException e){
            throw new BadRequestException("프로필을 가져올 수 없습니다.");
        }
        throw new BadRequestException("프로필을 가져올 수 없습니다.");
    }

    @Override
    public OauthDto.PROFILE checkProfile(ResponseEntity<String> response, String provider) {
        if(provider.equals("kakao")){
            OauthDto.KAKAO kakao = gson.fromJson(response.getBody(), OauthDto.KAKAO.class);
            return new OauthDto.PROFILE(kakao.getAccount().getName(), kakao.getAccount().getEmail());
        }else if(provider.equals("google")){
            OauthDto.GOOGLE google = gson.fromJson(response.getBody(), OauthDto.GOOGLE.class);
            return new OauthDto.PROFILE(google.getName(), google.getEmail());
        }else if(provider.equals("naver")){
            OauthDto.NAVER naver = gson.fromJson(response.getBody(), OauthDto.NAVER.class);
            return new OauthDto.PROFILE(naver.getResponse().getName(), naver.getResponse().getEmail());
        }else {
            throw new BadRequestException("해당 소셜 로그인은 없습니다.");
        }
    }
}