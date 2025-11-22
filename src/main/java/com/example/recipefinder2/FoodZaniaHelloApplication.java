package com.example.recipefinder2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FoodZaniaHelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the UI
        FXMLLoader fxmlLoader = new FXMLLoader(FoodZaniaHelloApplication.class.getResource("recipefinder2.fxml"));

        // Adjust scene size according to FXML layout dimensions (807x659 in FXML)
        Scene scene = new Scene(fxmlLoader.load(), 807, 659);

        // Set window title
        stage.setTitle("Recipe Finder");

        // Attach the scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();  // Launch the JavaFX application
    }
}
