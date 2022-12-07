package com.example.courseworkc.controller;

import com.example.courseworkc.classes.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;


public class AdminClient {
    private String log;
    String Check_Connect(String ip, int port){
        try(Socket clientSocket = new Socket(ip,port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Server is active. You are able to connect!";

        } catch (IOException e) {
            log = "Unable to find server with this credentials. Please, check the out!";
        }
        return log;
    }

    int signIn(String ip, int port, String login, String password){

        int status = 0;

        try(Socket clientSocket = new Socket(ip,port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            System.out.println("Connecting...");
            writer.write("authorization");
            writer.newLine();

            writer.write(login);
            writer.newLine();
            password = DigestUtils.sha256Hex(password);
            writer.write(password);
            writer.newLine();

            writer.flush();

            String success = reader.readLine(); // принимаем состояние входа

            status = success.equals("success")? 1 : 0;

        }
        catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return status;
    }

    void AddAdmin(String name, String surname, String login, String password,String email){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("addAdmin");
            writer.newLine();

            password = DigestUtils.sha256Hex(password);
            // отправляем имя фамилию логин пароль и емаил на сервер
            writer.write(name);writer.newLine();
            writer.write(surname);writer.newLine();
            writer.write(login);writer.newLine();
            writer.write(password);writer.newLine();
            writer.write(email);writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
    }

    ArrayList<AccountingWorkers> showAccountingWorkers(){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("ShowAccountingWorkers");writer.newLine();
            writer.flush();
            ArrayList<AccountingWorkers> arrayList = new ArrayList<AccountingWorkers>();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    arrayList =  (ArrayList<AccountingWorkers>) object;
                    return arrayList;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return null;
    }

    ArrayList<Salary> ShowSalaryWorkers(){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("ShowWorkersSalary");
            writer.newLine();
            writer.flush();
            ArrayList<Salary> arrayList = new ArrayList<Salary>();
            ArrayList<Salary> salaryworkers = new ArrayList<>();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    arrayList =  (ArrayList<Salary>) object;
                    for(Salary p : arrayList){
                        double sal = 0;
                        String rebuke = p.getAcc_rebuke();
                        if(Objects.equals(rebuke, "нет")){
                            int percent_bonus = Integer.parseInt(p.getAcc_bonus());
                            double bonus = percent_bonus * 0.01;
                            double coefficient = bonus + 1.0;
                            // 4_рабочие_недели * (часовая_ставка * часы_в_неделю) * коэффициент_премии
                            sal = 4.0 * Integer.parseInt(p.getWorker_salary()) * Integer.parseInt(p.getAcc_hour()) * coefficient;
                            p.setWorker_salary(String.valueOf(sal));
                        } else {
                            // 4_рабочие_недели * (часовая_ставка * часы_в_неделю)
                            sal = 4.0 * Integer.parseInt(p.getWorker_salary()) * Integer.parseInt(p.getAcc_hour());
                            p.setWorker_salary(String.valueOf(sal));
                        }
                        salaryworkers.add(p);
                    }

                    return salaryworkers;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return null;
    }

    ArrayList<Worker> showWorkers(){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("ShowWorkers");
            writer.newLine();
            writer.flush();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
//                    Object object = objectInputStream.readObject();
                    return (ArrayList<Worker>) objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return null;
    }

    ArrayList<User> showUsers(){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("showusers");writer.newLine();
            writer.flush();
            ArrayList<User> arrayList = new ArrayList<User>();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    arrayList =  (ArrayList<User>) object;
                    return arrayList;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return null;
    }

    ArrayList<Admin> showAdmins(){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            log = "Вы подключились к серверу";
            writer.write("showadmins");
            writer.newLine();
            writer.flush();
            ArrayList<Admin> arrayList = new ArrayList<Admin>();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    arrayList =  (ArrayList<Admin>) object;
                    return arrayList;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log = "Не удалось подключится к серверу";
        }
        return null;
    }

    void DeleteWorker(String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("deleteWorker");writer.newLine();
            writer.write(id);writer.newLine();writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void DeleteUser(String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("deleteUser");writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void DeleteAccountingWorker(String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("deleteAccountionWorker");writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    void DeleteAdmin(String id){ // просто передаю id на сервак
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("deleteAdmin");writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    Worker FindWorker(String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("FindWorker");writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();

            Worker worker = new Worker();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    worker =  (Worker) object;
                    return worker;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    Admin FindAdmin(String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("FindAdmin");writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();

            Admin admin = new Admin();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    admin =  (Admin) object;
                    return admin;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    void EditWorker(String id, String Name, String Surname, String Fathername,
                    String department,String Position, String Salary, String Year)
    {
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("EditWorker"); writer.newLine();
            writer.write(id); writer.newLine();
            writer.write(Name); writer.newLine();
            writer.write(Surname); writer.newLine();
            writer.write(Fathername); writer.newLine();
            writer.write(department); writer.newLine();
            writer.write(Position); writer.newLine();
            writer.write(Salary); writer.newLine();
            writer.write(Year); writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void EditAdmin(String id, String Name, String Surname, String Login, String Password, String Email){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("EditAdmin");writer.newLine();
            writer.write(id);writer.newLine();
            writer.write(Name);writer.newLine();
            writer.write(Surname);writer.newLine();
            writer.write(Login);writer.newLine();
            writer.write(Password);writer.newLine();
            writer.write(Email);writer.newLine();
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    void Accounting_add_worker(Worker worker){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            String worker_id = worker.getWorker_id();
            writer.write("Accounting_add_worker");writer.newLine();
            writer.write(worker_id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Accounting_add_rebuke(String worker_id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("Accounting_add_rebuke");writer.newLine();
            writer.write(worker_id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Accounting_delete_rebuke(String worker_id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("Accounting_delete_rebuke");writer.newLine();
            writer.write(worker_id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Accounting_bonus(Integer bonus,String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("Accounting_bonus");writer.newLine();
            writer.write(bonus.toString());writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Accounting_add_hour(Integer hour, String id){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("Accounting_hour");writer.newLine();
            writer.write(hour.toString());writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void AddWorker(String name, String surname, String fathername, String department, String position,String salary,String year){
        try(Socket clientSocket = new Socket("127.0.0.1",8081);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("AddWorker");writer.newLine();
            writer.write(name);writer.newLine();
            writer.write(surname);writer.newLine();
            writer.write(fathername);writer.newLine();
            writer.write(department);writer.newLine();
            writer.write(position);writer.newLine();
            writer.write(salary);writer.newLine();
            writer.write(year);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
