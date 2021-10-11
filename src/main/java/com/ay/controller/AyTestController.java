package com.ay.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *@author Ay
 * @date 2020/02/11
 **/
@Controller
@RequestMapping("/test")
public class AyTestController {

    //  @GetMapping注解：是 @RequestMapping(method = RequestMethod.GET) 的缩写
    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/h")
    public String h() {
        return "hello";
    }
}

