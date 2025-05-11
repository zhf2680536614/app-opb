package com.opb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.opb.dto.UserLoginDto;
import com.opb.entity.User;
import com.opb.vo.ManageLoginVo;
import com.opb.vo.UserLoginVo;

public interface ManageLoginService extends IService<User> {
    ManageLoginVo login(UserLoginDto dto);
}
