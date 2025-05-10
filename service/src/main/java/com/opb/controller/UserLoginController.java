package com.opb.controller;

import com.opb.dto.UserLoginDto;
import com.opb.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/login")
@Api(tags = "用户登录相关接口")
@Slf4j
public class UserLoginController {

    @PostMapping
    public Result<Object> login(@RequestBody UserLoginDto dto) {
        log.info("用户登录{}", dto.toString());
        return Result.success("登陆成功");
    }

}
