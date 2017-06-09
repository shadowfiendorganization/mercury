package com.atlandes.article.dao;

import com.atlandes.article.po.Diary;

/**
 * Created by Administrator on 2017/6/8/008.
 */
public interface DiaryMapper {

    int insert(Diary diary);

    Diary selectByTitle(String title);

    Boolean update(Diary diary);

    Boolean delete(Diary diary);
}
