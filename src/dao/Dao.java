package dao;

import java.sql.*;

public class Dao {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance_system?serverTimezone=UTC&useSSL=false", "root", "12345678");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean create(String query) {
        try {
            statement = connect.createStatement();
            return (!statement.execute(query)) ;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean update(String query) {
        try {
            statement = connect.createStatement();
            return (!statement.execute(query)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String query) {
        try {
            statement = connect.createStatement();
            return (!statement.execute(query)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet retrieve(String query) {
        try {
            statement = connect.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
