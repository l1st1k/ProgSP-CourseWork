package com.example.courseworkc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.courseworkc.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class UserAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<AccountingWorkers> ACCOUNTING_TABLE;

    @FXML
    private Button ANALITIC_RELOAD_BUTTON;

    @FXML
    private Button DELETE_WORKER_BUTTON;

    @FXML
    private PieChart department_DIAGRAM;

    @FXML
    private Text DEP_1;

    @FXML
    private Text DEP_11;

    @FXML
    private Text DEP_2;

    @FXML
    private Text DEP_21;

    @FXML
    private Text DEP_3;

    @FXML
    private Text DEP_31;

    @FXML
    private Text DEP_313;

    @FXML
    private Text DEP_32;

    @FXML
    private Text DEP_33;

    @FXML
    private Text DEP_34;

    @FXML
    private Text DEP_35;

    @FXML
    private Text DEP_4;

    @FXML
    private Text DEP_41;

    @FXML
    private Text DEP_5;

    @FXML
    private Text DEP_51;

    @FXML
    private Text FinishSalary;

    @FXML
    private PieChart HOUR_PIECHART;

    @FXML
    private Button HOUR_PIECHART_BUTTON;

    @FXML
    private TextField ID_FOR_DELETE_WORKER;

    @FXML
    private PieChart REBUKE_PIECHART;

    @FXML
    private Button RELOAD_ACCOUNTING;

    @FXML
    private Button RELOAD_PIECHART_REBOK;

    @FXML
    private TableView<Salary> SalaryTable;

    @FXML
    private Button ShowSalaryButton;

    @FXML
    private TableView<Worker> ShowWorkerTable;

    @FXML
    private Button ShowWorkersButton;

    private ArrayList<Worker> WorkerArrayList  = new ArrayList<>();
    private ArrayList<AccountingWorkers> AccountingWorkerArrayList  = new ArrayList<>();
    private ArrayList<Salary> salaryWorkers = new ArrayList<>();

    @FXML
    void initialize() {
        RELOAD_PIECHART_REBOK.setOnAction(event->{
            AdminClient adminClient2 = new AdminClient();
            ArrayList<AccountingWorkers> Workers2 = adminClient2.showAccountingWorkers();
            int dev,sell,add,log,man;
            dev=sell=add=log=man = 0;
            for(AccountingWorkers p : Workers2){
                if (Objects.equals(p.getAcc_rebuke(), "????????")){
                    if(Objects.equals(p.getWorker_department(), "????????????????????")){
                        dev++;
                    }
                    if(Objects.equals(p.getWorker_department(), "????????????")){
                        sell++;
                    }
                    if(Objects.equals(p.getWorker_department(), "??????????????")){
                        add++;
                    }
                    if(Objects.equals(p.getWorker_department(), "??????????????????")){
                        log++;
                    }
                    if(Objects.equals(p.getWorker_department(), "????????????????????????????????")){
                        man++;
                    }
                }
            }
            DEP_11.setText("?????????? ????????????????????: "+dev + " ??????????????????");
            DEP_21.setText("?????????? ????????????: "+sell+" ??????????????????");
            DEP_31.setText("?????????? ??????????????: "+add+" ??????????????????");
            DEP_41.setText("?????????? ??????????????????: "+log+" ??????????????????");
            DEP_51.setText("?????????? ????????????????????????????????: "+man+" ??????????????????");

            ObservableList<PieChart.Data> piechartdata =
                    FXCollections.observableArrayList(
                            new PieChart.Data("????????????????????",dev),
                            new PieChart.Data("????????????",sell),
                            new PieChart.Data("??????????????",add),
                            new PieChart.Data("??????????????????",log),
                            new PieChart.Data("????????????????????????????????",man));
            REBUKE_PIECHART.getData().clear();
            REBUKE_PIECHART.getData().addAll(piechartdata);
        });


        HOUR_PIECHART_BUTTON.setOnAction(event->{
            AdminClient adminClient2 = new AdminClient();
            ArrayList<AccountingWorkers> Workers2 = adminClient2.showAccountingWorkers();
            int dev,sell,add,log,man;
            dev=sell=add=log=man = 0;
            for(AccountingWorkers p : Workers2){
                if(Objects.equals(p.getWorker_department(), "????????????????????")){
                    dev = dev + Integer.parseInt(p.getAcc_hour());
                }
                if(Objects.equals(p.getWorker_department(), "????????????")){
                    sell = sell + Integer.parseInt(p.getAcc_hour());
                }
                if(Objects.equals(p.getWorker_department(), "??????????????")){
                    add = add + Integer.parseInt(p.getAcc_hour());
                }
                if(Objects.equals(p.getWorker_department(), "??????????????????")){
                    log = log + Integer.parseInt(p.getAcc_hour());
                }
                if(Objects.equals(p.getWorker_department(), "????????????????????????????????")){
                    man = man + Integer.parseInt(p.getAcc_hour());
                }
            }
            DEP_313.setText("?????????? ????????????????????: "+dev + " ??????????");
            DEP_32.setText("?????????? ????????????: "+sell+" ??????????");
            DEP_33.setText("?????????? ??????????????: "+add+" ??????????");
            DEP_34.setText("?????????? ??????????????????: "+log+" ??????????");
            DEP_35.setText("?????????? ????????????????????????????????: "+man+" ??????????");

            ObservableList<PieChart.Data> piechartdata =
                    FXCollections.observableArrayList(
                            new PieChart.Data("????????????????????",dev),
                            new PieChart.Data("????????????",sell),
                            new PieChart.Data("??????????????",add),
                            new PieChart.Data("??????????????????",log),
                            new PieChart.Data("????????????????????????????????",man));
            HOUR_PIECHART.getData().clear();
            HOUR_PIECHART.getData().addAll(piechartdata);
        });

        ANALITIC_RELOAD_BUTTON.setOnAction(event->{
            AdminClient adminClient2 = new AdminClient();
            ArrayList<Worker> Workers2 = adminClient2.showWorkers();
            int dev,sell,add,log,man;
            dev=sell=add=log=man = 0;
            for(Worker p : Workers2){
                if(Objects.equals(p.getWorker_department(), "????????????????????")){
                    dev++;
                }
                if(Objects.equals(p.getWorker_department(), "????????????")){
                    sell++;
                }
                if(Objects.equals(p.getWorker_department(), "??????????????")){
                    add++;
                }
                if(Objects.equals(p.getWorker_department(), "??????????????????")){
                    log++;
                }
                if(Objects.equals(p.getWorker_department(), "????????????????????????????????")){
                    man++;
                }
            }
            DEP_1.setText("?????????? ????????????????????: "+dev);
            DEP_2.setText("?????????? ????????????: "+sell);
            DEP_3.setText("?????????? ??????????????: "+add);
            DEP_4.setText("?????????? ??????????????????: "+log);
            DEP_5.setText("?????????? ????????????????????????????????: "+man);

            ObservableList<PieChart.Data> piechartdata =
                    FXCollections.observableArrayList(
                            new PieChart.Data("????????????????????",dev),
                            new PieChart.Data("????????????",sell),
                            new PieChart.Data("??????????????",add),
                            new PieChart.Data("??????????????????",log),
                            new PieChart.Data("????????????????????????????????",man));
            department_DIAGRAM.getData().clear();
            department_DIAGRAM.getData().addAll(piechartdata);


        });

        ShowSalaryButton.setOnAction(event->{
            AdminClient adminClient = new AdminClient();
            this.salaryWorkers = adminClient.ShowSalaryWorkers();
            ObservableList<Salary> observableList = FXCollections.observableArrayList(this.salaryWorkers);
            SalaryTable.setItems(observableList);
            SalaryTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Worker_id"));
            SalaryTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("Worker_name"));
            SalaryTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Worker_surname"));
            SalaryTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("Worker_department"));
            SalaryTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("Worker_salary"));

            double SumSalary = 0.0;
            for(Salary p : salaryWorkers){
                SumSalary += Double.parseDouble(p.getWorker_salary());
            }
            FinishSalary.setText("?????????? ???????? ????: "+ SumSalary +" ????????????");

        });

        RELOAD_ACCOUNTING.setOnAction(event->{
            AdminClient adminClient = new AdminClient();
            this.AccountingWorkerArrayList = adminClient.showAccountingWorkers();
            ObservableList<AccountingWorkers> observableList = FXCollections.observableArrayList(this.AccountingWorkerArrayList);
            ACCOUNTING_TABLE.setItems(observableList);
            ACCOUNTING_TABLE.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Worker_name"));
            ACCOUNTING_TABLE.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("Worker_surname"));
            ACCOUNTING_TABLE.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Worker_department"));
            ACCOUNTING_TABLE.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("Worker_position"));
            ACCOUNTING_TABLE.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("Worker_salary"));
            ACCOUNTING_TABLE.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("Acc_hour"));
            ACCOUNTING_TABLE.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("Acc_rebuke"));
            ACCOUNTING_TABLE.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("Acc_bonus"));
        });

        ShowWorkersButton.setOnAction(event->{
            AdminClient admin_client = new AdminClient();
            this.WorkerArrayList = admin_client.showWorkers();
            ObservableList<Worker> observableList = FXCollections.observableArrayList(this.WorkerArrayList);
            ShowWorkerTable.setItems(observableList);
            ShowWorkerTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("Worker_id"));
            ShowWorkerTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("Worker_name"));
            ShowWorkerTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("Worker_surname"));
            ShowWorkerTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("Worker_fathername"));
            ShowWorkerTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("Worker_department"));
            ShowWorkerTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("Worker_position"));
            ShowWorkerTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("Worker_salary"));
            ShowWorkerTable.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("Worker_year"));
        });
    }

}
