package kr.co.popool.bblmember.infra.interceptor;

import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.infra.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler){
        String token = request.getHeader("token");

        if(token == null){
            return true;
        }

        Optional<MemberEntity> memberEntity = jwtProvider.findMemberByToken(token);
        Optional<CorporateEntity> corporateEntity = jwtProvider.findCorporateByToken(token);

        if(memberEntity.isPresent()) {
            MemberThreadLocal.set(memberEntity.get());
        }
        if(corporateEntity.isPresent()){
            CorporateThreadLocal.set(corporateEntity.get());
        }

        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response
            , @NotNull Object handler, ModelAndView modelAndView) {

        if(MemberThreadLocal.get() != null){
            MemberThreadLocal.remove();
        }
        if(CorporateThreadLocal.get() != null){
            CorporateThreadLocal.remove();
        }
    }
}
