package com.gczhu.commonsso.service;

import com.gczhu.commonsso.common.ResponseResult;
import com.gczhu.commonsso.dto.AppInfoDto;
import com.gczhu.commonsso.dto.UserLoginDto;
import com.gczhu.commonsso.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    ResponseResult<String> login(UserLoginDto userLoginDto);
    ResponseResult<String> getToken(AppInfoDto appInfoDto);
}
