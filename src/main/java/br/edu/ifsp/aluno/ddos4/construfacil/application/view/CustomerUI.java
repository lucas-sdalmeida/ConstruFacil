package br.edu.ifsp.aluno.ddos4.construfacil.application.view;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CustomerUI {
    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
