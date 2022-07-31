package kr.co.popool.bblmember.infra.interceptor;

import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler){
        String token = request.getHeader("Authorization");

        if(token == null){
            return true;
        }

        MemberEntity memberEntity = jwtProvider.findMemberByToken(token);
        MemberThreadLocal.set(memberEntity);

        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response
            , @NotNull Object handler, ModelAndView modelAndView) {

        if(MemberThreadLocal.get() == null){
            return;
        }
        MemberThreadLocal.remove();
    }
}
