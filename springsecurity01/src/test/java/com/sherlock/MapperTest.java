package com.sherlock;

import com.sherlock.mapper.UserMapper;
import com.sherlock.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 15:19
 */
@Slf4j
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        log.info(users.toString());
    }

    @Test
    public void testPwdEnding() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode1 = encoder.encode("1234");
        String encode2 = encoder.encode("1234");
        log.info(encode1);
        log.info(encode2);

        System.out.println(encoder.matches("1234",
                "$2a$10$xZWyi7Yal64z21Bv8Urnq.imJsF/oBd25oV3.sdtm.7GSaHaLI0m."));

        System.out.println(encoder.matches("1234",
                "$2a$10$Ev2CqamNGyJsEIdrEt1Vlu8tWeTZdpg/ZffPW7qwmzFObBmDb1xNC"));

    }
}
