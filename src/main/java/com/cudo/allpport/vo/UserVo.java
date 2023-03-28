package com.cudo.allpport.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_info")
public class UserVo {

    @Id
    @Column
    private Integer userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Integer userPassword;

}
