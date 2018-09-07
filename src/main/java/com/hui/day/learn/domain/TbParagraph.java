package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_paragraph")
public class TbParagraph extends BaseDomain {
    /**
     * 段落id
     * */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "paragraph_id")
    private Long paragraphId;

    /**
     * 对应的文章id
     * */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 句子数量
     * */
    @Column(name = "sentence_count")
    private Integer sentenceCount;
}