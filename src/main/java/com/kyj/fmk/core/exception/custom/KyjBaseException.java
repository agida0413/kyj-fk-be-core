package com.kyj.fmk.core.exception.custom;

import com.kyj.fmk.core.model.enm.ApiErrCode;
import lombok.Getter;
import lombok.Setter;
/**
 * 2025-05-29
 * @author 김용준
 * Restful Api에서 사용하는 BASE exception
 */
@Setter
@Getter
public class KyjBaseException extends RuntimeException{
    private String msg;
    private String code;

    /**
     *
     * @param apiErrCode
     */
    public KyjBaseException(ApiErrCode apiErrCode) {
        super(apiErrCode.getCode());
        // 상수 에러코드
        this.msg=msg;
    }

    /**
     *
     * @param errCode
     * @param msg
     */
    public KyjBaseException(ApiErrCode errCode, String msg) {
        super(msg);
        this.msg = msg;
        this.code = errCode.getCode(); // enum에서 code 추출
    }
}
