package com.jiangc.test.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: RespCode
 * @Description:
 * @Author: jiangcheng31
 * @Date: 2021/5/6 17:45
 */
@AllArgsConstructor
@Getter
public enum RespCode {
    SUCESS(200,"sucess"),
    FAIL(400,"fail");

    private Integer code;
    private String msg;
}
