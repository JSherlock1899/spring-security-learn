package com.sherlock.service.impl;

import com.sherlock.pojo.entity.LoginUser;
import com.sherlock.pojo.entity.User;
import com.sherlock.service.LoginService;
import com.sherlock.utils.JwtUtil;
import com.sherlock.utils.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 16:13
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public RespResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(JwtUtil.JWTSEC, 0, String.valueOf(id));
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new RespResult(200, "登录成功", map);
    }
}
