package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import br.edu.ifsp.aluno.ddos4.construfacil.application.view.MainUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Pane sceneGraph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainUI.fxml")));
        Scene scene = new Scene(sceneGraph);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {

    }
}
