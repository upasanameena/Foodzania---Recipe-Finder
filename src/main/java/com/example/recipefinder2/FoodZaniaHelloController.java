package com.example.recipefinder2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.recipefinder2.FoodzaniaApp.switchScene;


public class FoodZaniaHelloController {
    
    @FXML
    private TextField ingredientInput;  // Input field for ingredients

    @FXML
    private ListView<String> recipeListView;  // List view to display matching recipes

    @FXML
    private Button findRecipesButton;  // Button to trigger recipe search

    @FXML
    private Button navigateButton;

    @FXML
    private Button anujapage;

    private Stage primaryStage;

    @FXML
    public void openHomepage(){
        switchScene("welcome.fxml");
    }
    @FXML

    public void openRecipe() {
        try {
            // Load the new.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("new.fxml"));
            Parent root = loader.<Parent>load();

            // Create a new stage for the recipe gallery
            Stage stage = new Stage();
            stage.setTitle("Recipe Gallery");
            stage.setScene(new Scene(root, 800, 600)); // Set appropriate width and height
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Sample recipe database with ingredients
    private final Map<String, List<String>> recipeDatabase = new HashMap<>();

    // Initialize sample recipes and their required ingredients
    public FoodZaniaHelloController()
    {
        recipeDatabase.put("Pasta with Tomato Sauce", List.of("Pasta", "Tomato", "Garlic", "Olive Oil"));
        recipeDatabase.put("Vegetable Stir Fry", List.of("broccoli", "carrot", "bell pepper", "soy sauce"));
        recipeDatabase.put("Soup", List.of("mushroom","carrot","water","spices","cornflour"));
        recipeDatabase.put("Pasta", List.of("pasta", "tomato sauce", "cheese", "garlic", "basil"));
        recipeDatabase.put("Pizza", List.of("pizza dough", "tomato sauce", "mozzarella", "pepperoni", "olives"));
        recipeDatabase.put("Burger", List.of("bun", "beef patty", "lettuce", "tomato", "cheese"));
        recipeDatabase.put("Salad", List.of("lettuce", "cucumber", "tomato", "feta", "olives"));
        recipeDatabase.put("Sandwich", List.of("bread", "ham", "cheese", "lettuce", "mustard"));
        recipeDatabase.put("Omelette", List.of("eggs", "cheese", "mushroom", "spinach", "onion"));
        recipeDatabase.put("Curry", List.of("chicken", "curry powder", "coconut milk", "onion", "tomato"));
        recipeDatabase.put("Stew", List.of("beef", "potatoes", "carrots", "onions", "broth"));
        recipeDatabase.put("Tacos", List.of("tortilla", "ground beef", "lettuce", "cheese", "salsa"));
        recipeDatabase.put("Sushi", List.of("sushi rice", "nori", "salmon", "avocado", "soy sauce"));
        recipeDatabase.put("Smoothie", List.of("banana", "spinach", "almond milk", "chia seeds", "honey"));
        recipeDatabase.put("Pancakes", List.of("flour", "eggs", "milk", "baking powder", "syrup"));
        recipeDatabase.put("Fried Rice", List.of("rice", "soy sauce", "carrot", "peas", "egg"));
        recipeDatabase.put("Noodles", List.of("noodles", "soy sauce", "sesame oil", "vegetables", "chicken"));
        recipeDatabase.put("Lasagna", List.of("lasagna noodles", "ground beef", "tomato sauce", "ricotta", "mozzarella"));
        recipeDatabase.put("Quesadilla", List.of("tortilla", "cheese", "chicken", "bell pepper", "onion"));
        recipeDatabase.put("Risotto", List.of("arborio rice", "chicken broth", "parmesan", "mushroom", "butter"));
        recipeDatabase.put("Burrito", List.of("tortilla", "beans", "rice", "cheese", "sour cream"));
        recipeDatabase.put("Dumplings", List.of("dumpling wrappers", "pork", "cabbage", "ginger", "soy sauce"));
        recipeDatabase.put("Porridge", List.of("oats", "milk", "banana", "honey", "cinnamon"));
        recipeDatabase.put("Goulash", List.of("beef", "onion", "paprika", "potatoes", "carrot"));
        recipeDatabase.put("Meatballs", List.of("ground beef", "breadcrumbs", "parmesan", "garlic", "egg"));
        recipeDatabase.put("Falafel", List.of("chickpeas", "parsley", "garlic", "cumin", "onion"));
        recipeDatabase.put("Chili", List.of("ground beef", "beans", "tomato", "chili powder", "onion"));
        recipeDatabase.put("Macaroni and Cheese", List.of("macaroni", "cheddar", "milk", "butter", "flour"));
        recipeDatabase.put("Stir Fry", List.of("chicken", "broccoli", "soy sauce", "ginger", "carrot"));
        recipeDatabase.put("Grilled Cheese", List.of("bread", "cheddar", "butter", "tomato soup", "ham"));

    }

    @FXML
    public void initialize() {
        // Add event listener to find recipes when the button is clicked
        findRecipesButton.setOnAction(e -> findMatchingRecipes());
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Method to find and display matching recipes
    private void findMatchingRecipes() {
        // Clear current recipes in the ListView
        recipeListView.getItems().clear();


        // Get the ingredients entered by the user
        String input = ingredientInput.getText();
        if (input != null && !input.trim().isEmpty()) {
            // Split input by commas to extract individual ingredients
            String[] inputIngredients = input.split(",");

            // Normalize the ingredients (trim spaces and convert to lowercase)
            List<String> enteredIngredients = new ArrayList<>();
            for (String ingredient : inputIngredients) {
                enteredIngredients.add(ingredient.trim().toLowerCase());
            }

            // Find recipes that match any of the user's ingredients
            List<String> matchedRecipes = new ArrayList<>();

            try (Connection connection = DatabaseConnection.getConnection()) {
                String sql = "SELECT name, ingredients FROM recipes";
                try (PreparedStatement statement = connection.prepareStatement(sql);
                     ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        String recipeName = resultSet.getString("name");
                        String ingredients = resultSet.getString("ingredients");
                        List<String> recipeIngredients = List.of(ingredients.split(","));

            /*for (Map.Entry<String, List<String>> entry : recipeDatabase.entrySet()) {
                String recipeName = entry.getKey();
                List<String> recipeIngredients = entry.getValue();*/

                        // Check if any recipe ingredients are available in the entered ingredients
                        for (String recipeIngredient : recipeIngredients) {
                            if (enteredIngredients.contains(recipeIngredient.toLowerCase())) {
                                matchedRecipes.add(recipeName);
                                break; // Break once we find a matching ingredient
                            }
                        }
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            // Display matched recipes or a default message if none are found
            if (matchedRecipes.isEmpty()) {
                recipeListView.getItems().add("No matching recipes found. Try different ingredients.");
            } else {
                recipeListView.getItems().addAll(matchedRecipes);
            }
        } else {
            // Show message if no ingredients were entered
            recipeListView.getItems().add("Please enter some ingredients.");
        }
        recipeListView.setStyle("-fx-background-color: beige;");
    }

    @FXML
    public void handleNavigateButtonAction() {
        try {
            // Load the new FXML file for the next page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));
            Parent root = loader.load();

            // Set the new scene on the current stage
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void openAnujaPage(ActionEvent event) {
        try {
            // Load the new FXML page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("new.fxml")); // Adjust the path as needed
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }


    @FXML
    public void handleStartCooking() {
        try {
            // Load the FXML for the "Start Cooking" page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/resources/com/example/recipefinder2/welcome.fxml"));
            Parent root = loader.load();

            // Get the current stage from any component in the current scene (e.g., the label's stage)
            Stage stage = (Stage) navigateButton.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
