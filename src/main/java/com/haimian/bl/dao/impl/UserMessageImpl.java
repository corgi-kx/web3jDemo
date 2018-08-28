package com.haimian.bl.dao.impl;

import com.haimian.bl.bean.UserBean;
import com.haimian.bl.dao.UserMessageDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


public class UserMessageImpl implements UserMessageDAO {
    private SqlSession sqlSession;

    @Override
    public void insertUserMessage(UserBean userBean)  {
        try {
            //读取主配置文件
            InputStream input = Resources.getResourceAsStream("mybatis.xml");
            //创建SqlSessionFactory对象
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
            //创建SqlSession对象,如果openSession()括号里写true会自动提交事务
            sqlSession = sessionFactory.openSession();
            //新增数据操作
            sqlSession.insert("insertUserMessage", userBean);
            //提交SqlSession
            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
