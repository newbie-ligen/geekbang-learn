package com.sqz.demo;

import java.sql.*;

public class JdbcBatchCRUD {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Connection  connection = DriverManager.getConnection("jdbc:mysql://192.168.188.200:3306/test_order?characterEncoding=utf-8&rewriteBatchedStatements=true", "root", "Root@123456");
        return connection;
    }



    public static void save() {
        try {
            Connection connection = getConnection();
            String sql = "insert into order values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement
                    preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<1000000;i++) {
                preparedStatement.setInt(1, i+1);
                preparedStatement.setString(2, "SN"+(i+1));
                preparedStatement.setInt(3,1);
                preparedStatement.setInt(4,1);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        try {
            Connection connection = getConnection();
            String sql = "update user set username=? where id =?";
            PreparedStatement
                    preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(2, 11);
            preparedStatement.setString(1,"11zss");
            preparedStatement.addBatch();
            preparedStatement.setInt(2, 12);
            preparedStatement.setString(1,"12zss");
            preparedStatement.addBatch();
            int[] i = preparedStatement.executeBatch();
            System.out.println(i);
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        try {
            Connection connection = getConnection();
            String sql = "delete from user where id =?";
            PreparedStatement
                    preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,11);
            preparedStatement.addBatch();
            preparedStatement.setInt(1,12);
            preparedStatement.addBatch();

            int[] i = preparedStatement.executeBatch();
            System.out.println(i);
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection) {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

         //save();
        //  update();
        delete();
    }
}
