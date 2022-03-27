package com.sherlock.filter;

import com.sherlock.mapper.UserMapper;
import com.sherlock.pojo.entity.User;
import com.sherlock.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 16:40
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            doFilter(request, response, filterChain);
            return;
        }

        //解析token
        Claims claims = JwtUtil.parseJWT(JwtUtil.JWTSEC, token);
        String id = claims.getSubject();
        if (!StringUtils.hasText(id)) {
            throw new RuntimeException("非法token");
        }

        //查询用户
        User user = userMapper.selectById(id);
        if (Objects.isNull(user)) {
            throw new RuntimeException("非法用户");
        }

        //放入SecurityContextHolder
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = Arrays.asList("hello", "test").stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        ;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, simpleGrantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        doFilter(request, response, filterChain);
    }
}
