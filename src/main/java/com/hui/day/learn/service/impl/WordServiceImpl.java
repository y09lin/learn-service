package com.hui.day.learn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hui.day.learn.domain.TbWord;
import com.hui.day.learn.repository.WordRepository;
import com.hui.day.learn.response.dto.WordVO;
import com.hui.day.learn.service.WordService;
import com.hui.day.learn.utils.Constans;
import com.hui.day.learn.utils.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * description ：
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/20
 */
@Slf4j
@Service("wordService")
public class WordServiceImpl implements WordService {
    @Autowired
    private WordRepository wordRepository;

    @Override
    public WordVO translate(String word) {
        if (StringUtils.isBlank(word)){
            return null;
        }
        log.debug("单词翻译：{}",word);
        TbWord tb = wordRepository.findByWord(word);
        WordVO vo;
        if (tb == null){
            String url = "http://fanyi.youdao.com/openapi.do?keyfrom=TranslateToastApp&key=1264267832" +
                    "&type=data&doctype=json&version=1.1&q="+word;
            try {
                String translation = OkHttpUtil.requestGet(url);
                if (StringUtils.isNotBlank(translation)){
                    tb = getFromNetwork(word,translation);
                    vo = WordVO.getFromTb(tb);
                }else {
                    return null;
                }
            } catch (IOException e) {
                log.info("translate exception, {}", (Object[]) e.getStackTrace());
                return null;
            }
        }else {
            vo = WordVO.getFromTb(tb);
        }
        return vo;
    }

    private TbWord getFromNetwork(String word,String translation){
        TbWord tb = new TbWord();
        tb.setWord(word);
        JSONObject json = JSON.parseObject(translation);
        if (json.getInteger(Constans.WORD_ERROR_CODE) != 0){
            return null;
        }
        if (json.get(Constans.WORD_TRANSLATION) !=null){
            tb.setTranslation(json.get(Constans.WORD_TRANSLATION).toString());
        }else {
            tb.setTranslation("");
        }
        if (json.get(Constans.WORD_BASIC) != null){
            JSONObject basic = (JSONObject) json.get(Constans.WORD_BASIC);
            if (basic.getString(Constans.WORD_PHONETIC) != null){
                tb.setPhonetic(basic.getString(Constans.WORD_PHONETIC));
            }else {
                tb.setPhonetic("");
            }
            if (basic.getString(Constans.WORD_PHONETIC_US) != null){
                tb.setUsPhonetic(basic.getString(Constans.WORD_PHONETIC_US));
            }else {
                tb.setUsPhonetic("");
            }
            if (basic.getString(Constans.WORD_PHONETIC_UK) != null){
                tb.setUkPhonetic(basic.getString(Constans.WORD_PHONETIC_UK));
            }else {
                tb.setUkPhonetic("");
            }
            if (basic.get(Constans.WORD_EXPLAINS) != null){
                tb.setExplains(basic.get(Constans.WORD_EXPLAINS).toString());
            }else {
                tb.setExplains("");
            }
        }else {
            tb.setPhonetic("");
            tb.setUsPhonetic("");
            tb.setUkPhonetic("");
            tb.setExplains("");
        }
        if (json.get(Constans.WORD_WEB) != null){
            tb.setWeb(json.get(Constans.WORD_WEB).toString());
        }else {
            tb.setWeb("");
        }
        return wordRepository.save(tb);
    }
}
