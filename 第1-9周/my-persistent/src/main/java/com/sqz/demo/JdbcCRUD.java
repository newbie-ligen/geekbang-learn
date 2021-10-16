package com.sqz.demo;

import java.sql.*;

public class JdbcCRUD {


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
            Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
            Connection  connection = DriverManager.getConnection("jdbc:mysql://192.168.188.200:3306/mybatis?characterEncoding=utf-8", "root", "Root@123456");
            return connection;
    }

    public static void select() {
        try {
            Connection connection = getConnection();
            String sql = "select * from user where username = ?";
            PreparedStatement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "tom1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("username");
                System.out.println("id=" + id + ",username=" + name);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            Connection connection = getConnection();
            String sql = "insert into user values(?,?)";
            PreparedStatement
                    preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 11);
            preparedStatement.setString(2,"zs");
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
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
            preparedStatement.setString(1,"zss");
            int i = preparedStatement.executeUpdate();
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
            int i = preparedStatement.executeUpdate();
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
       // select();
       // save();
      //  update();
        delete();
    }
}
