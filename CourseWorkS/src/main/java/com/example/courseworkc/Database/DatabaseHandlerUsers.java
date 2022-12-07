package com.example.courseworkc.Database;

import com.example.courseworkc.AdminServer.Configs;
import com.example.courseworkc.classes.User;
import com.example.courseworkc.Const.ConstUsers;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandlerUsers extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + ConstUsers.USER_TABLE + "(" +
                ConstUsers.USER_NAME + "," + ConstUsers.USER_SURNAME + "," +
                ConstUsers.USER_LOGIN + "," + ConstUsers.USER_PASSWORD + "," +
                ConstUsers.USER_EMAIL +"," + ConstUsers.USER_GENDER +"," + ConstUsers.USER_LOCATION + ")" +
                "VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getUser_firstname());
            prSt.setString(2, user.getUser_lastname());
            prSt.setString(3, user.getUser_login());
            prSt.setString(4, user.getUser_password());
            prSt.setString(5, user.getUser_email());
            prSt.setString(6, user.getUser_gender());
            prSt.setString(7, user.getUser_location());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + ConstUsers.USER_TABLE + " WHERE " +
                ConstUsers.USER_LOGIN + "=? AND "+ConstUsers.USER_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,user.getUser_login());
            prSt.setString(2,user.getUser_password());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> products = new ArrayList<User>();
        String select = "SELECT * FROM " + ConstUsers.USER_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                User p = new User();
                p.setUser_firstname(resSet.getString("firstname"));
                p.setUser_lastname(resSet.getString("lastname"));
                p.setUser_login(resSet.getString("login"));
                p.setUser_password(resSet.getString("password"));
                p.setUser_email(resSet.getString("email"));
                p.setUser_id(resSet.getString("id"));
                p.setUser_gender((resSet.getString("gender")));
                p.setUser_location(resSet.getString("location"));
                products.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void DeleteUser(String id){
        String delete = "DELETE FROM " + ConstUsers.USER_TABLE + " WHERE " + ConstUsers.USER_ID + "='" + id + "'; ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
            System.out.println("id принятое на сервер = " + id);
            System.out.println("Аккаунт пользователя успешно удален");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
