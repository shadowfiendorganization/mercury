package com.atlandes.article.service;

import com.atlandes.article.dao.DiaryMapper;
import com.atlandes.article.po.Diary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/8/008.
 */
@Service
public class DiaryService {

    @Resource
    private
    DiaryMapper diaryMapper;

    public String insert(Diary diary) {
        return String.valueOf(diaryMapper.insert(diary));
    }

    public String select(String title) {
        Diary diary = diaryMapper.selectByTitle(title);
        return diary.getContent();
    }

    public Boolean update(Diary diary) {
        return diaryMapper.update(diary);
    }

    public Boolean delete(Diary diary) {
        return diaryMapper.delete(diary);
    }
}
