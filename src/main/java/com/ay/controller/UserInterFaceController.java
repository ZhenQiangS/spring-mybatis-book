package com.ay.controller;

import com.ay.dao.AyUserDao;
import com.ay.dto.UserDTO;
import com.ay.model.AyUser;
import com.ay.service.AyUserService;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.BinaryClient;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/userInterface")
public class UserInterFaceController {

    @Resource
    private AyUserService ayUserService;

    @GetMapping("/allUser")
    public List<AyUser> getAllUser() {
        return ayUserService.findAllUser();
    }

    @PostMapping("/addUser")
    public HashMap addUser(@RequestBody AyUser ayUser) {
        int result = ayUserService.insert(ayUser);
        HashMap message = new HashMap();

        if (result > 0) message.put("result", "true");
        else
            message.put("result", "false");

        return message;
    }

    @PostMapping("/updateUser")
    public HashMap updateUser(AyUser ayUser) {
        int result = ayUserService.updateUser(ayUser);
        HashMap message = new HashMap();

        if (result > 0)
            message.put("result", "true");
        else
            message.put("result", "false");

        return message;
    }

    @DeleteMapping("/delete/{id}")
    public HashMap delete(@PathVariable("id") String id) {
        int result = ayUserService.deleteUser(id);
        HashMap message = new HashMap();

        if (result > 0)
            message.put("result", "true");
        else
            message.put("result", "false");

        return message;
    }

    @GetMapping("/findUser/{id}")
    public HashMap findUser(@PathVariable("id") String id) {
        AyUser user = ayUserService.findById(id);
        HashMap message = new HashMap();

        if (user.getId() > 0) {
            message.put("result", "true");
            message.put("data", user);
        } else
            message.put("result", "false");

        return message;
    }

}
