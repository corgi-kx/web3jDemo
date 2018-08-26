package com.haimian.bl.service;

import com.haimian.bl.bean.UserBean;
import com.haimian.bl.dao.DAOUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMessageService {

    public UserBean select(String id_card) throws SQLException {
        ResultSet resultSet = null;
        resultSet = DAOUtils.selectSql("select * from user_message where id_card = ?", DAOUtils.getList(id_card));
        UserBean userBean = new UserBean();
        while (resultSet.next()) {
            userBean.setUser_index(resultSet.getInt(1));
            userBean.setId_card(resultSet.getString(2));
            userBean.setPassword(resultSet.getString(3));
            userBean.setStates(resultSet.getInt(4));
            userBean.setEth_address(resultSet.getString(5));
            userBean.setEth_private_key(resultSet.getString(6));
            userBean.setEth_public_key(resultSet.getString(7));
            userBean.setEth_key_store(resultSet.getString(8));
            userBean.setScore(resultSet.getInt(9));
        }
        return  userBean;
    }

    public boolean insert(UserBean userBean) {
        String sql = "insert into user_message (id_card, passworld, eth_address, eth_private_key, eth_public_key, eth_key_store) values(?, ?, ?, ?, ?, ?)";
        return   DAOUtils.executeSql(sql, DAOUtils.getList(userBean.getId_card(), userBean.getPassword(),
                userBean.getEth_address(), userBean.getEth_private_key(),
                userBean.getEth_public_key(), userBean.getEth_key_store()));

    }

    public boolean update(int score, String id_card ) {
        String sql = "update user_message set score = ? where id_card = ?";
        return DAOUtils.executeSql(sql, DAOUtils.getList(score, id_card));
    }
}
