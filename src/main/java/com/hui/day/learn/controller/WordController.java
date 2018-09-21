package com.hui.day.learn.controller;

import com.hui.day.learn.response.RestResponse;
import com.hui.day.learn.response.codes.English2Code;
import com.hui.day.learn.response.dto.WordBookVO;
import com.hui.day.learn.response.dto.WordVO;
import com.hui.day.learn.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description ：
 *
 * @author ：huim_lin.
 * @date ：Created in 2018/9/20
 */
@Api(description = "单词相关接口")
@RestController
@RequestMapping(path = "/word",headers = BaseController.TOKEN)
public class WordController extends BaseController {
    @Autowired
    private WordService wordService;

    @ApiOperation("单词翻译")
    @GetMapping("/translate")
    public RestResponse<WordVO> translate(
            @ApiParam("要翻译的单词")@RequestParam("word") String word){
        WordVO vo = wordService.translate(word);
        if (vo == null){
            return RestResponse.exception(English2Code.TRANSLATE_ERROR);
        }
        return RestResponse.ok(vo);
    }

    @ApiOperation("新建单词本")
    @PostMapping("/createBook")
    public RestResponse<WordBookVO> createBook(
            @ApiParam("单词本名称")@RequestParam("bookName") String bookName){
        long userId = 1L;
        return RestResponse.ok(wordService.createBook(userId,bookName));
    }

    @ApiOperation("获取单词本列表")
    @GetMapping("/books")
    public RestResponse<List<WordBookVO>> getBooks(){
        long userId = 1L;
        return RestResponse.ok(wordService.getWordBook(userId));
    }

    @ApiOperation("获取单词本")
    @GetMapping("/book/{bookId}")
    public RestResponse<WordBookVO> getBook(
            @ApiParam("单词本id") @PathVariable("bookId") Long bookId){
        long userId = 1L;
        return RestResponse.ok(wordService.getWordBook(userId,bookId));
    }
}
