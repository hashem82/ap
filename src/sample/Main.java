package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PAINT_IO");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("pic/page2.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
