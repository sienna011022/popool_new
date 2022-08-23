package kr.co.popool.bblmember.infra.security.jwt;

import io.jsonwebtoken.*;
import kr.co.popool.bblcommon.error.exception.NotFoundException;
import kr.co.popool.bblcommon.error.exception.UnauthorizedException;
import kr.co.popool.bblcommon.error.exception.UserDefineException;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.enums.MemberRole;
import kr.co.popool.bblmember.infra.error.jwt.JwtTokenExpiredException;
import kr.co.popool.bblmember.infra.error.jwt.JwtTokenInvalidException;
import kr.co.popool.bblmember.infra.interceptor.MemberThreadLocal;
import kr.co.popool.bblmember.repository.MemberRepository;
import kr.co.popool.bblmember.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    final String MEMBER = "identity";
    private final long ACCESS_EXPIRE = 1000 * 60 * 30;              //30분
    private final long REFRESH_EXPIRE = 1000 * 60 * 60 * 24 * 14;   //2주

    public long getRefreshExpire() {
        return REFRESH_EXPIRE;
    }

    private final MemberRepository memberRepository;

    /**
     * 시크릿 키를 Base64로 인코딩을 하는 메소드.
     */
    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    /**
     * 사용자 정보를 통해 Cliams 객체를 생성하는 메소드
     * @param identity : 사용자 아이디
     * @param memberRole : 사용자 권한
     * @param name : 사용자 이름
     * @return 사용자 정보를 포함한 Claims 객체
     */
    private Claims generateClaims(String identity, MemberRole memberRole, String name){
        Claims claims = Jwts.claims();
        claims.put(MEMBER, identity);
        claims.put("member_role", memberRole);
        claims.put("name", name);

        return claims;
    }

    /**
     * 사용자 정보를 통해 AccessToken을 생성하는 메소드
     * @param identity : 사용자 아이디
     * @param memberRole : 사용자 권한
     * @param name : 사용자 이름
     * @return 사용자의 AccessToken
     */
    public String createAccessToken(String identity, MemberRole memberRole, String name){
        Date issueDate = new Date();    //토큰 발행 시각.
        Date expireDate = new Date();   //토큰 유효 시각.
        expireDate.setTime(issueDate.getTime() + ACCESS_EXPIRE);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(generateClaims(identity, memberRole, name))
                .setIssuedAt(issueDate)
                .setSubject("AccessToken")
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();
    }

    /**
     * 사용자 정보를 통해 RefreshToken 을 만드는 메서드
     * @param identity : 사용자 아이디
     * @param memberRole : 사용자 권한
     * @param name : 사용자 이름
     * @return 사용자의 RefreshToken
     */
    public String createRefreshToken(String identity, MemberRole memberRole, String name){
        Date issueDate = new Date();
        Date expireDate = new Date();
        expireDate.setTime(issueDate.getTime() + REFRESH_EXPIRE);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(generateClaims(identity, memberRole, name))
                .setIssuedAt(issueDate)
                .setSubject("RefreshToken")     //토큰 용도 다름.
                .setExpiration(expireDate)      //유효 시간 2주일.
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();
    }

    /**
     * RefreshToken 을 이용하여 AccessToken 을 만들어내는 메서드
     * @param refreshToken 사용자의 RefreshToken
     * @return 사용자의 새로운 AccessToken
     */
    public String createAccessToken(String refreshToken){
        MemberEntity memberEntity = findMemberByToken(refreshToken);

        return createAccessToken(memberEntity.getIdentity()
                , memberEntity.getMemberRole(), memberEntity.getName());
    }

    /**
     * Request의 Header에 있는 토큰을 추출하는 메소드
     * 평소 AccessToken을 담아서 주고 받다가, 만료 됐다는 예외 발생 시, 그때 Refresh만.
     * @param request : 사용자의 요청.
     * @return : AccessToken과 RefreshToken을 담은 객체를 Optional로 감싼 데이터.
     */
    public Optional<String> resolveToken(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("Authorization"));
    }

    /**
     * 키 변환을 위한 key 를 만드는 메서드
     * @return secret key
     */
    private byte[] generateKey(){
        try{
            return SECRET_KEY.getBytes("UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new UserDefineException("키 변환에 실패하였습니다. ", e.getMessage());
        }
    }

    /**
     * 토큰의 유효성을 판단하는 메소드
     * @param token : 토큰
     * @return 토큰이 만료되었는지에 대한 불리언 값
     * @exception ExpiredJwtException 토큰이 만료되었을 경우에 발생하는 예외
     */
    public boolean isUsable(String token){
        try {
            Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            log.error("Invalid JWT Signature");
            throw new JwtTokenInvalidException("Invalid JWT Signature");
        }catch (MalformedJwtException e){
            log.error("Invalid JWT token");
            throw new JwtTokenInvalidException("Invalid JWT Token");
        }catch (ExpiredJwtException e){
            log.error("Expired JWT token");
            throw new JwtTokenExpiredException();
        }catch (IllegalArgumentException e){
            log.error("Empty JWT Claims");
            throw new JwtTokenInvalidException("Empty JWT Claims");
        }
    }

    /**
     * 토큰을 이용하여 사용자 아이디를 찾는 메서드
     * @param token 토큰
     * @return 사용자의 아이디
     */
    public String findIdentityByToken(String token){
        return (String) Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody()
                .get(MEMBER);
    }

    /**
     * 토큰을 이용하여 사용자 권한을 찾는 메서드
     * @param token 토큰
     * @return 사용자의 권한
     */
    public String findRoleByToken(String token){
        return (String) Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody()
                .get("member_role");
    }

    /**
     * 토큰을 통해 MemberMstEntity 객체를 가져오는 메서드
     * @param token : 토큰
     * @return : jwt 토큰을 통해 찾은 MemberMstEntity 객체
     * @Exception UserNotFoundException : 해당 회원을 찾을 수 없는 경우 발생하는 예외
     */
    public MemberEntity findMemberByToken(String token){
        return memberRepository.findByIdentity(findIdentityByToken(token))
                .orElseThrow(() -> new NotFoundException("MemberMstEntity"));
    }


    private Set<? extends GrantedAuthority> getAuthorities(MemberRole role) {
        Set<GrantedAuthority> set = new HashSet<>();

        if(role.equals(MemberRole.ROLE_ADMIN)){
            set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        set.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return set;
    }

    /**
     * 토큰을 통해서 Authentication 객체를 만들어내는 메서드
     * @param token 토큰
     * @return 사용자 정보를 담은 UsernamePasswordAuthenticationToken 객체
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails =
                new User(findIdentityByToken(token),
                        "",
                        getAuthorities(MemberRole.of(findRoleByToken(token))));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
