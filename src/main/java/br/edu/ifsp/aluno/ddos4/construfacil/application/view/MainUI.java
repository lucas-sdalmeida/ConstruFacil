package br.edu.ifsp.aluno.ddos4.construfacil.application.view;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainUI {
    public Button cSupplier;
    public Button cProduct;
    public Button cVenda;
    public Button cPurchase;
    public Button cClient;


    public void goToSupplier(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("SupplierManegementUI");
    }

    public void goToProduct(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PurchaseManegementUI");
    }

    public void goToSale(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("SaleManegementUI");
    }

    public void goToPurchase(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PurchaseManegementUI");
    }

    public void goToCustomer(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("CustomerManegementUI");
    }
}
