package com.kyj.fmk.core.exception.handler;

import com.kyj.fmk.core.cst.dto.ResApiErrDTO;
import com.kyj.fmk.core.exception.custom.KyjAsncException;
import com.kyj.fmk.core.exception.custom.KyjBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 2025-05-29
 * @author 김용준
 * Restful Api에서 사용하는 글로벌 익셉션 핸들러
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * base 익셉션 핸들러
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(KyjBaseException.class)
    public ResponseEntity<ResApiErrDTO<?>> handleBaseException(KyjBaseException ex){

       ResApiErrDTO<Void> resApiErrDTO = ErrHelper.determineErrRes(ex);
        return ResponseEntity
                .status(HttpStatus.valueOf(resApiErrDTO.getStatus()))
                .body(resApiErrDTO);
    }


}
