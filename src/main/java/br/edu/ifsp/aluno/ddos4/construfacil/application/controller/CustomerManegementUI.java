package br.edu.ifsp.aluno.ddos4.construfacil.application.controller;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.CustomerSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.FindCustomerUseCase;

import java.io.IOException;
import java.util.Map;

public class CustomerManegementUI {
    @FXML
    private TableView<Customer> tTable;
    @FXML
    private TableColumn<Customer, String> cName;
    @FXML
    private TableColumn<Customer, String> cCPF;
    @FXML
    private TableColumn<Customer, String> cPhone;
    @FXML
    private TableColumn<Customer, String> cID;
    @FXML
    private TableColumn<Customer, String> cStatus;
    @FXML
    private TableColumn<Customer, String> cAddress;

    private ObservableList<Customer> tableData;

    FindCustomerUseCase findCustomerUseCase = new FindCustomerUseCase(new CustomerSQLiteDAO());
    @FXML
    private void initialize(){
        binidTableViewToItemsList();
        bindColumnToValueSource();
        loadDataAndShow();
    }

    private void binidTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tTable.setItems(tableData);
    }

    private void bindColumnToValueSource() {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        cPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataAndShow() {
        Map<Long, Customer> customers = findCustomerUseCase.findAll();
        tableData.clear();
        tableData.addAll(customers.values());
    }



    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void insertCustomer(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CustomerUI");
    }

    public void updateCustomer(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CustomerUI");
    }
}
