package br.edu.ifsp.aluno.ddos4.construfacil.application.controller;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class PurchaseManegementUI {
    public void insertPurchase(ActionEvent actionEvent) {
    }

    public void updatePurchase(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
