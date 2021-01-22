package com.gczhu.commonsso.service.impl;

import com.gczhu.commonsso.common.ResponseResult;
import com.gczhu.commonsso.common.utils.RedisUtil;
import com.gczhu.commonsso.common.utils.TokenUtil;
import com.gczhu.commonsso.dto.AppInfoDto;
import com.gczhu.commonsso.dto.UserLoginDto;
import com.gczhu.commonsso.mapper.AppInfoMapper;
import com.gczhu.commonsso.mapper.UserMapper;
import com.gczhu.commonsso.model.AppInfo;
import com.gczhu.commonsso.model.AppInfoExample;
import com.gczhu.commonsso.model.User;
import com.gczhu.commonsso.model.UserExample;
import com.gczhu.commonsso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AppInfoMapper appInfoMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    TokenUtil tokenUtil;

    @Value("${login.grantcode.prefix}")
    String GRANTCODE_PREFIX;
    @Value("${login.grantcode.timeout}")
    long GRANTCODE_TIMEOUT;

    @Override
    public ResponseResult<String> login(UserLoginDto userLoginDto) {
        if (StringUtils.isEmpty(userLoginDto) || StringUtils.isEmpty(userLoginDto.getPassword())
                || StringUtils.isEmpty(userLoginDto.getUsername())
                || StringUtils.isEmpty(userLoginDto.getAppId())) {
            return ResponseResult.error("请输入正确参数！");
        }

        //校验用户名是否存在
        //校验密码是否正确
        User user = userMapper.selectOneByName(userLoginDto.getUsername());
        if (StringUtils.isEmpty(user) || !userLoginDto.getPassword().equals(user.getPassword())){
            return ResponseResult.error("用户不存在或者密码错误！");
        }


        //校验appID
        AppInfo appInfo = appInfoMapper.selectOneByAppId(userLoginDto.getAppId());
        if (StringUtils.isEmpty(appInfo)){
            return ResponseResult.error("应用ID不存在！");
        }

        //生成校验码并保存
        String code = generateCode(userLoginDto.getAppId(), user.getUsername());
        return ResponseResult.ok("查询成功",code);
    }

    @Override
    public ResponseResult<String> getToken(AppInfoDto appInfoDto) {
        //校验必填项
        if (StringUtils.isEmpty(appInfoDto.getAppId()) || StringUtils.isEmpty(appInfoDto.getAppSecret())
        || StringUtils.isEmpty(appInfoDto.getGrantCode()))
            return ResponseResult.error("请输入必填项！");

        //校验登录方app信息
        AppInfoExample appInfoExample = new AppInfoExample();
        AppInfoExample.Criteria condition = appInfoExample.createCriteria();
        condition.andAppIdEqualTo(appInfoDto.getAppId());
        condition.andAppSecretEqualTo(appInfoDto.getAppSecret());
        if (appInfoMapper.countByExample(appInfoExample) == 0) return ResponseResult.error("app信息错误！");

        //校验授权码
        String key = GRANTCODE_PREFIX + appInfoDto.getAppId();
        String code = (String)redisUtil.get(key);
        String username = "";
        if(StringUtils.isEmpty(code) || !appInfoDto.getGrantCode().equals(code.split("\\|")[0])) {
            return ResponseResult.error("授权码无效！");
        }
        else{
            redisUtil.del(key);
            username = code.split("\\|")[1];
        }
        //生成token并删除授权码
        String token = tokenUtil.generateJwtToken(appInfoDto.getAppSecret(), "SSO", appInfoDto.getAppId(), username);
        return ResponseResult.ok("查询成功",token);
    }
    public String generateCode(String appId,String username){
        String code = (String)redisUtil.get(GRANTCODE_PREFIX+appId);
        if (!StringUtils.isEmpty(code)) return code.split("\\|")[0];

        StringBuilder builderCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            builderCode.append(random.nextInt(10));
        }
        //保存appid,code|user映射关系,并设置失效时间
        redisUtil.set(GRANTCODE_PREFIX+appId,String.format("%s|%s",builderCode.toString(),username),GRANTCODE_TIMEOUT);

        return builderCode.toString();
    }
}
