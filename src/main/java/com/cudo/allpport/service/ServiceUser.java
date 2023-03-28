package com.cudo.allpport.service;


import java.util.Map;

public interface ServiceUser {

    // 조회
    Map<String, Object> getUser (String userName);

    // 전체 조회
    Map<String, Object> getUserList ();

    // 등록
    Map<String, Object> postUser (Map<String, Object> param);


}
