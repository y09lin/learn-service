package com.hui.day.learn.response.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description ：
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/14
 */
@Data
public class SentenceVO {
    @ApiModelProperty("句子id")
    private Long sentenceId;

    @ApiModelProperty("所属段落id")
    private Long paragraphId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("音视频开始时间，秒")
    private Integer beginPoint;

    @ApiModelProperty("音视频结束时间，秒")
    private Integer endPoint;
}
