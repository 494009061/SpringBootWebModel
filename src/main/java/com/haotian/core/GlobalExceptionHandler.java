package com.haotian.core;

import com.haotian.core.vo.BaseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理器
 *
 * @author shanming.yang
 * @date 2018年7月13日 下午10:16:19
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * spring接收请求参数时反射错误 ，类型不匹配
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public BaseVO handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数异常", e.getMessage());
        return BaseVO.errorParam().setReturnMessage(e.getMessage());
    }

    /**
     * 参数校验使用3 用于捕获行参绑定的参数 如 String id  当 id 不传的时候 且没有配置选填的时候 出现此异常 自动捕获返回提示 spring 接收参数时 必填项 错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public BaseVO handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("参数异常", e.getMessage());
        return BaseVO.errorParam().setReturnMessage(e.getMessage());
    }

    /**
     * 入库时 参数包含了不匹配的编码符号
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {UncategorizedSQLException.class})
    public BaseVO handlerUncategorizedSQLException(UncategorizedSQLException e) {

        int errorCode = e.getSQLException().getErrorCode();

        switch (errorCode) {
            case 1366:
            case 1267:
                return BaseVO.errorParam().setReturnMessage("请求参数内容不合法");
            default:
                log.error("异常信息：", e);
                return BaseVO.errorParam().setReturnMessage("后台处理异常");
        }
    }

    /**
     * 参数校验使用 @Validated 时返回的错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public BaseVO handlerBindException(BindException e) {
        log.error("bindExceptionErrorHandler info:{}", e.getMessage());
        FieldError fieldError = e.getFieldError();
        String defaultMessage = null;
        if (fieldError != null) {
            defaultMessage = fieldError.getDefaultMessage();
        } else {
            defaultMessage = e.getMessage();
        }
        if (defaultMessage == null) {
            defaultMessage = fieldError.getField() + ":" + "错误信息没有设置,请联系后台管理";
        }
        return BaseVO.errorParam().setReturnMessage(defaultMessage);
    }

    /**
     * 参数校验使用2  当参数使用 requestBody 的时候 @Validated 触发的 为MethodArgumentNotValidException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public BaseVO handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("bindExceptionErrorHandler info:{}", e.getMessage());
        FieldError fieldError = e.getBindingResult().getFieldError();
        String defaultMessage = null;
        if (fieldError != null) {
            defaultMessage = fieldError.getDefaultMessage();
        } else {
            defaultMessage = e.getMessage();
        }
        if (defaultMessage == null) {
            defaultMessage = fieldError.getField() + ":" + "错误信息没有设置,请联系后台管理";
        }
        return BaseVO.errorParam().setReturnMessage(defaultMessage);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BaseVO handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return  BaseVO.errorSystem().setReturnMessage("触发唯一索引");
    }


    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public BaseVO allExceptionHandler(Throwable ex) {
        log.error("异常信息", ex);
        return  BaseVO.errorSystem().setReturnMessage("后台处理异常:" + ex.getMessage());
    }
}
