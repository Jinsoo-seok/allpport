package com.cudo.allpport.controller;


import com.cudo.allpport.service.ServiceUser;
import com.cudo.allpport.util.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ControllerUser {

    final ServiceUser serviceUser;

    @GetMapping("/user")
    public ResponseEntity getUserList(HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        String apiInfo ="[" + request.getMethod() + "] [" + request.getRequestURI() + "]";
        log.info("{} [START] [{}]", apiInfo, startTime);

        Map<String, Object> responseMap = new HashMap<>();

        try{
            responseMap = serviceUser.getUserList();

            if(responseMap.isEmpty()){
                responseMap.put("code", ResponseCode.SUCCESS.getCode());
                responseMap.put("message", ResponseCode.SUCCESS.getMessage());
            }
        }
        catch(Exception exception){
            log.error("[Exception][getUserList] - " + exception.getMessage());
            responseMap.put("code", ResponseCode.FAIL.getCode());
            responseMap.put("message", ResponseCode.FAIL.getMessage());
            responseMap.put("log", exception.getMessage());
        }

        long endTime = System.currentTimeMillis();
        long procTime = System.currentTimeMillis();
        log.info("{} [END] [{}] - {}", apiInfo, procTime, responseMap.get("code"));

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(responseMap, headers, HttpStatus.OK);
    }
}
