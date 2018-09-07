package com.hui.day.learn.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.hui.day.learn.response.codes.CodeEnum;
import com.hui.day.learn.response.codes.Default0Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class RestResponse<T> implements Serializable{
    private static final long serialVersionUID = 3913053169036346111L;

    @ApiModelProperty(value = "执行状态码, 执行成功返回 0, 其他的都是 EXCEPTION")
    private long code;

    @ApiModelProperty(value = "消息, OK 的时候, message 一般不填")
    private String message = null;

    @ApiModelProperty(value = "具体的数据, EXCEPTION 的时候, data 一般 不填")
    private T data = null;

    @ApiModelProperty(value = "时间戳, 只在 exception 时有用")
    private Date timestamp = null;

    @ApiModelProperty(value = "放一些辅助信息 key->value, 一般用来进一步描述异常信息, OK 时暂未用到")
    private Map<String, Object> extraInfo = null;

    public static <T> RestResponse ok(T data) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.code = Default0Code.OK.getCode();
        restResponse.data = data;
        return restResponse;
    }

    public static <T> RestResponse ok(CodeEnum codeEnum) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.code = Default0Code.OK.getCode();
        restResponse.message = codeEnum.getDefaultMessage();
        return restResponse;
    }

    public static RestResponse exception(Exception e) {
        Map<String, Object> exceptionInfo=new HashMap<>();
        exceptionInfo.put("Error",e.getMessage());
        return exception(Default0Code.INTERNAL_SERVER_ERROR, exceptionInfo);
    }

    public static RestResponse exception(CodeEnum codeEnum, Map<String, Object> exceptionInfo) {
        RestResponse restResponse = new RestResponse();
        restResponse.code = codeEnum.getCode();
        restResponse.message = codeEnum.getDefaultMessage();
        // 国际化时, 使用这个
        //        restResponse.message = I18nUtil.getResponseMessage(responseCode);
        restResponse.timestamp = new Date();
        restResponse.extraInfo = exceptionInfo;
        return restResponse;
    }

    public static RestResponse exception(CodeEnum codeEnum) {
        return exception(codeEnum,null);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .add("data", data)
                .add("timestamp", timestamp == null ? null : timestamp.getTime())
                .add("extraInfo", extraInfo)
                .toString();
    }
}