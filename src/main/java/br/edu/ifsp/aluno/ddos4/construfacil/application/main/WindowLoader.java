package br.edu.ifsp.aluno.ddos4.construfacil.application.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class WindowLoader extends Application {
    private static Scene scene;
    private static Object controller;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainUI"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(Objects.requireNonNull(WindowLoader.class.getResource(fxml + ".fxml")).openStream());
        controller = fxmlLoader.getController();
        return root;
    }

    public static Object getController() {
        return controller;
    }
}
