package com.example.demo.batch;

import javax.naming.PartialResultException;
import java.math.BigDecimal;
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
            String sql = "insert into b_order values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement
                    preparedStatement = connection.prepareStatement(sql);
            for(int i=0;i<1000000;i++) {
                preparedStatement.setInt(1, i+1);
                preparedStatement.setString(2, "SN"+(i+1));
                preparedStatement.setInt(3,1);
                preparedStatement.setInt(4,1);
                preparedStatement.setBigDecimal(5,new BigDecimal(100.1));
                preparedStatement.setBigDecimal(6,new BigDecimal(0.5));
                preparedStatement.setInt(7,1);
                preparedStatement.setBigDecimal(8,new BigDecimal(12.5));
                preparedStatement.setInt(9,1);
                preparedStatement.setDate(10,  new Date(System.currentTimeMillis()));
                preparedStatement.setDate(11,new Date(System.currentTimeMillis()));
                preparedStatement.setDate(12,new Date(System.currentTimeMillis()));
                preparedStatement.setDate(13,new Date(System.currentTimeMillis()));
                preparedStatement.setDate(14,new Date(System.currentTimeMillis()));
                preparedStatement.setDate(15,new Date(System.currentTimeMillis()));
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
        long start = System.currentTimeMillis();
         save();
         long end = System.currentTimeMillis();
        System.out.println((end-start)/1000);
    }
}
