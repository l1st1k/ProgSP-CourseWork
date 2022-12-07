package com.example.courseworkc.classes;

import java.io.Serializable;

public class AccountingWorkers  implements Serializable {
    private String Worker_name;
    private String Worker_surname;
    private String Worker_department;
    private String Worker_position;
    private String Worker_salary;
    private String Acc_hour;
    private String Acc_bonus;
    private String Acc_rebuke;

    public AccountingWorkers() {

    }

    @Override
    public String toString() {
        return "AccountingWorkers{" +
                "Worker_name='" + Worker_name + '\'' +
                ", Worker_surname='" + Worker_surname + '\'' +
                ", Worker_department='" + Worker_department + '\'' +
                ", Worker_position='" + Worker_position + '\'' +
                ", Worker_salary='" + Worker_salary + '\'' +
                ", Acc_hour='" + Acc_hour + '\'' +
                ", Acc_bonus='" + Acc_bonus + '\'' +
                ", Acc_rebuke='" + Acc_rebuke + '\'' +
                '}';
    }

    public AccountingWorkers(String worker_name, String worker_surname, String worker_department, String worker_position, String worker_salary, String acc_hour, String acc_bonus, String acc_rebuke) {
        Worker_name = worker_name;
        Worker_surname = worker_surname;
        Worker_department = worker_department;
        Worker_position = worker_position;
        Worker_salary = worker_salary;
        Acc_hour = acc_hour;
        Acc_bonus = acc_bonus;
        Acc_rebuke = acc_rebuke;
    }

    public String getWorker_name() {
        return Worker_name;
    }

    public void setWorker_name(String worker_name) {
        Worker_name = worker_name;
    }

    public String getWorker_surname() {
        return Worker_surname;
    }

    public void setWorker_surname(String worker_surname) {
        Worker_surname = worker_surname;
    }

    public String getWorker_department() {
        return Worker_department;
    }

    public void setWorker_department(String worker_department) {
        Worker_department = worker_department;
    }

    public String getWorker_position() {
        return Worker_position;
    }

    public void setWorker_position(String worker_position) {
        Worker_position = worker_position;
    }

    public String getWorker_salary() {
        return Worker_salary;
    }

    public void setWorker_salary(String worker_salary) {
        Worker_salary = worker_salary;
    }

    public String getAcc_hour() {
        return Acc_hour;
    }

    public void setAcc_hour(String acc_hour) {
        Acc_hour = acc_hour;
    }

    public String getAcc_bonus() {
        return Acc_bonus;
    }

    public void setAcc_bonus(String acc_bonus) {
        Acc_bonus = acc_bonus;
    }

    public String getAcc_rebuke() {
        return Acc_rebuke;
    }

    public void setAcc_rebuke(String acc_rebuke) {
        Acc_rebuke = acc_rebuke;
    }
}
