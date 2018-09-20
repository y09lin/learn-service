package com.hui.day.learn.controller;

import com.hui.day.learn.controller.params.VoaBean;
import com.hui.day.learn.response.RestResponse;
import com.hui.day.learn.utils.VOAUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huim_lin
 * */

@Api(description = "调戏接口")
@RestController
@RequestMapping(path = "/test")
public class TestController {

    @ApiOperation("Just test")
    @GetMapping("/hello")
    public String test(){
        return "2333";
    }

    @ApiOperation("获取voa")
    @PostMapping("/getVOA")
    public RestResponse<VoaBean> getVoaBean(String text){
        return RestResponse.ok(VOAUtils.getVOA(text));
    }
}
