package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Main extends Application {
    private static final int CANVAS_WIDTH = 697;
    private static final int CANVAS_HEIGHT = 400;

    private Stage primaryStage;
    private AnchorPane pane;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        mainWindow();


    }

    public void mainWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
            pane = loader.load();

            pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override

                public void handle(MouseEvent event) {
                    System.out.println("X: " + event.getSceneX());
                    System.out.println("Y: " + event.getSceneY());


                }
            });

            // TODO SET PANE HEIGHT

            // TODO CANVAS RAHMEN BORDER

            pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


            // primaryStage.setMinHeight(800.00);
            // primaryStage.setMinWidth(600.00);
            primaryStage.setTitle("Java Project");



            Controller windowController = loader.getController();
            windowController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.show();


        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    public AnchorPane getRoot(){
        return this.pane;
    }


}
