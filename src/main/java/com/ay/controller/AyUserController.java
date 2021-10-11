package com.ay.controller;

import com.ay.model.AyUser;
import com.ay.model.AyUser3;
import com.ay.service.AyUserService;
import com.ay.validator.AyUserValidator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
//@SessionAttributes("ayUser")
//@SessionAttributes(types={AyUs.class,AyRole.class},value={"ayUser","ayRole"}) 可以设置多个对象到 HTTPSession 中，types 表示放入 httpSession 当中的对象类型
@RequestMapping(value = "/user2")
public class AyUserController {

    private static Logger logger = Logger.getLogger(AyUserController.class);

    @Resource
    private AyUserValidator ayUserValidator;

    @Resource
    private AyUserService ayUserService;


    // 或者 @RequestMapping("/hello")
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("message", "hello ay");
        return "hello";
    }

    /* 在浏览器中输入以下地址,都可以进入该方法里
     localhost:8080/user
     localhost:8080/user/
     localhost:8080/user/page
     localhost:8080/user/page_abc
     localhost:8080/user/view/
     localhost:8080/user/view/view     */
    @RequestMapping(value = {
            "",
            "/page",
            "page*",
            "view/*,**/msg"
    })
    public String hello1(Model model) {
        model.addAttribute("message", "多个地址可以进入这个方法里");
        return "hello";
    }

    // 只有 http 的 get请求方法 才能访问。 也可以使用 @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    String get() {
        return "hello from get";
    }


    // 只有 http 的 delete请求方法 才能访问。 也可以使用 @DeleteMapping
    @RequestMapping(method = RequestMethod.DELETE)
    String delete() {
        return "hello form delete";
    }

    // 只有 http 的 post请求方法 才能访问。 也可以使用 @PostMapping
    @RequestMapping(method = RequestMethod.POST)
    String post() {
        return "hello from post";
    }

    // 只有 http 的 put 请求方法 才能访问。 也可以使用 @PutMapping
    @RequestMapping(method = RequestMethod.PUT)
    String put() {
        return "hello from put";
    }

    // 只有 http 的 patch请求方法 才能访问。 也可以使用 @PatchMapping
    @RequestMapping(method = RequestMethod.PATCH)
    String patch() {
        return "hello from patch";
    }

    //在请求头中的 Accept(返回类型:表示该http请求,能处理的数据类型) 头中必须包含 "application/json"才能进入该方法

    @RequestMapping(value = "/produces", produces = {"application/json"})
    String getProduces() {
        return "produces attribute";
    }

    //请求的内容必须是 "application/json"或者"application/xml"
    @RequestMapping(value = "/consumes", consumes = {
            "application/json",
            "application/xml"
    })
    String getConsumes() {
        return "consumes attribute";
    }

    //  目的:缩小请求映射的范围; headers:该属性指定Request 中必须包含某些指定的header 值,才能让该方法处理请求
    @RequestMapping(value = "/head", headers = {
            "content-type=text/plain",
            "content-type=text/html"
    })
    String post2() {
        return "mapping applied along with heades";
    }

    // 当personId = 10 才会进入该方法
    @RequestMapping(value = "/fetch", params = {
            "personId=10"
    })
    public String getParams(@RequestParam("personId") String id) {
        System.out.println(" fetch params using params attribute =" + id);
        return "hello";
    }


    @GetMapping("/findById/{id}")
    public String findById(@PathVariable String id) {
        System.out.println("在浏览器中输入URL：http:localhost:8080/user/findById/1时，方法中的id =1");
        return "hello";
    }

    @PostMapping("/add")
    public String add(@RequestBody AyUser ayUser) {
        return "只有 post 方法 才能进入此方法";
    }

    @ModelAttribute
    public void redirectTest(Model model) {
        model.addAttribute("name", "ay");
    }

    @GetMapping("hello4")
    public String hello(Model model, ModelMap modelMap, Map map) {
        System.out.println("因为redirectTest 方法有@ModelAttribute 注释 所以先执行redirectTest 方法；" +
                "设置的name属性的值为'ay' 在当前方法中的 model,modelMap,map 都能读取到它（这三个对象都是指向一个对象）");
        return "hello";
    }

    //等价于 ModelAndView3 方法的实现
    @RequestMapping("ModelAndView")
    public ModelAndView ModelAndView2() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "ay");
        mv.setViewName("hello");
        return mv;
    }

    //等价于 ModelAndView2 方法的实现
    @GetMapping("ModelAndView2")
    public String ModelAndView3(Model model) {
        model.addAttribute("name", "ay");
        return "hello";
    }

    // 看方法接收的参数
    @GetMapping("HttpServletRequest")
    public ModelAndView hello3(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "ay");
        mv.setViewName("hello");
        return mv;
    }

    /*    当浏览器输入localhost:8080/user/findByNameAndPassword？name=小米&password=666时
        形参 自动接收对应的值*/
    @GetMapping("/findByNameAndPassword")
    public String findByNameAndPassword(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "password", required = false, defaultValue = "123") String password) {
        System.out.println("name = " + name);
        System.out.println("password = " + password);
        return "success";
    }

    /*当在浏览器中输入 http:localhost:8080/user/owners/123/pets/456 时 ，自动会把值绑定到@PathVariable
    注解的同名上*/
    @GetMapping(value = "/owners/{ownerId}/pets/{petId}")
    public String findPet(@PathVariable Long ownerId, @PathVariable Long petId) {
        System.out.println("ownerId：" + ownerId);
        System.out.println("ownerId：" + petId);
        return "hello";
    }


    /*因为Accept—Encoding是数组类型，所有encoding 和 accept 都是数组类型*/
    @GetMapping("/requestHeader")
    public String handle(
            @RequestHeader("Accept-Encoding") String[] encoding,
            @RequestHeader("Accept") String[] accept) {
        for (String item : encoding) {
            System.out.println(item);
        }

        for (String item : encoding) {
            System.out.println(item);
        }
        return "hello";
    }

    // 获取请求中的 cookie信息
    @RequestMapping("/cookieValue")
    public String handle(@CookieValue("JSESSIONID") String cookie) {
        System.out.println(cookie);
        return "hello";
    }

    //只要有 @ModelAttribute 注解 就先执行有该注释的方法，页面表达：${user.name}
    @ModelAttribute
    public void init(Model model) {
        AyUser ayUser = new AyUser();
        ayUser.setId(1);
        ayUser.setName("ay");
        model.addAttribute("user", ayUser);
        System.out.println("给Model 添加值");
    }

    //只要有 @ModelAttribute 注解 就先执行有该注释的方法
    @ModelAttribute
    public void init02() {
        System.out.println("init2 ...");
    }

    // 输入 http://localhost:8080/user/getName?name=22 时 页面 会把22 显示出来,页面表达：${name}
    @ModelAttribute("getName")
    public String init3(@RequestParam(value = "name", required = false) String name) {
        return name;
    }

    @RequestMapping("/getName")
    public String getName() {
        return "hello";
    }


    //    这两个注释同时使用时，类不能使用RequestMapping 注释
    @ModelAttribute("name21")
    @RequestMapping("/hello")
    public String hello() {
        return "name2";
    }

    //优先执行带有ModelAttribute注释的方法。 浏览器中输入 http:localhost:8080/****?id=32&name=haha，时 页面会显示id 和 name
    @ModelAttribute("ayUser")
    public AyUser init(@RequestParam(value = "id", required = false) Integer id,
                       @RequestParam(value = "name", required = false) String name) {
        AyUser ayUser = new AyUser();
        ayUser.setId(id);
        ayUser.setName(name);
        return ayUser;

    }

    // Model 的生命周期只存在于 HTTP 请求的处理过程中，要达到 参数在多个请求中共享则：
    // 进入该方法后，ayUser 实例 被放入Model中，因为该类有SessionAttributes 注释，所以 也会把ayUser 放入 到HTTPSession作用域中
    // 执行后会重定向到 hello5方法中，就可以获取 ayUser 对象了
    @RequestMapping("redirect")
    public String redirectTest2(Model model) {
        AyUser ayUser = new AyUser();
        ayUser.setName("wo");
        model.addAttribute("ayUser", ayUser);
        return "redirect:hello5";
    }

    //在参数中用 ModelMap 接收 model
    @RequestMapping("hello5")
    public String hello5(ModelMap modelMap) {
        AyUser ayUser = (AyUser) modelMap.get("ayUser");
        System.out.println(ayUser.getName());
        return "hello";
    }

    // Model 的生命周期只存在于 HTTP 请求的处理过程中，要达到 参数在多个请求中共享则：
    @RequestMapping("redirect3")
    public String redirect3(Model model) {
        AyUser ayUser = new AyUser();
        ayUser.setName("wo");
        model.addAttribute("ayUser", ayUser);
        return "redirect:hello6";
    }

    // 用SessionAttribute 来指定接收某个model属性的值
    @RequestMapping("hello6")
    public String hello6(@SessionAttribute("ayUser") AyUser ayUser) {
        System.out.println(ayUser.getName());
        return "hello";
    }

    // sessionStatus.setComplete 后，下次会话就没值了
    @RequestMapping("hello7")
    public String hello7(Model model, SessionStatus sessionStatus) {
        System.out.println("肯：删除 HTTPSession中的属性");
        sessionStatus.setComplete();
        return "hello";
    }

    @RequestMapping("hello8")
    public String hello8(ModelMap modelMap) {
        AyUser ayUser = (AyUser) modelMap.get("ayUser");
        System.out.println("测试值:" + ayUser.getName());
        return "hello";
    }

    @RequestMapping("/responseBody")
    @ResponseBody
    public String hello9() {
        return "在浏览器中输入http:localhost:8080/user/responseBody，方法返回的不是视图，而是把字符串 直接写入HTTP响应正文中，返回给浏览器。";
    }

    @RequestMapping("/responseBody2")
    @ResponseBody
    public List<String> hello11() {
        List<String> list = new ArrayList<String>();
        list.add("ay");
        list.add("al");
        list.add("把json 字符串 直接写入HTTP 响应正文中，返回给浏览器。");
        return list;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/submit")
    @ResponseBody
    public void submit(@RequestBody AyUser ayUser) {
        System.out.println("name：" + ayUser.getName());
        System.out.println("id：" + ayUser.getId());
    }

    @RequestMapping("/save")
    public String save() {
        return "saveUser";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute AyUser ayuser, Model model, Errors errors) {

        ayUserValidator.validate(ayuser, errors);
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "error";
        }
        int count = ayUserService.insert(ayuser);
        return "success";

    }

    @RequestMapping("/save2")
    public String save2() {
        return "saveUser2";
    }

    @PostMapping("/insert2")
    public String insert2(@Valid AyUser3 ayUser2, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result);
            return "error";
        }
        return "success";
    }


    @GetMapping("/xml")
    public String xml() {
        return "index";
    }


    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("url", "/userInterface/addUser");
        model.addAttribute("method", "post");
        model.addAttribute("user", new AyUser());
        model.addAttribute("buttonStr", "修改");

        return "curd/addOrUpdateUser";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(Model model, @PathVariable("id") String id) {
        model.addAttribute("url", "/userInterface/updateUser");
        model.addAttribute("method", "post");

        AyUser ayUser = ayUserService.findById(id);
        model.addAttribute("user", ayUser);
        model.addAttribute("buttonStr", "修改");
        return "curd/addOrUpdateUser";
    }

    @GetMapping("/userList")
    public String userList() {
        return "/curd/userList";
    }
}

