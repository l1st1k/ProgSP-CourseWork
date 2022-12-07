package com.example.courseworkc.AdminServer;

import com.example.courseworkc.classes.*;
import com.example.courseworkc.Database.DatabaseHandler;
import com.example.courseworkc.Database.DatabaseHandlerAccounts;
import com.example.courseworkc.Database.DatabaseHandlerUsers;
import com.example.courseworkc.Database.DatabaseHandlerWorkers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8081)) {
            System.out.println("Server is working now!");
            while(true)
                try
                {
                    Socket socket =  serverSocket.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try(BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                                BufferedWriter writer = new BufferedWriter(
                                        new OutputStreamWriter(socket.getOutputStream())))
                            {
                                String menu = reader.readLine();
                                System.out.println("action - " + menu); // выводим пункт меню в консоль
                                switch (menu){
                                    case "authorization":{
                                        String Auth_login = reader.readLine(); // принимаем логин
                                        String Auth_password = reader.readLine(); // принимаем пороль
                                        ServerMethods s = new ServerMethods();
                                        String SingInSuccess;
                                        SingInSuccess =  s.LoginAdmin(Auth_login,Auth_password);
                                        writer.write(SingInSuccess); // отправляем успешность вхождения
                                        System.out.println("auth_result - " + SingInSuccess);
                                        writer.newLine();
                                        writer.flush();
                                    }break;
                                    case "authorizationUser":{
                                        String Auth_login = reader.readLine(); // принимаем логин
                                        String Auth_password = reader.readLine(); // принимаем пороль
                                        ServerMethods s = new ServerMethods();
                                        String SingInSuccess;
                                        SingInSuccess =  s.LoginUser(Auth_login,Auth_password);
                                        writer.write(SingInSuccess); // отправляем успешность вхождения
                                        System.out.println("auth_result - " + SingInSuccess);
                                        writer.newLine();
                                        writer.flush();
                                    }break;
                                    case "Accounting_add_worker":{
                                        String worker_id = reader.readLine();
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        ServerMethods s = new ServerMethods();
                                        String success = s.AccountingCheck(worker_id);
                                        // TODO check logic
                                        if(Objects.equals(success, "not success")){
                                            d.Accounting_add_worker(worker_id);
                                        }else{
                                            System.out.println("Данный работник уже добавлен в учет деятельности");
                                        }
                                    }break;
                                    case "Accounting_add_rebuke":{
                                        String worker_id = reader.readLine();
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_add_rebuke(worker_id);
                                    }break;
                                    case "Accounting_delete_rebuke":{
                                        String worker_id = reader.readLine();
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_delete_rebuke(worker_id);
                                    }break;
                                    case "Accounting_bonus":{
                                        String bonus_p = reader.readLine();
                                        String id = reader.readLine();
                                        Integer bonus = Integer.parseInt(bonus_p);
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_bonus(bonus,id);
                                    }break;
                                    case "Accounting_hour":{
                                        String hour = reader.readLine();
                                        String id = reader.readLine();
                                        Integer hours = Integer.parseInt(hour);
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        d.Accounting_add_hour(hours,id);
                                    }break;
                                    case "addUser":{
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String login = reader.readLine();
                                        String password = reader.readLine();
                                        String email = reader.readLine();
                                        String gender = reader.readLine();
                                        String location = reader.readLine();
                                        User user = new User(name,surname,login,password,email,gender,location);
                                        DatabaseHandlerUsers d = new DatabaseHandlerUsers();
                                        d.signUpUser(user);
                                    }break;
                                    case "addAdmin":{
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String login = reader.readLine();
                                        String password = reader.readLine();
                                        String email = reader.readLine();
                                        Admin admin = new Admin(name,surname,login,password,email);
                                        DatabaseHandler dbHandler = new DatabaseHandler();
                                        dbHandler.signUpAdmin(admin);
                                    }break;
                                    case "ShowWorkersSalary":{
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        ArrayList<Salary> salary_list = d.getWorkerSalary();
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(salary_list);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "ShowAccountingWorkers":{
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        ArrayList<AccountingWorkers> accountingWorkers = d.getAccountingWorkers();
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(accountingWorkers);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "ShowWorkers":{
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        ArrayList<Worker> workers = d.getWorker();
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(workers);
                                            objectOutputStream.flush();
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "showusers":{
                                        DatabaseHandlerUsers d = new DatabaseHandlerUsers();
                                        ArrayList<User> users = d.getUsers();
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(users);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }break;
                                    case "showadmins": {
                                        DatabaseHandler d = new DatabaseHandler();
                                        ArrayList<Admin> admins = d.getAdmins();
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(admins);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "deleteWorker":{
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String id = reader.readLine();
                                        d.DeleteWorker(id);
                                    }break;
                                    case "deleteUser":{
                                        DatabaseHandlerUsers d = new DatabaseHandlerUsers();
                                        String id = reader.readLine();
                                        d.DeleteUser(id);
                                    }break;
                                    case "deleteAccountionWorker":{
                                        DatabaseHandlerAccounts d = new DatabaseHandlerAccounts();
                                        String id = reader.readLine();
                                        d.DeleteAccountingWorker(id);
                                    }break;
                                    case "deleteAdmin":{
                                        DatabaseHandler d = new DatabaseHandler();
                                        String id =reader.readLine();
                                        d.DeleteAdmin(id);
                                    }break;
                                    case "FindAdmin":{
                                        DatabaseHandler d = new DatabaseHandler();
                                        String id =reader.readLine();
                                        Admin admin = d.Find_Admin_For_Edit(id);
                                        System.out.println(admin.getAdmin_firstname()+" "+admin.getAdmin_lastname()+" "+admin.getAdmin_login()
                                                +" "+admin.getAdmin_password()+" "+admin.getAdmin_email());
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(admin);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case"FindWorker":{
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String id =reader.readLine();
                                        Worker worker = d.Find_Worker_For_Edit(id);
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(worker); // отправляем работника на клиент
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "EditWorker":{
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String id = reader.readLine();
                                        String Name = reader.readLine();
                                        String Surname = reader.readLine();
                                        String Fathername = reader.readLine();
                                        String Department = reader.readLine();
                                        String Position = reader.readLine();
                                        String Salary = reader.readLine();
                                        String Year = reader.readLine();
                                        Worker worker = new Worker(Name,Surname,Fathername,Department,Position,Year,Salary);
                                        d.EditWorker(id,worker);
                                    }break;
                                    case "EditAdmin":{
                                        DatabaseHandler d = new DatabaseHandler();
                                        String id =reader.readLine();
                                        String Name =reader.readLine();
                                        String Surname =reader.readLine();
                                        String Login =reader.readLine();
                                        String Password =reader.readLine();
                                        String Email =reader.readLine();
                                        Admin admin = new Admin(Name, Surname,Login,Password,Email);
                                        d.EditAdmin(id,admin);
                                    }break;
                                    case "AddWorker":{
                                        DatabaseHandlerWorkers d = new DatabaseHandlerWorkers();
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String fathername = reader.readLine();
                                        String department = reader.readLine();
                                        String position = reader.readLine();
                                        String salary = reader.readLine();
                                        String year = reader.readLine();
                                        Worker worker =new Worker(name,surname,fathername,department,position,year,salary);
                                        d.AddWorker(worker);
                                    }
                                }

                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }).start();

                }catch (Exception e){
                    e.printStackTrace();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
