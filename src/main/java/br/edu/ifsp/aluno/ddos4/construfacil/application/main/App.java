package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import br.edu.ifsp.aluno.ddos4.construfacil.application.view.MainUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private static Scene scene;
    MainUI mainUI;

    @Override
    public void start(Stage stage) throws IOException {
        Pane sceneGraph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainUI.fxml")));
        Scene scene1 = new Scene(sceneGraph);
        stage.setScene(scene1);
        stage.show();
    }
}
