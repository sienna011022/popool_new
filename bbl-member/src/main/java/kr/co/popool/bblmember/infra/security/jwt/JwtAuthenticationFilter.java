package kr.co.popool.bblmember.infra.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final Optional<String> token = jwtProvider.resolveToken(request);

        try {
            if(token.isPresent() && jwtProvider.isUsable(token.get())){
                Authentication authentication = jwtProvider.getAuthentication(token.get());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            handlerExceptionResolver.resolveException(request, response, null, e);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
