package com.hui.day.learn.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_sentence")
public class TbSentence extends BaseDomain {
    /**
     * 句子id
     * */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sentence_id")
    private Long sentenceId;

    /**
     * 对应的段落id
     * */
    @Column(name = "paragraph_id")
    private Long paragraphId;

    /**
     * 句子内容
     * */
    @Column(name = "content")
    private String content;

    /**
     * 这个句子对应的音视频开始时间，秒
     * */
    @Column(name = "begin_point")
    private Integer beginPoint;

    /**
     * 这个句子对应的音视频结束时间，秒
     * */
    @Column(name = "end_point")
    private Integer endPoint;
}