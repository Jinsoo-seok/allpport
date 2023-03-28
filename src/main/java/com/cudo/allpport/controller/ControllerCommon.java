package com.cudo.allpport.controller;

import com.cudo.allpport.util.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
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
public class ControllerCommon {

    @GetMapping("healthCheck")
    public ResponseEntity getHealthCheck(HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        String apiInfo = "[" + request.getMethod() + "] [" + request.getRequestURI() + "]";
        log.info("{} [START] [{}]", apiInfo, startTime);

        // Service
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("code", ResponseCode.SUCCESS.getCode());
        responseMap.put("message", ResponseCode.SUCCESS.getMessage());
        log.info("HealthCheck 200 OK");

        long endTime = System.currentTimeMillis();
        long procTime = endTime-startTime;
        log.info("{} [END] [{}] - {}", apiInfo, procTime, responseMap.get("code"));

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(responseMap, headers, HttpStatus.OK);
    }
}
