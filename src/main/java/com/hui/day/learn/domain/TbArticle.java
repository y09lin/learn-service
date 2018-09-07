package com.hui.day.learn.domain;

import com.hui.day.learn.response.dto.ArticleVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wahaha
 * */
@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_article")
@SqlResultSetMapping(
        name = "ArticleVOMapping",
        classes = @ConstructorResult(
                targetClass = ArticleVO.class,
                columns = {
                        @ColumnResult(name = "articleId", type = Long.class),
                        @ColumnResult(name = "type"),
                        @ColumnResult(name = "title"),
                        @ColumnResult(name = "author"),
                        @ColumnResult(name = "date"),
                        @ColumnResult(name = "source")
                }))
public class TbArticle extends BaseDomain{

    /**
     * 文章id
     * */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 对应字典中的code
     * */
    @Column(name = "dict_code")
    private Integer dictCode;

    /**
     * 来源id，比如voa_id，ted_id，book_id等
     * */
    @Column(name = "src_id")
    private Long srcId;

    /**
     * 段落数量
     * */
    @Column(name = "paragraph_count")
    private Integer paragraphCount;

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

    /**
     * 状态：1正常
     * */
    @Column(name = "status")
    private Integer status;
}
