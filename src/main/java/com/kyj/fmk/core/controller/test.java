package com.kyj.fmk.core.controller;

import com.kyj.fmk.core.async.AsyncVoidInvoke;
import com.kyj.fmk.core.cst.dto.ResApiDTO;
import com.kyj.fmk.core.cst.enm.ApiErrCode;
import com.kyj.fmk.core.cst.enm.FileType;
import com.kyj.fmk.core.exception.custom.KyjBatException;
import com.kyj.fmk.core.exception.custom.KyjSysException;
import com.kyj.fmk.core.file.FileService;
import com.kyj.fmk.core.mail.MailSender;
import com.kyj.fmk.core.util.CookieUtil;
import com.kyj.fmk.core.util.RandomGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class test {

   private final  MailSender mailSender;
    private final FileService fileService;
    @RequestMapping("/test")
    public ResponseEntity<ResApiDTO<Void>> test(){

        return ResponseEntity
                .ok()
                .body(new ResApiDTO<Void>());
    }


    @RequestMapping("/test2")
    public ResponseEntity<ResApiDTO<Map>> test2(){
Map<String,String> map = new HashMap<>();
map.put("ttt","123123");
        return ResponseEntity
                .ok()
                .body(new ResApiDTO<Map>(map));
    }


    @RequestMapping("/test3")
    public ResponseEntity<ResApiDTO<Map>> test3(){
        throw new KyjSysException(ApiErrCode.CM001,"시스템오류");
    }


    @RequestMapping("/test4")
    public ResponseEntity<ResApiDTO<Map>> test4(HttpHeaders httpHeaders){

     CookieUtil.createCookie("test","111",3000, "/",httpHeaders);

        return ResponseEntity.ok().
        headers(httpHeaders).body(null);
    }


    @RequestMapping("/test5")
    public String test4(HttpServletRequest request){


     String object=  (String) CookieUtil.getCookie("test",request);
    log.trace("cookie={}",object);
        return object;
    }

    @RequestMapping("/test6")
    public  ResponseEntity<ResApiDTO<Map>>  test6(HttpHeaders httpHeaders){

        CookieUtil.deleteCookie("test",httpHeaders,"/");
        return ResponseEntity.ok().
                headers(httpHeaders).body(null);
    }


    @RequestMapping("/test7")
    public  ResponseEntity<ResApiDTO<?>>  test7(){

    String str = RandomGenerator.generateRandomNumber(10);

     ResApiDTO<String> apiDTO = new ResApiDTO(str);
        return ResponseEntity
                .ok()
                .body(apiDTO);
    }


    @RequestMapping("/test8")
    public  ResponseEntity<ResApiDTO<?>>  test8(@Valid testdto testdto){
//            throw new KyjSysException(ApiErrCode.CM006,"DDDDDDD");
        return ResponseEntity
                .ok()
                .body(null);
    }
}
