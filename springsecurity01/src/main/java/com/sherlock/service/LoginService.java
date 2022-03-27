package com.sherlock.service;

import com.sherlock.pojo.entity.User;
import com.sherlock.utils.RespResult;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 16:13
 */
public interface LoginService {

    RespResult login(User user);
}
