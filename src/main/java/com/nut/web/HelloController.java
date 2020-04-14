package com.nut.web;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//热部署
@Slf4j
@RestController
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        log.info("这是一件测试info的语句");
        log.error("这是一件测试error的语句");
        log.warn("这是一件测试warn的语句");
        return "helloWorld";
    }
}
