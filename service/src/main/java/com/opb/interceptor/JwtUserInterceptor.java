package com.opb.interceptor;

import com.opb.constant.JwtClaimsConstant;
import com.opb.context.UserContext;
import com.opb.properties.JwtProperties;
import com.opb.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUserInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    private final JwtClaimsConstant jwtClaimsConstant;

    private final UserContext userContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断拦截到的是Controller的方法还是其他资源
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        //从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        try{
            log.info("用户jwt校验{}",token);
            Claims claims = JwtUtil.parseJwt(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(jwtClaimsConstant.USER_ID).toString());
            log.info("当前用户的id为{}",userId);
            userContext.setUserId(userId);
            return true;
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userContext.clearUserId();
    }
}
