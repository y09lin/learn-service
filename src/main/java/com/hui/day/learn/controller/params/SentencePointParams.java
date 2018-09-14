package com.hui.day.learn.controller.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description ：设置句子音视频起止时间点
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/14
 */
@Data
public class SentencePointParams {
    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("句子节点列表")
    private List<PointParams> pointList;
}
