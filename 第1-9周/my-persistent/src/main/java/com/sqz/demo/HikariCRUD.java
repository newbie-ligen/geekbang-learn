package com.sqz.demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class HikariCRUD {

    public static HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.188.200:3306/mybatis?characterEncoding=utf-8&rewriteBatchedStatements=true");
        config.setUsername("root");
        config.setPassword("Root@123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }

    public static void update(HikariDataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "update user set username=? where id =?";
        PreparedStatement
                preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(2, 11);
        preparedStatement.setString(1, "zss");
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
    }

    public static void save(HikariDataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "insert into user values(?,?)";
        PreparedStatement
                preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 11);
        preparedStatement.setString(2, "zs");
        int i = preparedStatement.executeUpdate();
        System.out.println(i);
    }

    public static void select(HikariDataSource dataSource) throws SQLException {

            Connection connection = dataSource.getConnection();
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
    }

    public static void delete(HikariDataSource dataSource) throws SQLException {

            Connection connection =dataSource.getConnection();
            String sql = "delete from user where id =?";
            PreparedStatement
                    preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,11);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);

    }


    public static void main(String[] args) throws SQLException {
        HikariDataSource dataSource = createDataSource();
        save(dataSource);
        update(dataSource);
        select(dataSource);
        delete(dataSource);
    }
}
