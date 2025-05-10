package com.opb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opb.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<User> {

}
