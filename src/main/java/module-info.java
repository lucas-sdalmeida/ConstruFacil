module br.edu.ifsp.aluno.ddos4.construfacil {
    requires javafx.controls;
    requires javafx.fxml;
    requires rt;
    requires java.sql;


    opens br.edu.ifsp.aluno.ddos4.construfacil to javafx.fxml;
    exports br.edu.ifsp.aluno.ddos4.construfacil;
}