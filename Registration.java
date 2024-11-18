/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classexercise7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Danny
 */
public class RegistrationApp extends Application {
    
    private static final String DB_URL = "jdbc:sqlite:C:/Users/Danny/OneDrive - Rancho Santiago Community College District/Documents/NetBeansProjects/ClassExercise7/students.db";

    
    @Override
    public void start(Stage primaryStage) {
        //create registration controls
        Label lblName = new Label("Name:");
        TextField txtName = new TextField();
        
        Label lblAge = new Label("Age:");
        TextField txtAge = new TextField();
        
        Button buttonRegister = new Button("Register");
        
        //set button size
        buttonRegister.setPrefWidth(100);
        buttonRegister.setPrefHeight(40);
        
        //create layout container
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(lblName, txtName,
                lblAge, txtAge, buttonRegister);
        
        //create scene
        Scene scene = new Scene(layout, 300, 200);
        
        //set the scene on the stage
        primaryStage.setScene(scene);
        
        //set the stage title and show it
        primaryStage.setTitle("Registration App");
        primaryStage.show();
        
        //handle register button click
        buttonRegister.setOnAction(event ->
        {
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            
            try
            {
                Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO students (name, age) VALUES ('" + name + "', " + age + ")");
                stmt.close();
                conn.close();
                
                System.out.println("Registration Successful!");
                
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
