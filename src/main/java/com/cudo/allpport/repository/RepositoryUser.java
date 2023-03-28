package com.cudo.allpport.repository;

import com.cudo.allpport.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<UserVo, Long> {

    UserVo findByUserName(String userName);

    UserVo findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
