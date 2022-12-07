package com.example.courseworkc.AdminServer;

import com.example.courseworkc.classes.Admin;
import com.example.courseworkc.classes.User;
import com.example.courseworkc.Database.DatabaseHandler;
import com.example.courseworkc.Database.DatabaseHandlerAccounts;
import com.example.courseworkc.Database.DatabaseHandlerUsers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMethods {
    public String LoginAdmin(String loginText, String loginPassword){
        DatabaseHandler dbHandler = new DatabaseHandler();
        Admin admin = new Admin();
        admin.setAdmin_login(loginText);
        admin.setAdmin_password(loginPassword);
        ResultSet result =  dbHandler.getAdmin(admin);

       int counter = 0;

       try{
        while(result.next()){
           counter++;
        }
       }catch (SQLException e){
           e.printStackTrace();
       }
        return counter>=1? "success": "not success";
    }

    public String LoginUser(String loginText, String loginPassword){
        DatabaseHandlerUsers dbHandler = new DatabaseHandlerUsers();
        User user = new User();
        user.setUser_login(loginText);
        user.setUser_password(loginPassword);

        ResultSet result =  dbHandler.getUser(user);
        int counter = 0;
        try{
            while(result.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return counter>=1? "success": "not success";
    }

    public String AccountingCheck(String worker_id){
        DatabaseHandlerAccounts dbHandler = new DatabaseHandlerAccounts();
        ResultSet result =  dbHandler.getAccounting(worker_id);
        int counter = 0;
        try{
            while(result.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return counter>=1? "success": "not success";
    }
}
