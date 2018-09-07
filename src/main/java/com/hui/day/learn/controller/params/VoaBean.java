package com.hui.day.learn.controller.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class VoaBean {

    @ApiModelProperty("段落")
    private List<ParagraphParams> pList;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("时间")
    private String date;
}
