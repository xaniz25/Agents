//Created by Shanice Talan on September 11, 2019
//CMPP 264 Java - Day 6 Assignment: JavaFX/SceneBuilder Application that modifies Agent table
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //changed title and scene size
        primaryStage.setTitle("Agent Editor");
        primaryStage.setScene(new Scene(root, 400, 525));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
