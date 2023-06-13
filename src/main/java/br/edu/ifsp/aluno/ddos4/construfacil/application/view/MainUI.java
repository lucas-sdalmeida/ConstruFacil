package br.edu.ifsp.aluno.ddos4.construfacil.application.view;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.App;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.io.IOException;

public class MainUI {
    public Button cSupplier;
    public Button cProduct;
    public Button cVenda;
    public Button cPurchase;
    public Button cClient;


    public void goToSupplier(ActionEvent actionEvent) throws IOException {
        App.setRoot("SupplierUI.fxml");
    }

    public void goToProduct(ActionEvent actionEvent) {
    }

    public void goToSale(ActionEvent actionEvent) {
    }

    public void goToPurchase(ActionEvent actionEvent) {
    }

    public void goToCliente(ActionEvent actionEvent) {
    }
}
