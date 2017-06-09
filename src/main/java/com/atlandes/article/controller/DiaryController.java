package com.atlandes.article.controller;

import com.atlandes.article.po.Diary;
import com.atlandes.article.service.DiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/8/008.
 */
@Controller
@RequestMapping("/diary")
public class DiaryController {
    @Resource
    private
    DiaryService diaryService;

    @RequestMapping("/upload")
    @ResponseBody
    public String uploadDiary (Diary diary){
        diaryService.insert(diary);
        return "upload completed";
    }

    @RequestMapping("/check")
    @ResponseBody
    public String selectDiary(String title){
        return diaryService.select(title);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Boolean updateDiary(Diary diary){
        return diaryService.update(diary);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Boolean deleteDiary(Diary diary){
        return diaryService.delete(diary);
    }
}
