package com.opb.controller.user;

import com.opb.dto.UserLoginDto;
import com.opb.result.Result;
import com.opb.service.UserLoginService;
import com.opb.vo.UserLoginVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户登录相关接口")
@Slf4j
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody UserLoginDto dto) {
        log.info("用户登录{}", dto.toString());
        UserLoginVo vo = userLoginService.login(dto);
        return Result.success(vo);
    }

}
