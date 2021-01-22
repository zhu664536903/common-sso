package com.gczhu.commonsso.controller;

import com.gczhu.commonsso.common.ResponseResult;
import com.gczhu.commonsso.dto.AppInfoDto;
import com.gczhu.commonsso.dto.UserLoginDto;
import com.gczhu.commonsso.model.AppInfo;
import com.gczhu.commonsso.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/sso")
@RestController
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("getGrantCode")
    public ResponseResult<String> getGrantCode(UserLoginDto userLoginDto){
        return userService.login(userLoginDto);
    }
    @RequestMapping("getToken")
    public ResponseResult<String> getToken(AppInfoDto appInfoDto){
        return userService.getToken(appInfoDto);
    }

}
