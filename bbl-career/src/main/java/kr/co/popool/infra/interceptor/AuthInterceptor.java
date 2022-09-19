package kr.co.popool.infra.interceptor;

import com.sun.istack.NotNull;
import kr.co.popool.bblcommon.jwt.JwtProviderCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProviderCommon jwtProviderCommon;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION)
                .replace("Bearer", "").trim();

        if(token==null){
            return true;
        }

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
