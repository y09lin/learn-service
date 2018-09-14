package com.hui.day.learn.controller.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description ：音视频时间节点
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/14
 */
@Data
public class PointParams {
    @NotBlank
    @ApiModelProperty("句子id，不能为空")
    private Long sentenceId;

    @NotBlank
    @ApiModelProperty("开始时间点，单位秒，不能为空")
    private Integer beginPoint;

    @NotBlank
    @ApiModelProperty("结束时间点，单位秒，不能为空")
    private Integer endPoint;
}
