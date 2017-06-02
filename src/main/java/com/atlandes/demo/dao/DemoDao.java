package com.atlandes.demo.dao;

import com.atlandes.demo.po.Demo;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XD.Wang on 2017/5/23.
 * 示例DAO
 */
@Repository
public class DemoDao extends BaseDao {

    private static Logger log = LoggerFactory.getLogger(DemoDao.class);

    public List<Demo> getDemoList() {
        SqlSession session = factory.openSession();
        List<Demo> demos = session.selectList("com.atlandes.demo.getDemoList");
        session.close();
        return demos;
    }

}
