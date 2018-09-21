package com.hui.day.learn.response.dto;

import com.alibaba.fastjson.JSON;
import com.hui.day.learn.domain.TbWord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

/**
 * description ：单词
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/20
 */
@Data
public class WordVO {
    @ApiModelProperty("单词id")
    private Long wordId;

    @ApiModelProperty("单词")
    private String word;

    @ApiModelProperty("意思")
    private List<String> translation;

    @ApiModelProperty("发音")
    private String phonetic;

    @ApiModelProperty("美式发音")
    private String usPhonetic;

    @ApiModelProperty("英式发音")
    private String ukPhonetic;

    @ApiModelProperty("翻译")
    private List<String> explains;

    @ApiModelProperty("网络意思")
    private List<WordWebVO> web;

    public static WordVO getFromTb(TbWord word){
        if (word == null){
            return null;
        }
        WordVO vo = new WordVO();
        vo.setWordId(word.getWordId());
        vo.setWord(word.getWord());
        vo.setTranslation(JSON.parseArray(word.getTranslation(),String.class));
        vo.setPhonetic(word.getPhonetic());
        vo.setUsPhonetic(word.getUsPhonetic());
        vo.setUkPhonetic(word.getUkPhonetic());
        vo.setExplains(JSON.parseArray(word.getExplains(),String.class));
        vo.setWeb(JSON.parseArray(word.getWeb(),WordWebVO.class));
        return vo;
    }

    public static List<WordVO> getFromTb(List<TbWord> words){
        List<WordVO> voList = new ArrayList<>();
        if (words!=null && words.size()>0){
            for (TbWord w:words){
                voList.add(getFromTb(w));
            }
        }
        return voList;
    }
}
