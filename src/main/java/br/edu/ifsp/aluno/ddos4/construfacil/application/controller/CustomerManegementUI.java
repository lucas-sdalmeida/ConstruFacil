package br.edu.ifsp.aluno.ddos4.construfacil.application.controller;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CustomerManegementUI {
    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
