package com.haotian.core.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;

import java.io.Serializable;

/**
 * @Author: zhangpeng
 * @Date: 2019/4/12 10:02
 * @email zhangpeng@hiynn.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BaseVO", description = "返回的VO视图数据模型")
public class BaseVO<T> implements Serializable {

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
    private T data;
    @ApiModelProperty(value = "返回码")
    private String returnCode;
    @ApiModelProperty(value = "提示信息")
    private String returnMessage;

    public BaseVO() {
        this.returnCode = "001";
        this.returnMessage = "成功接收请求，视图模型未初始化";
    }


    public static BaseVO build() {
        return new BaseVO();
    }

    public BaseVO success() {
        this.returnCode = Code.RETURN_CODE_SUCCESS;
        this.returnMessage = Message.RETURN_CODE_SUCCESS_MSG;
        return this;
    }

    public BaseVO errorParam() {
        this.returnCode = Code.RETURN_CODE_PARAM_ERROR;
        this.returnMessage = Message.RETURN_CODE_PARAM_ERROR_MSG;
        return this;
    }

    public BaseVO errorAuth() {
        this.returnCode = Code.RETURN_CODE_AUTH_ERROR;
        this.returnMessage = Message.RETURN_CODE_AUTH_ERROR_MSG;
        return this;
    }

    public BaseVO errorSystem() {
        this.returnCode = Code.RETURN_CODE_SYS_ERROR;
        this.returnMessage = Message.RETURN_CODE_SYS_ERROR_MSG;
        return this;
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

    public T getData() {
        return data;
    }

    public BaseVO setData(T data) {
        this.success();
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
