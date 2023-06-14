module br.edu.ifsp.aluno.ddos.construfacil {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.aluno.ddos4.construfacil.application.main to javafx.fxml;

    opens br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.cashdesk to javafx.base;
    opens br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer to javafx.base;
    opens br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase to javafx.base;
    opens br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale to javafx.base;
    opens br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product to javafx.base;
    opens br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier to javafx.base;

    opens br.edu.ifsp.aluno.ddos4.construfacil.application.view to javafx.fxml;
    opens br.edu.ifsp.aluno.ddos4.construfacil.application.controller to javafx.fxml;

    exports br.edu.ifsp.aluno.ddos4.construfacil.application.view;
    exports br.edu.ifsp.aluno.ddos4.construfacil.application.controller;

    exports br.edu.ifsp.aluno.ddos4.construfacil.application.main;
}