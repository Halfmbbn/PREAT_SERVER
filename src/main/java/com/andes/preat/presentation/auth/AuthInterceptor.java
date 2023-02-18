package com.andes.preat.presentation.auth;

import com.andes.preat.exception.invalidToken.ExpiredToken;
import com.andes.preat.exception.invalidToken.NotRequiredToken;
import com.andes.preat.service.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod) || getLoginAnnotation(handler) == null) {
            return true;
        }
        if (hasAuthorization(request)) {
            validateAuthorization(request);
            return true;
        }
        validateTokenRequired(handler);
        return true;
    }
    private void validateTokenRequired(Object handler) {
        Login auth = getLoginAnnotation(handler);
        if (auth != null && auth.required()) {
            throw new NotRequiredToken();
        }
    }
    private void validateAuthorization(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!jwtProvider.isValidToken(authorizationHeader)) {
            throw new ExpiredToken();
        }
    }
    private boolean hasAuthorization(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION) != null;
    }
    private Login getLoginAnnotation(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return handlerMethod.getMethodAnnotation(Login.class);
    }
}
