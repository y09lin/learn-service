package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_user")
public class TbUser extends BaseDomain {
    /**
     * 用户id
     * */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户名称
     * */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码，sha512，bcrypt，aes
     * */
    @Column(name = "password")
    private String password;

    /**
     * 盐。512+盐然后Bcrypt
     * */
    @Column(name = "salt")
    private String salt;

    /**
     * 注册时间
     * */
    @Column(name = "signTime")
    private Date sign_time;

    /**
     * 用户权限，对应dict字典code
     * */
    @Column(name = "admin_level")
    private Integer adminLevel;

    /**
     * 积分
     * */
    @Column(name = "credit")
    private Integer credit;
}