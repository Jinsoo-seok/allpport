package com.cudo.allpport.controller;


import com.cudo.allpport.config.ParamException;
import com.cudo.allpport.service.ServiceUser;
import com.cudo.allpport.util.ParameterUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.cudo.allpport.util.ParameterUtils.parameterString;
import static com.cudo.allpport.util.ParameterUtils.parameterValidation;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ControllerUser {

    final ServiceUser serviceUser;

    @GetMapping("/user/{userName}")
    public ResponseEntity<Object> getUser(HttpServletRequest request, @PathVariable String userName){
        long startTime = System.currentTimeMillis();
        String apiInfo ="[" + request.getMethod() + "] [" + request.getRequestURI() + "]";
        log.info("{} [START] [{}]", apiInfo, startTime);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.putAll(ParameterUtils.responseOption("SUCCESS"));

        try{
            responseMap = serviceUser.getUser(userName);
        }
        catch(Exception exception){
            log.error("[Exception][getUser] - " + exception.getMessage());
            responseMap.putAll(ParameterUtils.responseOption("FAIL"));
            responseMap.put("log", exception.getMessage());
        }

        long endTime = System.currentTimeMillis();
        long procTime = endTime-startTime;
        log.info("{} [END] [{}] - {}", apiInfo, procTime, responseMap.get("code"));

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(responseMap, headers, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUserList(HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        String apiInfo ="[" + request.getMethod() + "] [" + request.getRequestURI() + "]";
        log.info("{} [START] [{}]", apiInfo, startTime);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.putAll(ParameterUtils.responseOption("SUCCESS"));

        try{
            responseMap = serviceUser.getUserList();
        }
        catch(Exception exception){
            log.error("[Exception][getUserList] - " + exception.getMessage());
            responseMap.putAll(ParameterUtils.responseOption("FAIL"));
            responseMap.put("log", exception.getMessage());
        }

        long endTime = System.currentTimeMillis();
        long procTime = endTime-startTime;
        log.info("{} [END] [{}] - {}", apiInfo, procTime, responseMap.get("code"));

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(responseMap, headers, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Object> postUser(HttpServletRequest request, @RequestBody Map<String, Object> param){
        long startTime = System.currentTimeMillis();
        String apiInfo ="[" + request.getMethod() + "] [" + request.getRequestURI() + "]";
        log.info("{} [START] [{}]", apiInfo, startTime);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.putAll(ParameterUtils.responseOption("SUCCESS"));

        String[] keyList = {"userName", "userPassword"};

        try{
            parameterValidation(param, keyList);
            parameterString("userName", param.get("userName"), true, 0, null);
            parameterString("userPassword", param.get("userPassword"), true, 0, null);

            responseMap = serviceUser.postUser(param);

        }catch (ParamException paramException){
            responseMap.put("code", paramException.getCode());
            responseMap.put("message", paramException.getMessage());
        }catch(Exception exception){
            log.error("[Exception][postUser] - " + exception.getMessage());
            responseMap.putAll(ParameterUtils.responseOption("FAIL"));
            responseMap.put("log", exception.getMessage());
        }

        long endTime = System.currentTimeMillis();
        long procTime = endTime-startTime;
        log.info("{} [END] [{}] - {}", apiInfo, procTime, responseMap.get("code"));

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity(responseMap, headers, HttpStatus.OK);
    }
}
