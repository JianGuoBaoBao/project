package com.nut.web;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "Hello")
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/")
    @ApiOperation("Hello")
    @ApiImplicitParam
    public String hello(){
        log.info("这是一件测试info的语句");
        log.error("这是一件测试error的语句");
        log.warn("这是一件测试warn的语句");
        return "helloWorld";
    }
}
