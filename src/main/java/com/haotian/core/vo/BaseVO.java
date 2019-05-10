package com.haotian.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: zhangpeng
 * @Date: 2019/4/12 10:02
 * @email zhangpeng@hiynn.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BaseVO",description = "返回的VO视图数据模型")
public class BaseVO {

    private  final String RETURN_CODE_SUCCESS = "000";
    private  final String RETURN_CODE_SUCCESS_MSG = "success";
    private  final String RETURN_CODE_PARAM_ERROR = "002";
    private  final String RETURN_CODE_PARAM_ERROR_MSG = "param error";
    private  final String RETURN_CODE_AUTH_ERROR = "003";
    private  final String RETURN_CODE_AUTH_ERROR_MSG = "User authentication failed";
    private  final String RETURN_CODE_SYS_ERROR = "999";
    private  final String RETURN_CODE_SYS_ERROR_MSG = "system error";
    @ApiModelProperty(value = "数据")
    private Object data;
    @ApiModelProperty(value = "返回码")
    private String returnCode;
    @ApiModelProperty(value = "提示信息")
    private String returnMessage;

    public BaseVO(){
        this.returnCode="001";
        this.returnMessage = "成功接收请求，视图模型未初始化";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseVO successReturn() {
        this.returnCode = this.RETURN_CODE_SUCCESS;
        this.returnMessage = this.RETURN_CODE_SUCCESS_MSG;
        return this;
    }

    public void success() {
        this.returnCode = this.RETURN_CODE_SUCCESS;
        this.returnMessage = this.RETURN_CODE_SUCCESS_MSG;

    }

    public BaseVO errorParamReturn() {
        this.returnCode = this.RETURN_CODE_PARAM_ERROR;
        this.returnMessage = this.RETURN_CODE_PARAM_ERROR_MSG;
        return this;
    }
    public void errorParam() {
        this.returnCode = this.RETURN_CODE_PARAM_ERROR;
        this.returnMessage = this.RETURN_CODE_PARAM_ERROR_MSG;
    }

    public BaseVO errorSystemReturn() {
        this.returnCode = this.RETURN_CODE_SYS_ERROR;
        this.returnMessage = this.RETURN_CODE_SYS_ERROR_MSG;
        return this;
    }

    public void errorSystem() {
        this.returnCode = this.RETURN_CODE_SYS_ERROR;
        this.returnMessage = this.RETURN_CODE_SYS_ERROR_MSG;
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

    public void errorAuth(){
        this.returnCode=this.RETURN_CODE_AUTH_ERROR;
        this.returnMessage=this.RETURN_CODE_AUTH_ERROR_MSG;
    }

    public BaseVO errorAuthReturn(){
        this.returnCode=this.RETURN_CODE_AUTH_ERROR;
        this.returnMessage=this.RETURN_CODE_AUTH_ERROR_MSG;
        return this;
    }
    @Override
    public String toString() {
        return "BaseVO{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                '}';
    }
}
