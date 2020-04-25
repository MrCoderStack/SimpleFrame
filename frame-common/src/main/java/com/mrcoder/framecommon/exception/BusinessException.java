package com.mrcoder.framecommon.exception;

import com.mrcoder.framecommon.constants.ErrorCodeEnum;
import com.mrcoder.framecommon.model.ResponseInfo;
import org.apache.commons.lang3.StringUtils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 全局业务异常[HTTP状态码默认返回200]
 * @author: mrcoder
 */
@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6842004487143726249L;

    private Integer errCode;

    public BusinessException() {
        super(ErrorCodeEnum.SYSTEM_ERROR.getErrMsg());
        this.errCode = ResponseInfo.FAIL_CODE;
    }

    public BusinessException(String errMsg) {
        super(errMsg);
        this.errCode = ResponseInfo.FAIL_CODE;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getErrMsg());
        this.errCode = errorCodeEnum.getErrCode();
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String errMsg) {
        super(StringUtils.isEmpty(errMsg) ? errorCodeEnum.getErrMsg() : errMsg);
        this.errCode = errorCodeEnum.getErrCode();
    }
}