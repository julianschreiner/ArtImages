package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        Rectangle rectangle = new Rectangle();

        rectangle.setX(100.0);
        rectangle.setY(150.0);
        rectangle.setWidth(500.0);
        rectangle.setHeight(150.0);

        Group root2 = new Group(rectangle);
        primaryStage.setScene(new Scene(root2, 300, 275));


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
