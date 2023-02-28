package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class calcApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calc-view.fxml"));
        Scene scene = new Scene(loader.load());
//        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("Calculator");
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        ((calcController)loader.getController()).init(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}