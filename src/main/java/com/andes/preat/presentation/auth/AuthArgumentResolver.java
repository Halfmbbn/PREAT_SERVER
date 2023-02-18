package com.andes.preat.presentation.auth;

import com.andes.preat.exception.invalidToken.NotFoundTokenFromHeader;
import com.andes.preat.service.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(VerifiedMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final String authorizationHeader = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            throw new NotFoundTokenFromHeader();
        }
        return jwtProvider.getPayload(authorizationHeader);
    }
}
