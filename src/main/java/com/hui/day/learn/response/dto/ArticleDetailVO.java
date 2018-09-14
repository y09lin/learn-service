package com.hui.day.learn.response.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description ：文章详情
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/14
 */
@Data
public class ArticleDetailVO extends ArticleVO {
    @ApiModelProperty("文章对应的MP3地址")
    private String mp3;

    @ApiModelProperty("段落列表")
    private List<ParagraphVO> paragraphList;
}
