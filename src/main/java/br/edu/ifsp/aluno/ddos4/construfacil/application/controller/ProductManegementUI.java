package br.edu.ifsp.aluno.ddos4.construfacil.application.controller;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.ProductSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.FindProductUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Map;

public class ProductManegementUI {
    @FXML
    private TableView<Product> tTable;
    @FXML
    private TableColumn<Product, String> cName;
    @FXML
    private TableColumn<Product, String> cID;
    @FXML
    private TableColumn<Product, String> cQuantity;
    @FXML
    private TableColumn<Product, String> cAvaregePurchase;
    @FXML
    private TableColumn<Product, String> cStatus;

    private ObservableList<Product> tableData;

    FindProductUseCase findProductUseCase = new FindProductUseCase(new ProductSQLiteDAO());

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
        cQuantity.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));
        cAvaregePurchase.setCellValueFactory(new PropertyValueFactory<>("averagePurchasePriceInCents"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadDataAndShow() {
        Map<Long, Product> products = findProductUseCase.findAll();
        tableData.clear();
        tableData.addAll(products.values());
    }

    public void insertProduct(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ProductUI");
    }

    public void editProduct(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ProductUI");
    }

    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
