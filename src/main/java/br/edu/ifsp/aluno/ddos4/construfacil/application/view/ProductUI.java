package br.edu.ifsp.aluno.ddos4.construfacil.application.view;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.CustomerSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.ProductSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer.RegistryCustomerUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.RegistryProductUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProductUI {
    @FXML
    private Button bSave;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAvarage;
    @FXML
    private TextField txtQuantity;

    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void save(ActionEvent actionEvent) {
        Button clickButton = (Button) actionEvent.getSource();
        if (clickButton == bSave){
            ProductSQLiteDAO productSQLiteDAO = new ProductSQLiteDAO();
            RegistryProductUseCase productUseCase = new RegistryProductUseCase(productSQLiteDAO);
            Product p1 = new Product(txtName.getText(), Long.parseLong(txtQuantity.getText()), Long.parseLong(txtAvarage.getText()));
            productUseCase.registry(p1);
            System.out.println("Dados Salvos");
        }
    }
}
