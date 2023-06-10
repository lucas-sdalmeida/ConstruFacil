module br.edu.ifsp.aluno.ddos4.construfacil {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.aluno.ddos4.construfacil.application.main to javafx.fxml;
    exports br.edu.ifsp.aluno.ddos4.construfacil.application.main;
}