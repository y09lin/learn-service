package com.hui.day.learn.controller.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description ：分页获取单词列表参数
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */
@Data
public class WordBookParams {
    @NotNull
    @ApiModelProperty("单词本id")
    private Long bookId;

    @ApiModelProperty("排序字段，\n" +
            "* 1 - 按单词排序\n" +
            "* 2 - 按添加时间排序\n" +
            "* 3 - 按熟练程度排序")
    private List<Integer> orderList;

    @ApiModelProperty("1升序，2降序")
    private List<Integer> sortList;

    @ApiModelProperty("页码，从0开始，默认为0")
    private Integer pageIndex;

    @ApiModelProperty("每页多少个单词，默认为20")
    private Integer pageSize;
}
