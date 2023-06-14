package br.edu.ifsp.aluno.ddos4.construfacil.application.controller;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.SupplierSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.FindSupplierUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Map;

public class SupplierManegementUI {
    @FXML
    private TableView<Supplier> tTable;

    @FXML
    private TableColumn<Supplier, String> cIDSupplier;
    @FXML
    private TableColumn<Supplier, String> cCorporateName;
    @FXML
    private TableColumn<Supplier, String> cCNPJ;
    @FXML
    private TableColumn<Supplier, String> cPhoneNumberSupplier;

    @FXML
    private TableColumn<Supplier, String> cStatus;

    @FXML
    private ObservableList<Supplier> tableDate;

    @FXML
    private Button bInsert;
    @FXML
    private Button bUpdate;

    FindSupplierUseCase findSupplierUseCase = new FindSupplierUseCase(new SupplierSQLiteDAO());

    @FXML
    private void initialize(){
        binidTableViewToItemsList();
        bindColumnToValueSource();
        loadDataAndShow();
    }

    private void binidTableViewToItemsList() {
        tableDate = FXCollections.observableArrayList();
        tTable.setItems(tableDate);
    }

    private void bindColumnToValueSource() {
        cIDSupplier.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCorporateName.setCellValueFactory(new PropertyValueFactory<>("corporateName"));
        cCNPJ.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cPhoneNumberSupplier.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataAndShow() {
        Map<Long, Supplier>  suppliers = findSupplierUseCase.findAll();
        tableDate.clear();
        tableDate.addAll(suppliers.values());
    }
    public void insertOrUpdateCustomer(ActionEvent actionEvent) throws IOException{
        Button clickButton = (Button) actionEvent.getSource();
        if(clickButton == bInsert) {
            WindowLoader.setRoot("SupplierUI");
            System.out.println("Foi selecionado a opção: Inserir");
        }
        if(clickButton == bUpdate) {
            WindowLoader.setRoot("SupplierUI");
            System.out.println("Foi selecionado a opção: Atualizar");
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
