package com.ay.controller;

import com.ay.model.AyUserDemo;
import com.ay.service.AyUserDemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/user1")
public class AyUserDemoController {

    @Resource

    private AyUserDemoService AyUserDemoService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<AyUserDemo> AyUserDemoList = AyUserDemoService.findAll();
        for (AyUserDemo AyUserDemo : AyUserDemoList) {
            System.out.println("id：" + AyUserDemo.getId());
            System.out.println("name：" + AyUserDemo.getName());
        }
        return "hello";
    }

    @GetMapping("/resultMap")
    public String resultMap() {

        AyUserDemo AyUserDemo2 = new AyUserDemo();
        AyUserDemo2.setName("定当");
        AyUserDemo2.setPassword("123");
        AyUserDemoService.insert_1(AyUserDemo2);
        AyUserDemoService.insert_2(AyUserDemo2);

        List<AyUserDemo> AyUserDemoList = AyUserDemoService.resultMap();
        List<AyUserDemo> AyUserDemoList2 = AyUserDemoService.findAll();
        List<AyUserDemo> AyUserDemoList3 = AyUserDemoService.findByNameAndPassword("阿兰", "123");
        List<AyUserDemo> AyUserDemoList4 = AyUserDemoService.findAll3();

        for (AyUserDemo AyUserDemo : AyUserDemoList) {
            System.out.println("id：" + AyUserDemo.getId());
            System.out.println("name：" + AyUserDemo.getName());
            System.out.println("password：" + AyUserDemo.getPassword());
        }
        System.out.println("...............................................................");
        for (AyUserDemo AyUserDemo : AyUserDemoList2) {
            System.out.println("id：" + AyUserDemo.getId());
            System.out.println("name：" + AyUserDemo.getName());
            System.out.println("password：" + AyUserDemo.getPassword());
        }
        System.out.println("...............................................................");
        for (AyUserDemo AyUserDemo : AyUserDemoList3) {
            System.out.println("id：" + AyUserDemo.getId());
            System.out.println("name：" + AyUserDemo.getName());
            System.out.println("password：" + AyUserDemo.getPassword());
        }

        System.out.println("...............................................................");
        for (AyUserDemo AyUserDemo : AyUserDemoList4) {
            System.out.println("id：" + AyUserDemo.getId());
            System.out.println("name：" + AyUserDemo.getName());
            System.out.println("password：" + AyUserDemo.getPassword());
        }

        return "hello";
    }
}
