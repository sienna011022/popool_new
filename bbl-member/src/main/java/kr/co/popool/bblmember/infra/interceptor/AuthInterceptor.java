package kr.co.popool.bblmember.infra.interceptor;

import kr.co.popool.bblcommon.jwt.JwtProviderCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProviderCommon jwtProviderCommon;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler){
        Optional<String> isToken = Optional.of(request.getHeader(HttpHeaders.AUTHORIZATION)
                .replace("Bearer", "").trim());

        if(!isToken.isPresent()){
            return true;
        }

        String token = isToken.get();

        String identity = jwtProviderCommon.findIdentityByToken(token);
        MemberThreadLocal.set(identity);

        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler,
                           ModelAndView modelAndView){

        if(MemberThreadLocal.get()==null){
            return;
        }

        MemberThreadLocal.remove();
    }
}
