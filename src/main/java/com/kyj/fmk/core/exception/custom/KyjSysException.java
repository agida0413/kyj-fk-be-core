package com.kyj.fmk.core.exception.custom;

import com.kyj.fmk.core.model.enm.ApiErrCode;
/**
 * 2025-05-29
 * @author 김용준
 * Restful Api에서 사용하는 시스템 Exception
 */
public class KyjSysException extends KyjBaseException{
    /**
     *
     * @param apiErrCode
     */
    public KyjSysException(ApiErrCode apiErrCode){
        super(apiErrCode);
        this.setCode(apiErrCode.getCode());

    }

    /**
     *
     * @param errCode
     * @param msg
     */
    public KyjSysException(ApiErrCode errCode, String msg){
        super(errCode,msg);
        this.setMsg(msg);
        this.setCode(errCode.getCode());
    }
}
