package edu.metrostate;

import edu.metrostate.migrations.Migrations;
import gui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static gui.showoverview.ShowOverviewController.load;
import static gui.showoverview.ShowOverviewController.populate;


public class Main extends Application {
    private MainController controller;
    private Connection connection;
    private Migrations migrations;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("initializing main.java");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent root = loader.load();
            this.controller = loader.getController();
            primaryStage.setTitle("My Application");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
