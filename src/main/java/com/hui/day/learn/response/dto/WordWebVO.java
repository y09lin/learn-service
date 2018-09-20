package com.hui.day.learn.response.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description ：单词网络意思
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/20
 */
@Data
public class WordWebVO {
    @ApiModelProperty("网络词")
    private String key;

    @ApiModelProperty("网络意思")
    private List<String> value;
}
