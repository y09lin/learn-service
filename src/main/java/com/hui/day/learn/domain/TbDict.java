package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_dict")
public class TbDict extends BaseDomain {
    /**
     * 字典id
     * */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "dict_id")
    private Integer dictId;

    /**
     * 代号
     * */
    @Column(name = "code")
    private Integer code;

    /**
     * 显示名称
     * */
    @Column(name = "name")
    private String name;

    /**
     * 类型
     * */
    @Column(name = "type")
    private String type;

    /**
     * 状态，1有效
     * */
    @Column(name = "status")
    private Integer status;

    /**
     * 描述
     * */
    @Column(name = "discription")
    private String discription;
}
