package com.cudo.allpport.service;

import com.cudo.allpport.repository.RepositoryUser;
import com.cudo.allpport.util.ParameterUtils;
import com.cudo.allpport.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServiceUserImpl implements ServiceUser {

    final RepositoryUser repositoryUser;

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
}
