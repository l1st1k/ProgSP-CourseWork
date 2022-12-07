package com.example.courseworkc.Database;

import com.example.courseworkc.AdminServer.Configs;
import com.example.courseworkc.classes.Admin;
import com.example.courseworkc.Const.Const;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpAdmin(Admin admin) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.FIRST_NAME + "," + Const.LAST_NAME + "," +
                Const.LOGIN + "," + Const.PASSWORD + "," +
                Const.EMAIL + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, admin.getAdmin_firstname());
            prSt.setString(2, admin.getAdmin_lastname());
            prSt.setString(3, admin.getAdmin_login());
            prSt.setString(4, admin.getAdmin_password());
            prSt.setString(5, admin.getAdmin_email());


            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAdmin(Admin admin) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
        Const.LOGIN + "=? AND "+Const.PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
             prSt.setString(1,admin.getAdmin_login());
            prSt.setString(2,admin.getAdmin_password());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ArrayList<Admin> getAdmins() {
        ArrayList<Admin> products = new ArrayList<>();
        String select = "SELECT * FROM " + Const.USER_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Admin p = new Admin();
                p.setAdmin_firstname(resSet.getString("firstname"));
                p.setAdmin_lastname(resSet.getString("lastname"));
                p.setAdmin_login(resSet.getString("login"));
                p.setAdmin_password(resSet.getString("password"));
                p.setAdmin_email(resSet.getString("email"));
                p.setAdmin_id(resSet.getString("id"));

                products.add(p);
            }
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return products;
    }

    public void DeleteAdmin(String id){
        String delete = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.ADMIN_ID + "='" + id + "'; ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
            System.out.println("id принятое на сервер = " + id);
            System.out.println("Админ успешно удален");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Admin Find_Admin_For_Edit(String id){
        Admin admin = new Admin();
        String findAdmin = "SELECT * FROM " + Const.USER_TABLE + " WHERE "+Const.ADMIN_ID + "=?";
        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(findAdmin);
            prSt.setString(1,id);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Admin p = new Admin();
                p.setAdmin_firstname(resSet.getString("firstname"));
                p.setAdmin_lastname(resSet.getString("lastname"));
                p.setAdmin_login(resSet.getString("login"));
                p.setAdmin_password(resSet.getString("password"));
                p.setAdmin_email(resSet.getString("email"));
                p.setAdmin_id(resSet.getString("id"));
                admin = p;
            }
            System.out.println("id принятое на сервер = " + id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void EditAdmin(String id, Admin admin){
        try{
            String update = "UPDATE " + Const.USER_TABLE +
                    " SET " + Const.FIRST_NAME + "=?, " + Const.LAST_NAME + "=?, "
                    + Const.LOGIN + "=?, " + Const.PASSWORD + "=?, " + Const.EMAIL + "=? " +
                    " WHERE " + Const.ADMIN_ID + "=?";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

            System.out.println(id + " " + admin.toString());
            preparedStatement.setString(1, admin.getAdmin_firstname());
            preparedStatement.setString(2, admin.getAdmin_lastname());
            preparedStatement.setString(3, admin.getAdmin_login());
            preparedStatement.setString(4, admin.getAdmin_password());
            preparedStatement.setString(5, admin.getAdmin_email());
            preparedStatement.setInt(6, Integer.parseInt(id));
            System.out.println("Админ успешно изменен!");

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }
}