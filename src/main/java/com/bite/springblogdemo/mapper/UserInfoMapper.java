package com.bite.springblogdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bite.springblogdemo.pojo.dataobject.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
