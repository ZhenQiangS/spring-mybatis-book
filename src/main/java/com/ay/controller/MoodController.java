package com.ay.controller;

import com.ay.dto.MoodDTO;
import com.ay.service.MoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/mood")
public class MoodController {

    @Resource
    private MoodService moodService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<MoodDTO> list = moodService.findAll();
        model.addAttribute("moods", list);
        return "mood";
    }

    @GetMapping("/{moodId}/praise")
    public String praise(Model model, @PathVariable(value = "moodId") String moodId, @RequestParam(value = "userId") String userId) {
        boolean isPraise = moodService.praiseMood(userId, moodId);

        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods", moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";
    }

    @GetMapping("/{moodId}/praise4Redis")
    public String PraiseForRedis(Model model, @PathVariable("moodId") String moodId,
                                 @RequestParam("userId") String userId) {

        //为了方便使用  随机生成用户 ID
        Random random = new Random();
        userId = String.valueOf(random.nextInt(100));
        boolean isParise = moodService.praiseMood4Redis(userId, moodId);

        List<MoodDTO> moodDTOS = moodService.findAll4Redis();
        model.addAttribute("moods", moodDTOS);
        model.addAttribute("isPraise", isParise);

        return "mood";

    }

    @GetMapping("/{moodId}/praise4RedisAsync")
    public String PraiseForRedisAsync(Model model, @PathVariable("moodId") String moodId,
                                      @RequestParam("userId") String userId) {

        //为了方便使用  随机生成用户 ID
        Random random = new Random();
        userId = String.valueOf(random.nextInt(100));
        boolean isParise = moodService.praiseMood4RedisAsync(userId, moodId);

        List<MoodDTO> moodDTOS = moodService.findAll4Redis();
        model.addAttribute("moods", moodDTOS);
        model.addAttribute("isPraise", isParise);

        return "mood";

    }
}
