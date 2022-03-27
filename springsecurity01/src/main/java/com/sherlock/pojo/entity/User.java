package com.sherlock.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 15:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("auth_user")
public class User {

    @TableId
    private Long id;

    private String username;

    private String password;

}
