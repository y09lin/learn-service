package com.hui.day.learn.controller;

import com.hui.day.learn.response.RestResponse;
import com.hui.day.learn.response.codes.English2Code;
import com.hui.day.learn.response.dto.WordVO;
import com.hui.day.learn.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public RestResponse<WordVO> translate(@RequestParam("word") String word){
        WordVO vo = wordService.translate(word);
        if (vo == null){
            return RestResponse.exception(English2Code.TRANSLATE_ERROR);
        }
        return RestResponse.ok(vo);
    }
}
