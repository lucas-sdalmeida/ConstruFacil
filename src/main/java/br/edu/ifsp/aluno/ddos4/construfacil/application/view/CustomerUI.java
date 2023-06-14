package br.edu.ifsp.aluno.ddos4.construfacil.application.view;

import br.edu.ifsp.aluno.ddos4.construfacil.application.controller.UIMode;
import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.CustomerSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.RegistryCustomerUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CustomerUI {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPhone;

    @FXML
    private Button bSave;

    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void Save(ActionEvent actionEvent) {
        Button clickButton = (Button) actionEvent.getSource();
        if (clickButton == bSave){
            CustomerSQLiteDAO customerSQLiteDAO = new CustomerSQLiteDAO();
            RegistryCustomerUseCase registryCustomerUseCase = new RegistryCustomerUseCase(customerSQLiteDAO);
            Customer c1 = new Customer(txtName.getText(), txtCPF.getText(), txtAddress.getText(), txtPhone.getText());
            registryCustomerUseCase.registry(c1);
            System.out.println("Dados Salvos");
            }
        }
}
