package com.mrcoder.framecommon.exception;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.mrcoder.framecommon.constants.ErrorCodeEnum;
import com.mrcoder.framecommon.model.ResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 全局异常处理器
 * @author: mrcoder
 */
@Slf4j
@ControllerAdvice
public class GlobalExpectionHandler {


    /**
     * 异常捕获处理
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseInfo expectionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {

        log.error(e.getMessage());

        if (e instanceof MethodArgumentNotValidException) {
            // JavaBean参数校验异常[HTTP状态码500]
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors(); // 取出错误信息
            ObjectError error = allErrors.get(0); // 只返回第一个错误信息即可
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseInfo.fail(error.getDefaultMessage());

        } else if (e instanceof ConstraintViolationException) {
            // Controller方法参数校验异常[HTTP状态码500]
            String message = ((ConstraintViolationException) e).getConstraintViolations().iterator().next().getMessage();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseInfo.fail(message);

        } else if (e instanceof BusinessException) {
            // 自定义业务异常[HTTP状态码默认200]
            BusinessException exception = (BusinessException) e;
            return ResponseInfo.fail(exception.getErrCode(), exception.getMessage());

        } else {
            // 系统异常[HTTP状态码500]
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseInfo.fail(ErrorCodeEnum.SYSTEM_ERROR);

        }
    }
}