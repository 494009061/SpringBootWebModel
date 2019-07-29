package com.haotian.core.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 只序列化不为null 字段  排除了字典类
 *
 * @Author: zhangpeng
 * @Date: 2019/4/12 10:02
 * @email zhangpeng@hiynn.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BaseVO", description = "返回的VO视图数据模型")
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 4295924705096425656L;

    /**
     * spring jackson 和 fastJson 不进行序列化
     */
    @JsonInclude
    public final static class Code {
        @JSONField(serialize = false)
        public static final String RETURN_CODE_SUCCESS = "000";
        @JSONField(serialize = false)
        public static final String RETURN_CODE_PARAM_ERROR = "002";
        @JSONField(serialize = false)
        public static final String RETURN_CODE_AUTH_ERROR = "003";
        @JSONField(serialize = false)
        public static final String RETURN_CODE_SYS_ERROR = "999";
    }

    /**
     * spring jackson 和 fastJson 不进行序列化
     */
    @JsonInclude
    public final static class Message {
        @JSONField(serialize = false)
        public static final String RETURN_CODE_SUCCESS_MSG = "success";
        @JSONField(serialize = false)
        public static final String RETURN_CODE_PARAM_ERROR_MSG = "param error";
        @JSONField(serialize = false)
        public static final String RETURN_CODE_AUTH_ERROR_MSG = "authentication failed";
        @JSONField(serialize = false)
        public static final String RETURN_CODE_SYS_ERROR_MSG = "system error";
    }


    @ApiModelProperty(value = "数据")
    private Object data;
    @ApiModelProperty(value = "返回码")
    private String returnCode;
    @ApiModelProperty(value = "提示信息")
    private String returnMessage;

    /**
     * 禁止手动创建 强制要求自动初始化
     */
    private BaseVO() {
    }

    private static BaseVO build() {
        return new BaseVO();
    }

    public static BaseVO success() {
        return build()
                .setReturnCode(Code.RETURN_CODE_SUCCESS)
                .setReturnMessage(Message.RETURN_CODE_SUCCESS_MSG);
    }

    public static BaseVO errorParam() {
        return build()
                .setReturnCode(Code.RETURN_CODE_PARAM_ERROR)
                .setReturnMessage(Message.RETURN_CODE_PARAM_ERROR_MSG);
    }

    public static BaseVO errorAuth() {
        return build()
                .setReturnCode(Code.RETURN_CODE_AUTH_ERROR)
                .setReturnMessage(Message.RETURN_CODE_AUTH_ERROR_MSG);
    }

    public static BaseVO errorSystem() {
        return build()
                .setReturnCode(Code.RETURN_CODE_SYS_ERROR)
                .setReturnMessage(Message.RETURN_CODE_SYS_ERROR_MSG);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public BaseVO setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public BaseVO setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public <T> T dataCast(Class<T> clazz) {
       return clazz.cast(this.data);
    }

    public BaseVO setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
