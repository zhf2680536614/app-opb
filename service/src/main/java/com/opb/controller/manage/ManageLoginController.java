package com.opb.controller.manage;

import com.opb.dto.UserLoginDto;
import com.opb.result.Result;
import com.opb.service.ManageLoginService;
import com.opb.service.UserLoginService;
import com.opb.vo.ManageLoginVo;
import com.opb.vo.UserLoginVo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manage")
@Api(tags = "管理员登录相关接口")
@Slf4j
@RequiredArgsConstructor
public class ManageLoginController {
    private final ManageLoginService manageLoginService;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody UserLoginDto dto) {
        log.info("管理员登录{}", dto.toString());
        ManageLoginVo vo = manageLoginService.login(dto);
        return Result.success(vo);
    }
}
