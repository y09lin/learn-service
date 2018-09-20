package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * description ：生词库
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/20
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_word")
public class TbWord extends BaseDomain{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long wordId;

    @Column(name = "word")
    private String word;

    @Column(name = "translation")
    private String translation;

    @Column(name = "phonetic")
    private String phonetic;

    @Column(name = "us_phonetic")
    private String usPhonetic;

    @Column(name = "uk_phonetic")
    private String ukPhonetic;

    @Column(name = "explains")
    private String explains;

    @Column(name = "web")
    private String web;
}
