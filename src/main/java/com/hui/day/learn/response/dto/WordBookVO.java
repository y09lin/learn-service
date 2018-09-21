package com.hui.day.learn.response.dto;

import com.hui.day.learn.domain.TbWordBook;
import com.hui.day.learn.utils.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：单词本VO
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/21
 */
@Data
public class WordBookVO {
    @ApiModelProperty("单词本id")
    private Long bookId;

    @ApiModelProperty("单词本名称")
    private String bookName;

    @ApiModelProperty("单词本创建时间，格式：yyyy-MM-dd hh:mm")
    private String createTime;

    public static WordBookVO getFromTb(TbWordBook book){
        if (book == null){
            return null;
        }
        WordBookVO vo = new WordBookVO();
        vo.setBookId(book.getBookId());
        vo.setBookName(book.getBookName());
        vo.setCreateTime(DateUtil.toString(book.getCreateTime(),DateUtil.FORMAT_DATE_TIME_YMDHM));
        return vo;
    }

    public static List<WordBookVO> getFromTb(List<TbWordBook> bookList){
        List<WordBookVO> voList = new ArrayList<>();
        if (bookList!=null && bookList.size()>0){
            for (TbWordBook book:bookList){
                voList.add(getFromTb(book));
            }
        }
        return voList;
    }
}
