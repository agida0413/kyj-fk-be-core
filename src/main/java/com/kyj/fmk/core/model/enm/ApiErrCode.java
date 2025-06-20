package com.kyj.fmk.core.model.enm;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
/**
 * 2025-05-29
 * @author 김용준
 * Restful Api에서 사용하는 에러응답 Enum
 */
@Getter
public enum ApiErrCode {

    CM001("CM001","기본응답메시지 {0}"),
    CM002("CM002","서버내부 오류입니다."),
    CM003("CM003","쿠키추출 오류입니다."),
    CM004("CM004","UTIL 클래스 오류입니다."),
    CM005("CM005","메일전송중 오류가 발생했습니다."),
    CM006("CM006","비동기 서비스수행중 오류가 발생했습니다."),
    CM007("CM007","지원되지 않는 파일확장자입니다."),
    CM008("CM008","업로드 최대용량을 초과하였습니다."),
    CM009("CM009","파일저장중 알수없는 오류가 발생하였습니다."),
    CM010("CM010","파일삭제중 알수없는 오류가 발생하였습니다."),
    CM011("CM011","파일다운로드중 알수없는 오류가 발생하였습니다."),
    CM012("CM012","분산락획득에 실패하였습니다.");

    private final String code;
    private final String msg;

    /**
     * 에러코드 생성자
     * @param code
     * @param msg
     */
    ApiErrCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 정적 맵 초기화 (O(1) 조회용)
     */
    private static final Map<String, ApiErrCode> CODE_MAP = new HashMap<>();

    static {
        for (ApiErrCode errCode : values()) {
            CODE_MAP.put(errCode.code, errCode);
        }
    }

    /**
     * O1복잡도로 정적 맵에서 알맞은 메시지를 가져온다.
     * @param code
     * @return ApiErrCode
     */
    public static String of(String code) {
        return CODE_MAP.get(code).getMsg();
    }



}
