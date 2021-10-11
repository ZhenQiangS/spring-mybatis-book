package com.ay.controller;

import com.ay.annotation.CSRFToke;
import com.ay.enums.CSRFTokenBehavior;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attack")
public class AttackController {

    @GetMapping("/xssView")
    public String xss() {
        String sql = "1' or '1'='1";
        System.out.println("防SQL注入:" + StringEscapeUtils.escapeSql(sql));//防SQL注入  
        System.out.println("转义HTML,注意汉字:" + StringEscapeUtils.escapeHtml("<font>chen磊  xing</font>")); //转义HTML,注意汉字  
        System.out.println("反转义HTML:" + StringEscapeUtils.unescapeHtml("<font>chen磊  xing</font>")); //反转义HTML  

        System.out.println("转成Unicode编码：" + StringEscapeUtils.escapeJava("陈磊兴")); //转义成Unicode编码  

        System.out.println("转义XML：" + StringEscapeUtils.escapeXml("<name>陈磊兴</name>")); //转义xml  
        System.out.println("反转义XML：" + StringEscapeUtils.unescapeXml("<name>陈磊兴</name>")); //转义xml
        return "attack/xss";
    }

    @GetMapping("/xssSubmitByGet")
    public String xssGet(Model model,
                         @RequestParam(value = "value") String value) {
        model.addAttribute("xssResult", value);
        return "attack/xssResult";
    }

    @PostMapping("/xssSubmitByPost")
    public String xssPost(Model model,
                          @RequestParam(value = "value") String value) {
        model.addAttribute("xssResult", value);
        return "attack/xssResult";
    }

    @PostMapping("/SubmitByPost")
    public String xssGet2(Model model,
                          @RequestParam(value = "value") String value) {
        model.addAttribute("xssResult", value);
        return "attack/xssResult";
    }

    @CSRFToke(behavior = CSRFTokenBehavior.CREATE)
    @GetMapping("/csrfView")
    public String csrf() {

        return "attack/csrf";
    }

    @CSRFToke(behavior = CSRFTokenBehavior.CHECK)
    @PostMapping("/csrfSubmitByPost")
    public String csrfPost(Model model) {
        model.addAttribute("xssResult", "防CSRF攻击成功");
        return "attack/csrfResult";
    }

    @CSRFToke(behavior = CSRFTokenBehavior.CHECK)
    @PostMapping("/csrfSubmitByPost2")
    public String csrfPost2() {

        return "attack/csrfResult";
    }

}
