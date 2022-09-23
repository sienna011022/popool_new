package kr.co.popool.bblcommon.jwt;

import io.jsonwebtoken.Jwts;
import kr.co.popool.bblcommon.error.exception.UserDefineException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Slf4j
@Service
public class JwtProviderCommon {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    final String MEMBER_IDENTITY = "identity";
    final String MEMBER_ROLE = "member_role";

    /**
     * 시크릿 키를 Base64로 인코딩을 하는 메소드.
     */
    @PostConstruct
    protected void init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    /**
     * 키 변환을 위한 key 를 만드는 메서드
     *
     * @return secret key
     */
    private byte[] generateKey() {
        try {
            return SECRET_KEY.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UserDefineException("키 변환에 실패하였습니다. ", e.getMessage());
        }
    }

    /**
     * 토큰을 이용하여 사용자 아이디를 찾는 메서드
     *
     * @param token 토큰
     * @return 사용자의 아이디
     */
    public String findIdentityByToken(String token) {
        return (String) Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody()
                .get(MEMBER_IDENTITY);
    }

    /**
     * 토큰을 이용하여 사용자 권한을 찾는 메서드
     *
     * @param token 토큰
     * @return 사용자의 권한
     */
    public String findRoleByToken(String token) {
        return (String) Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody()
                .get(MEMBER_ROLE);
    }
}
