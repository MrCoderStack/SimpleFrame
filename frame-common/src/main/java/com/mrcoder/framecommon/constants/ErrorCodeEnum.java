package com.mrcoder.framecommon.constants;

import com.mrcoder.framecommon.model.ResponseInfo;

/**
 * @Description: 系统错误类型枚举类
 * @author: mrcoder
 */
public enum ErrorCodeEnum {

    SYSTEM_ERROR(ResponseInfo.FAIL_CODE, "系统异常，请稍后重试"),

    NOT_LOGIN(-2, "用户未登录"),

    PARAM_ERROR(ResponseInfo.FAIL_CODE, "参数非法"),

    PARAM_MISSING(ResponseInfo.FAIL_CODE, "参数缺失");

    private Integer errCode;

    private String errMsg;

    private ErrorCodeEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

}

