//package br.edu.ifsp.aluno.ddos4.construfacil.application.controller;
//
//import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
//import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase.FindPurchaseUseCase;
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
//public class PurchaseManegementUI {
//    @FXML
//    private TableView<Purchase> tTable;
//    @FXML
//    private TableColumn<Purchase, String> cIDPurchase;
//    @FXML
//    private TableColumn<Purchase, String> cIDSupplier;
//    @FXML
//    private TableColumn<Purchase, String> cIDProduct;
//    @FXML
//    private TableColumn<Purchase, String> cPurchaseDate;
//
//    @FXML
//    private ObservableList<Purchase> tableData;
//
//    FindPurchaseUseCase findPurchaseUseCase = new FindPurchaseUseCase(new PurchaseSQLiteDAO());
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
//        cIDPurchase.setCellValueFactory(new PropertyValueFactory<>("id"));
//        cIDProduct.setCellValueFactory(new PropertyValueFactory<>("purchasingItems"));
//        cIDSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
//        cPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
//    }
//
//    private void loadDataAndShow() {
//        Map<Long, Purchase> purchases = findPurchaseUseCase.findAll();
//        tableData.clear();
//        tableData.addAll(purchases.values());
//    }
//
//    public void insertPurchase(ActionEvent actionEvent) throws IOException {
//        WindowLoader.setRoot("PurchaseUI");
//    }
//
//    public void updatePurchase(ActionEvent actionEvent) throws IOException {
//        WindowLoader.setRoot("PurchaseUI");
//    }
//
//    public void back(ActionEvent actionEvent) throws IOException {
//        WindowLoader.setRoot("MainUI");
//    }
//}
