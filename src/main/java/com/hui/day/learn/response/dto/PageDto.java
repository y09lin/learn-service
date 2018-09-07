package com.hui.day.learn.response.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wahaha
 * */
@Data
public class PageDto<T> {
    @ApiModelProperty("当前页码")
    private int pageIndex;

    @ApiModelProperty("每页多少条")
    private int pageSize;

    @ApiModelProperty("总纪录数")
    private long totalElement;

    @ApiModelProperty("总页数")
    private long totalPage;

    @ApiModelProperty("返回的列表数据")
    private List<T> dataList;

    public PageDto(){}

    public PageDto(int pageIndex, int pageSize, long totalElement, long totalPage,List<T> dataList) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalElement = totalElement;
        this.totalPage = totalPage;
        this.dataList     = dataList;
    }
}
