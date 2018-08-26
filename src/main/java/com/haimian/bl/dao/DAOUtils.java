package com.haimian.bl.dao;

import com.haimian.bl.JDBCUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUtils {
    public static boolean executeSql(String sql, List<Object> paratmeters)  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object object : paratmeters) {
                preparedStatement.setObject(i, object);
                i ++;
            }
            return 1 == preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet selectSql(String sql, List<Object> paratmeters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            for (Object object : paratmeters) {
                preparedStatement.setObject(i , object);
                i ++;
            }
            return resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Object> getList (Object... objects){
        List<Object> objectList = new ArrayList<>();
        for ( Object object : objects ) {
            objectList.add(object);
        }
        return objectList;
    }
}
