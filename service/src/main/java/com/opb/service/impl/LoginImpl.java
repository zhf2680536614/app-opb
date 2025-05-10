package com.opb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opb.entity.User;
import com.opb.mapper.LoginMapper;
import com.opb.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl extends ServiceImpl<LoginMapper, User> implements LoginService {
}
