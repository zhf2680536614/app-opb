package com.opb.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opb.constant.JwtClaimsConstant;
import com.opb.dto.UserLoginDto;
import com.opb.entity.User;
import com.opb.enumeration.UserStatusEnum;
import com.opb.exception.BaseException;
import com.opb.mapper.UserLoginMapper;
import com.opb.message.LoginMessage;
import com.opb.properties.JwtProperties;
import com.opb.service.UserLoginService;
import com.opb.utils.JwtUtil;
import com.opb.vo.UserLoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserLoginImpl extends ServiceImpl<UserLoginMapper, User> implements UserLoginService {

    private final JwtProperties jwtProperties;

    @Override
    public UserLoginVo login(UserLoginDto dto) {

        String username = dto.getUsername();
        String password = dto.getPassword();

        //判断用户是否存在
        User user = lambdaQuery()
                .eq(StrUtil.isNotBlank(username), User::getUsername, username)
                .one();

        if(ObjectUtil.isNull(user)) {
            throw new BaseException(LoginMessage.USERNAME_PASSWORD_ERROR);
        }

        if(!password.equals(user.getPassword())) {
            throw new BaseException(LoginMessage.USERNAME_PASSWORD_ERROR);
        }

        //判断账号是否冻结
        if(ObjectUtil.equals(user.getStatus(), UserStatusEnum.FAIL.getKey())) {
            throw new BaseException(LoginMessage.USER_FAIL);
        }

        //用户名和密码正确，生成token
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,user.getId());
        claims.put(JwtClaimsConstant.TYPE,user.getType());

        String token = JwtUtil.generateJwt(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVo userLoginVo = new UserLoginVo();

        userLoginVo.setUserId(user.getId());
        userLoginVo.setAuthentication(token);

        return userLoginVo;
    }
}
