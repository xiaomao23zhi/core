package com.github.xiaomao23zhi.core.controller;

import com.github.xiaomao23zhi.core.handler.JSONResponse;
import com.github.xiaomao23zhi.core.handler.JSONResponseBuilder;
import com.github.xiaomao23zhi.core.service.FFmpegService;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomao23zhi
 */
@Api(tags = "JavaCV", value = "bytedeco/javacv")
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class JavaCVController {

    @Autowired
    private final FFmpegService ffmpegService;

    public JavaCVController(FFmpegService FFmpegService) {
        this.ffmpegService = FFmpegService;
    }

    @ApiOperation(value = "")
    @GetMapping(value = "/grab")
    public JSONResponse<String> getHealth(@RequestParam String url, @RequestParam int frames) {

        String result = ffmpegService.grabFrame(url, frames);

        return JSONResponseBuilder.success(result);
    }
}
