package com.sherlock.controller;

import com.sherlock.pojo.entity.User;
import com.sherlock.service.LoginService;
import com.sherlock.utils.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 16:11
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public RespResult login(@RequestBody User user) {
        RespResult res = loginService.login(user);
        return res;
    }
}
