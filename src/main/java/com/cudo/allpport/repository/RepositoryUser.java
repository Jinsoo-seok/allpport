package com.cudo.allpport.repository;

import com.cudo.allpport.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<UserVo, Long> {
}
