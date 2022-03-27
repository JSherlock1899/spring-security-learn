package com.sherlock.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: jiang yonghui
 * @description:
 * @date: 2022/3/27 15:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public RespResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
}
