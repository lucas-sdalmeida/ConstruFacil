//package br.edu.ifsp.aluno.ddos4.construfacil.application.controller;
//
//import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleDAO;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale.FindSaleUseCase;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.io.IOException;
//import java.util.Map;
//
//public class SaleManegementUI {
//    @FXML
//    private TableView<Sale> tTable;
//    @FXML
//    private TableColumn<Sale, String> cIDSale;
//    @FXML
//    private TableColumn<Sale, String> cIDCostumer;
//    @FXML
//    private TableColumn<Sale, String> cIDProduct;
//    @FXML
//    private TableColumn<Sale, String> cSaleDate;
//
//    @FXML
//    ObservableList<Sale> tableData;
//
//    FindSaleUseCase findSaleUseCase = new FindSaleUseCase(new SaleSQLiteDAO());
//
//    @FXML
//    private void initialize(){
//        binidTableViewToItemsList();
//        bindColumnToValueSource();
//        loadDataAndShow();
//    }
//
//    private void binidTableViewToItemsList() {
//        tableData = FXCollections.observableArrayList();
//        tTable.setItems(tableData);
//    }
//
//    private void bindColumnToValueSource() {
//        cIDSale.setCellValueFactory(new PropertyValueFactory<>("id"));
//        cSaleDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
//        cIDCostumer.setCellValueFactory(new PropertyValueFactory<>("customer"));
//        cIDProduct.setCellValueFactory(new PropertyValueFactory<>("saleItems"));
//    }
//
//    private void loadDataAndShow() {
//        Map<Long, Sale> sales = findSaleUseCase.findAll();
//        tableData.clear();
//        tableData.addAll(sales.values());
//    }
//
//
//    public void insertSale(ActionEvent actionEvent) throws IOException {
//        WindowLoader.setRoot("SaleUI");
//    }
//
//    public void updateSale(ActionEvent actionEvent) throws IOException {
//        WindowLoader.setRoot("SaleUI");
//    }
//
//    public void back(ActionEvent actionEvent) throws IOException {
//        WindowLoader.setRoot("MainUI");
//    }
//}
