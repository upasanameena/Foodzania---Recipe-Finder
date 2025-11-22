package com.example.recipefinder2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Objects;

public class FoodzaniaApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Foodzania");

        // Load the welcome page from FXML
        StackPane welcomeLayout = loadWelcomePage();

        // Set the scene for the welcome page
        Scene welcomeScene = new Scene(welcomeLayout, 400, 300);
        primaryStage.setScene(welcomeScene);
        welcomeScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/recipefinder2/styles.css")).toExternalForm());

        primaryStage.show();
    }

    private StackPane loadWelcomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));
            StackPane layout = loader.load();

            // Get the welcome label and set up the click event to navigate to the next page
            Label welcomeLabel = (Label) layout.lookup("#welcomeLabel");
            welcomeLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> navigateToNextPage());

            return layout;
        } catch (IOException e) {
            e.printStackTrace();
            return new StackPane(); // Return an empty layout on error
        }
    }

    private void navigateToNextPage() {
        // Create a new layout for the next page
        FoodZaniaHelloApplication appPage = new FoodZaniaHelloApplication();
        try {
            appPage.start(primaryStage); // Use the same stage to switch scenes
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void switchScene(String fxmlFile) {
        try {
            // Load FXML and set it inside a ScrollPane
            Parent root = FXMLLoader.load(Objects.requireNonNull(FoodzaniaApp.class.getResource("welcome.fxml")));

            // Set the new scene on the stage
            Scene newScene = new Scene(root, 1400, 700);  // Adjusted size
            primaryStage.setScene(newScene);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
