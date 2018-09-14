package com.hui.day.learn.response.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description ：
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/14
 */
@Data
public class ParagraphVO {
    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("段落id")
    private Long paragraphId;

    @ApiModelProperty("句子List")
    private List<SentenceVO> sentenceList;
}
