package com.hui.day.learn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hui.day.learn.controller.params.WordBookParams;
import com.hui.day.learn.dao.WordDao;
import com.hui.day.learn.domain.TbUserWord;
import com.hui.day.learn.domain.TbWord;
import com.hui.day.learn.domain.TbWordBook;
import com.hui.day.learn.repository.UserWordRepository;
import com.hui.day.learn.repository.WordBookRepository;
import com.hui.day.learn.repository.WordRepository;
import com.hui.day.learn.response.codes.English2Code;
import com.hui.day.learn.response.dto.PageDto;
import com.hui.day.learn.response.dto.WordBookVO;
import com.hui.day.learn.response.dto.WordVO;
import com.hui.day.learn.response.exception.GlobalVerifyException;
import com.hui.day.learn.service.WordService;
import com.hui.day.learn.utils.Constans;
import com.hui.day.learn.utils.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private WordBookRepository bookRepository;

    @Autowired
    private UserWordRepository userWordRepository;

    @Autowired
    private WordDao wordDao;

    @Override
    public WordVO translate(String word) {
        if (StringUtils.isBlank(word)){
            return null;
        }
        log.debug("单词翻译：{}",word);
        TbWord tb = wordRepository.findByWord(word);
        WordVO vo;
        if (tb == null){
            tb = getFromNetwork(word);
            vo = WordVO.getFromTb(tb);
        }else {
            vo = WordVO.getFromTb(tb);
        }
        return vo;
    }

    private TbWord getFromNetwork(String word){
        String url = "http://fanyi.youdao.com/openapi.do?keyfrom=TranslateToastApp&key=1264267832" +
                "&type=data&doctype=json&version=1.1&q="+word;
        String translation;
        try {
            translation = OkHttpUtil.requestGet(url);
        } catch (IOException e) {
            log.info("translate exception, {}", (Object[]) e.getStackTrace());
            return null;
        }
        if (StringUtils.isBlank(translation)){
            return null;
        }

        JSONObject json = JSON.parseObject(translation);
        if (json.getInteger(Constans.WORD_ERROR_CODE) != 0){
            return null;
        }
        TbWord tb = new TbWord();
        tb.setWord(word);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WordBookVO createBook(long userId, String bookName) {
        TbWordBook book = new TbWordBook();
        book.setUserId(userId);
        book.setBookName(bookName);
        book.setCreateTime(new Date());
        return WordBookVO.getFromTb(bookRepository.save(book));
    }

    @Override
    public WordBookVO getWordBook(long userId, long bookId) {
        return WordBookVO.getFromTb(wordDao.getWordBook(userId, bookId));
    }

    @Override
    public List<WordBookVO> getWordBook(long userId) {
        return WordBookVO.getFromTb(wordDao.getWordBook(userId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WordVO addWord(long userId, String word, long bookId) {
        if (!wordDao.hasBook(userId, bookId)){
            throw new GlobalVerifyException(English2Code.WORD_BOOK_NOT_EXIST);
        }
        TbWord tb = wordRepository.findByWord(word);
        if (tb == null){
            tb = getFromNetwork(word);
            if (tb == null) {
                throw new GlobalVerifyException(English2Code.TRANSLATE_ERROR);
            }
        }
        TbUserWord uw = new TbUserWord();
        uw.setUserId(userId);
        uw.setWordId(tb.getWordId());
        uw.setBookId(bookId);
        uw.setGraspLevel(0);
        uw.setAddTime(new Date());
        userWordRepository.save(uw);
        return WordVO.getFromTb(tb);
    }

    @Override
    public PageDto<WordVO> getWords(long userId, WordBookParams params) {
        Page<TbWord> wordPage = wordDao.getWords(userId,params.getBookId(),params.getOrderList(),
                params.getSortList(),params.getPageIndex(),params.getPageSize());
        return new PageDto<>(params.getPageIndex(), params.getPageSize(),
                wordPage.getTotalElements(), wordPage.getTotalPages(),
                WordVO.getFromTb(wordPage.getContent()));
    }
}
