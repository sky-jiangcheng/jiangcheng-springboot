package com.jiangc.test.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: RespResult
 * @Description:
 * @Author: jiangcheng31
 * @Date: 2021/5/6 17:37
 */
@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RespResult<T> implements Serializable {
    @ApiModelProperty("响应状态码")
    private Integer code;

    @ApiModelProperty("响应描述")
    private String msg;

    @ApiModelProperty("响应数据")
    private T data;

    private RespResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static RespResult ok(){
        return new RespResult(RespCode.SUCESS.getCode(),RespCode.SUCESS.getMsg());
    }

    public static <T> RespResult<T> ok(T data){
        return new RespResult<T>(RespCode.SUCESS.getCode(),RespCode.SUCESS.getMsg(),data);
    }

    public static RespResult error(){
        return new RespResult(RespCode.FAIL.getCode(),RespCode.FAIL.getMsg());
    }

    public static RespResult error(String msg){
        return new RespResult(RespCode.FAIL.getCode(),msg);
    }
}
