package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("Loading form.fxml...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/form.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
        primaryStage.setTitle("Aadhar Card Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Application started successfully.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}