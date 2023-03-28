package com.cudo.allpport.service;

import com.cudo.allpport.repository.RepositoryUser;
import com.cudo.allpport.util.ParameterUtils;
import com.cudo.allpport.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceUserImpl implements ServiceUser {

    final RepositoryUser repositoryUser;

    final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> getUser(String userName) {
        Map<String, Object> responseMap = new HashMap<>();

        UserVo userVo = repositoryUser.findByUserName(userName);

        if(userVo != null){
            responseMap.put("data", userVo);
            responseMap.putAll(ParameterUtils.responseOption("SUCCESS"));
        }
        else{
            responseMap.putAll(ParameterUtils.responseOption("NO_CONTENT"));
        }
        return responseMap;
    }

    @Override
    public Map<String, Object> getUserList() {
        Map<String, Object> responseMap = new HashMap<>();

        List<UserVo> userList = repositoryUser.findAll();

        if(userList.size()>0){
            responseMap.put("data", userList);
            responseMap.putAll(ParameterUtils.responseOption("SUCCESS"));
        }
        else{
            responseMap.putAll(ParameterUtils.responseOption("NO_CONTENT"));
        }

        return responseMap;
    }

    @Override
    public Map<String, Object> postUser(Map<String, Object> param) {
        Map<String, Object> responseMap = new HashMap<>();

        UserVo userVo = new UserVo();
        userVo.setUserName((String) param.get("userName"));
        userVo.setUserPassword(passwordEncoder.encode((CharSequence) param.get("userPassword")));

        UserVo saveResult = repositoryUser.save(userVo);

        if(saveResult != null){
            responseMap.put("data", saveResult);
            responseMap.putAll(ParameterUtils.responseOption("SUCCESS"));
        }
        else{
            responseMap.putAll(ParameterUtils.responseOption("FAIL"));
        }

        return responseMap;
    }
}
