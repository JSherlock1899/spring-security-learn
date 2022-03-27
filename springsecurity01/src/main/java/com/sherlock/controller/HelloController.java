package com.sherlock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 14:23
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('hello333')")
    public String hello() {
        return "hello";
    }
}
