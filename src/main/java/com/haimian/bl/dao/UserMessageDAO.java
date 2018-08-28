package com.haimian.bl.dao;

import com.haimian.bl.bean.UserBean;

import java.io.IOException;

public interface UserMessageDAO {
    void insertUserMessage(UserBean userBean) throws IOException;
}
