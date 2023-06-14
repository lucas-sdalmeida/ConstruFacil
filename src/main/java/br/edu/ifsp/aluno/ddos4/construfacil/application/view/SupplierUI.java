package br.edu.ifsp.aluno.ddos4.construfacil.application.view;

import br.edu.ifsp.aluno.ddos4.construfacil.application.main.WindowLoader;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.ProductSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao.SupplierSQLiteDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.RegistryProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.RegistrySupplierUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SupplierUI {
    @FXML
    private Button bSave;
    @FXML
    private TextField txtCorporateName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtCNPJ;


    public void back(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }


    public void save(ActionEvent actionEvent) {
        Button clickButton = (Button) actionEvent.getSource();
        if (clickButton == bSave){
            SupplierSQLiteDAO supplierSQLiteDAO = new SupplierSQLiteDAO();
            RegistrySupplierUseCase registrySupplierUseCase = new RegistrySupplierUseCase(supplierSQLiteDAO);
            Supplier s1 = new Supplier(txtCNPJ.getText(), txtCorporateName.getText(), txtPhone.getText());
            registrySupplierUseCase.registry(s1);
            System.out.println("Dados Salvos");
        }
    }
}
