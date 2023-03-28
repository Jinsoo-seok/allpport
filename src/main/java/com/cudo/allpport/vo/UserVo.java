package com.cudo.allpport.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_info")
public class UserVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;

    @Column(nullable = false)
    private String userName;

    @JsonIgnore
    @Column(nullable = false)
    private String userPassword;

}
