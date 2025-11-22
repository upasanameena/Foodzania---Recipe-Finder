# Foodzania---Recipe-Finder

A JavaFX-based desktop application that helps you find recipes based on available ingredients. Simply enter the ingredients you have, and Foodzania will suggest matching recipes from the database.

## ✨ Features

- **Ingredient-based Recipe Search**: Enter your available ingredients and find matching recipes
- **Modern JavaFX UI**: Beautiful and intuitive user interface
- **Database Integration**: MySQL database for storing and retrieving recipes
- **Recipe Gallery**: Browse ready-made recipes
- **Welcome Page**: User-friendly welcome screen

## 🛠️ Technologies & Libraries Used

### Frontend (UI/Desktop Application)

#### Core JavaFX Libraries (v22.0.1)
- **javafx-controls** - UI controls (Button, Label, TextField, ListView, etc.)
- **javafx-fxml** - FXML for declarative UI layout
- **javafx-web** - WebView support for embedded web content
- **javafx-swing** - Swing interoperability
- **javafx-media** - Media playback support

#### UI Enhancement Libraries
- **ControlsFX (v11.2.1)** - Additional enhanced JavaFX controls and components

#### UI Components & Technologies
- **FXML** - XML-based UI markup language for JavaFX
- **CSS** - Custom stylesheets (styles.css, styles1.css) for UI styling
- **JavaFX Layouts**: VBox, HBox, StackPane, BorderPane, GridPane
- **JavaFX Effects**: Bloom, Glow, Lighting, Shadow
- **ImageView** - For displaying images in the UI

### Backend (Database & Logic)

#### Database
- **MySQL Connector/J (v8.0.30)** - JDBC driver for MySQL database connectivity
- **MySQL** - Relational database management system
- **JDBC API** - Java Database Connectivity for database operations

#### Java Standard Libraries
- **java.sql** - Database operations (Connection, PreparedStatement, ResultSet)
- **java.util** - Collections framework (ArrayList, HashMap, List, Map)
- **java.io** - File I/O operations

### Build Tools & Plugins

- **Maven** - Dependency management and build automation
- **Maven Compiler Plugin (v3.13.0)** - Java 22 compilation
- **JavaFX Maven Plugin (v0.0.8)** - JavaFX application packaging and execution

### Testing Framework

- **JUnit Jupiter (v5.10.2)**
  - junit-jupiter-api - Testing API
  - junit-jupiter-engine - Test execution engine

### Core Technologies

- **Java 22** - Programming language and runtime
- **JavaFX 22.0.1** - Desktop application framework
- **Maven** - Build automation and project management

## 📋 Prerequisites

Before running this application, make sure you have:

1. **Java JDK 22** or higher installed
2. **Maven** installed and configured
3. **MySQL Server** installed and running
4. A MySQL database named `foodzania` created

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd recipefinder2
```

### 2. Database Setup

1. Start your MySQL server
2. Create a database named `foodzania`:
   ```sql
   CREATE DATABASE foodzania;
   ```

3. Create a `recipes` table:
   ```sql
   USE foodzania;
   
   CREATE TABLE recipes (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       ingredients TEXT NOT NULL
   );
   ```

4. (Optional) Insert sample recipes:
   ```sql
   INSERT INTO recipes (name, ingredients) VALUES
       ('Pasta with Tomato Sauce', 'Pasta,Tomato,Garlic,Olive Oil'),
       ('Vegetable Stir Fry', 'broccoli,carrot,bell pepper,soy sauce'),
       ('Soup', 'mushroom,carrot,water,spices,cornflour');
   ```

### 3. Configure Database Connection

Update the database credentials in `src/main/java/com/example/recipefinder2/DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/foodzania";
private static final String USER = "root";  // Change if needed
private static final String PASSWORD = "root";  // Change to your MySQL password
```

### 4. Build the Project

```bash
mvn clean compile
```

### 5. Run the Application

```bash
mvn javafx:run
```

Or run the main class directly:
```bash
java --module-path <path-to-javafx> --add-modules javafx.controls,javafx.fxml -cp target/classes com.example.recipefinder2.FoodzaniaApp
```

## 📁 Project Structure

```
recipefinder2/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/recipefinder2/
│       │       ├── FoodzaniaApp.java          # Main application entry point
│       │       ├── FoodZaniaHelloApplication.java
│       │       ├── FoodZaniaHelloController.java  # Main controller
│       │       └── DatabaseConnection.java    # Database connection utility
│       └── resources/
│           └── com/example/recipefinder2/
│               ├── *.fxml                     # FXML UI files
│               ├── *.css                      # Stylesheets
│               └── images/                    # Image resources
├── pom.xml                                    # Maven configuration
└── README.md                                  # This file
```

## 🎯 Usage

1. Launch the application
2. You'll see the welcome page - click to proceed
3. Enter your available ingredients (comma-separated) in the input field
4. Click "Find Recipes" to see matching recipes
5. Use the "HOME" button to return to the homepage
6. Use the "READYMADE" button to browse the recipe gallery

## 🔧 Development

### Building

```bash
mvn clean package
```

### Running Tests

```bash
mvn test
```

## 📝 Notes

- The application requires an active MySQL connection
- Make sure your MySQL server is running before launching the app
- Default database credentials are `root/root` - update them in `DatabaseConnection.java` for production use

## 🤝 Contributing

Feel free to fork this project and submit pull requests for any improvements!

## 📄 License

This project is open source and available for educational purposes.

## 👥 Authors

Created as a recipe finder application project.

---

**Enjoy cooking with Foodzania! 🍜**

