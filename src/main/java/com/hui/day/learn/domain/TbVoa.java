package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_voa")
public class TbVoa extends BaseDomain {
    /**
     * VOA文章id
     * */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "voa_id")
    private Long voaId;

    /**
     * 标题
     * */
    @Column(name = "title")
    private String title;

    /**
     * MP3下载地址
     * */
    @Column(name = "mp3")
    private String mp3;

    /**
     * 上传时间
     * */
    @Column(name = "upload_time")
    private Date uploadTime;

    /**
     * 上传者id，对应user_id
     * */
    @Column(name = "upload_user")
    private Long uploadUser;

    /**
     * voa来源，比如摘自哪个网站
     * */
    @Column(name = "source")
    private String source;

    /**
     * 作者
     * */
    @Column(name = "author")
    private String author;

    /**
     * 日期
     * */
    @Column(name = "date")
    private String date;
}