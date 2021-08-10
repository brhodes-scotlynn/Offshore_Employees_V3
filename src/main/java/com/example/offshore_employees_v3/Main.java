package com.example.offshore_employees_v3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Large_Employee current_large_employee;
    public static String current_user;
    public static String current_pass;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Employee_Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void exit_program(Event event) {
        Platform.exit();
        System.exit(0);
    }

    public static void createNewScene(Event event, String newFileFXML) {
        Parent newRoot = null;
        try {
            newRoot = (Parent)FXMLLoader.load(Main.class.getResource(newFileFXML));
        } catch (IOException var5) {
            var5.printStackTrace();
        }
        Scene newScene = new Scene(newRoot);
        newScene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }

    public static Stage createFileChooserStage(Event event, String newFileFXML) {
        Parent newRoot = null;
        try {
            newRoot = (Parent)FXMLLoader.load(Main.class.getResource(newFileFXML));
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        new Scene(newRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        return window;

    }


    public static void main(String[] args) {
        launch();
    }
}