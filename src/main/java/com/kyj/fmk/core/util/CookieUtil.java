package com.kyj.fmk.core.util;

import com.kyj.fmk.core.model.enm.ApiErrCode;
import com.kyj.fmk.core.exception.custom.KyjSysException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

/**
 * 2025-05-31
 * @author 김용준
 * Restful Api에서 사용하는 쿠키관련 유틸클래스
 */
public class CookieUtil {

    /**
     * 서블릿 리퀘스트로부터 쿠키를 읽는 메소드
     * @param key
     * @param request
     * @return
     */
        public static Object getCookie(String key, HttpServletRequest request ) {

            Object object="";//리턴 오브젝터 초기화

            try{
                Cookie[] cookies = request.getCookies();

                for (Cookie cookie : cookies) {

                    if (cookie.getName().equals(key)) {//쿠키를 읽어서 매개변수의 키값인것을 가져옴

                        object= cookie.getValue(); // 그것에 대한 value를 읽음
                    }
                }
            }catch (Exception e){
                throw new KyjSysException(ApiErrCode.CM003);
            }


            return object; // 가져온값 리턴
        }


    /**
     * 쿠키를 생성하고 헤더에 담는 메소드
     * @param key
     * @param value
     * @param age
     * @param path
     * @param httpHeaders
     */
        public static void createCookie(String key, String value, int age, String path, HttpHeaders httpHeaders) {//저장할 쿠키의 키값과 밸류를 매개변수로 받음

            headerAdd(httpHeaders,createCookie(key,value,age,path));
        }


    /**
     * 쿠키를 생성하는 메소드
     * @param key
     * @param value
     * @param age
     * @param path
     * @return
     */
        public static ResponseCookie createCookie(String key, String value, int age ,String path) {//저장할 쿠키의 키값과 밸류를 매개변수로 받음

            ResponseCookie cookie = ResponseCookie.from(key, value)
                    .httpOnly(true)
                   // .secure(true)  // HTTPS 사용할 경우 true
                    .path(path)
                    .maxAge(age)  // 1시간
                    .build();

            return  cookie;
        }

    /**
     * 쿠키를 제거하고 헤더에 담는 메소드
     * @param key
     * @param httpHeaders
     * @param path
     */
        public static void deleteCookie(String key, HttpHeaders httpHeaders,String path) {

            headerAdd(httpHeaders,deleteCookie(key,path));
        }

    /**
     * 쿠키를 제거하는 메소드
     * @param key
     * @param path
     * @return
     */
        public static ResponseCookie deleteCookie(String key,String path) {
            ResponseCookie cookie = ResponseCookie.from(key, null)
                    .httpOnly(true)
                    // .secure(true)  // HTTPS 사용할 경우 true
                    .path(path)
                    .maxAge(0)  // 1시간
                    .build();

            return cookie;
        }

    /**
     * 쿠키를 제어하고 결과적을로 헤더에 담는 메소드
     * @param headers
     * @param cookie
     */
    private static void headerAdd(HttpHeaders headers,ResponseCookie cookie){
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        }
}
