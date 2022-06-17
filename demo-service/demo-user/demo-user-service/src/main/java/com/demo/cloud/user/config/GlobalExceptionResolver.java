package com.demo.cloud.user.config;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.ArrayUtil;
import com.demo.cloud.common.core.constant.CommonCode;
import com.demo.cloud.common.core.exception.BusinessException;
import com.demo.cloud.common.core.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

/**
 * @author 普通全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionResolver {
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }



    /**
     * 多参数校验异常信息处理
     *
     * @param fieldErrors errors
     * @return error message
     */
    private String processFieldErrors(List<FieldError> fieldErrors) {
        if (ArrayUtil.isEmpty(fieldErrors)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            sb.append(localizedErrorMessage);
            sb.append(";");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(fieldError, currentLocale);
    }

    /**
     * 处理参数校验异常
     *
     * @param ex ex
     * @return ResponseEntity
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleArgException(MethodArgumentNotValidException ex) {
        BindingResult b = ex.getBindingResult();
        List<FieldError> fieldErrors = b.getFieldErrors();
        final String message = processFieldErrors(fieldErrors);
        return ApiResponse.failed(CommonCode.PARAM_VALID_ERROR, message);
    }


    /**
     * 参数校验
     *
     * @param ex ex
     * @return ResponseEntity
     */
    @ExceptionHandler(BindException.class)
    public ApiResponse handleArgException(BindException ex) {
        BindingResult b = ex.getBindingResult();
        List<FieldError> fieldErrors = b.getFieldErrors();
        final String message = processFieldErrors(fieldErrors);
        return ApiResponse.failed(CommonCode.PARAM_VALID_ERROR, message);
    }

    /**
     * 业务异常处理
     *
     * @param ex 业务异常
     * @return ResponseEntity
     */
    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    public ApiResponse handleBusinessException(BusinessException ex) {
        log.warn(String.format("业务异常：%d,%s", ex.getCode(), ex.getMessage()));
        return ApiResponse.failed(ex.getMessage());
    }


    @ResponseBody
    @ExceptionHandler({ValidateException.class})
    public ApiResponse handleValidateException(ValidateException ex) {
        log.warn(String.format("参数异常：%s", ex.getMessage()));
        return ApiResponse.failed(CommonCode.PARAM_VALID_ERROR, ex.getMessage());
    }


    /**
     * 未知异常处理
     *
     * @param ex 异常
     * @return ResponseEntity
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception ex) {
        log.error(String.format("服务器内部异常：%s", ex.getMessage()));
        return ApiResponse.failed(CommonCode.INTERNAL_SERVER_ERROR,ex.getMessage());
    }
}
