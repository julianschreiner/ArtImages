package sample;

import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.*;
import javafx.collections.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.shape.Line;

public class Controller {
    // VIEWS
    @FXML
    private TextField startCurve;
    @FXML
    private Button drawButton;
    @FXML
    private Button clearButton;
    @FXML
    private Canvas drawArea;
    @FXML
    private ChoiceBox colorInput;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField startPos;
    @FXML
    private TextField layer;
    @FXML
    private ChoiceBox sizeOfType;

    // Size of the canvas for the Mandelbrot set
    private static final int CANVAS_WIDTH = 697;
    private static final int CANVAS_HEIGHT = 400;

    // Left and right border
    private static final int X_OFFSET = 25;

    // Top and Bottom border
    private static final int Y_OFFSET = 25;


    // Values for the Mandelbro set
    private static double MANDELBROT_RE_MIN = -2;
    private static double MANDELBROT_RE_MAX = 1;
    private static double MANDELBROT_IM_MIN = -1.2;
    private static double MANDELBROT_IM_MAX = 1.2;


    public Main main;
    private ArrayList<Node> itemsDrawn;


    private Figures figures = new Figures();


    public void setMain(Main main) {
        this.main = main;
        initialize();
    }

    @FXML
    private void initialize() {
        drawArea.setHeight(CANVAS_HEIGHT);
        drawArea.setWidth(CANVAS_WIDTH);
        drawArea.setLayoutX(X_OFFSET);
        drawArea.setLayoutY(Y_OFFSET);
        ObservableList<String> list = FXCollections.observableArrayList("1.Line", "2.Triangle", "3.Point", "4.Square", "5.Circle", "6.Rectangle", "7.SemiCircle", "8.Oval", "9", "10");
        choiceBox.setItems(list);

        ObservableList<String> list2 = FXCollections.observableArrayList("BLUE", "BLACK", "GREEN", "PURPLE", "RED", "BROWN", "CYAN", "GREY", "PINK", "LIME");
        colorInput.setItems(list2);

        ObservableList<String> list3 = FXCollections.observableArrayList("Small", "Medium", "Large");
        sizeOfType.setItems(list3);

        itemsDrawn = new ArrayList<Node>();
    }

    @FXML
    public void testMethod() {
        //drawShapes(gc);
        GraphicsContext gc = drawArea.getGraphicsContext2D();
        String userChoice = choiceBox.getValue().toString();
        String colorChoice;

        // FALLBACK COLOR
        if (colorInput.getValue() != null) {
            colorChoice = colorInput.getValue().toString();
        } else {
            colorChoice = "BLACK";
        }

        Double startPosX;
        Double startPosY;

        if (!startPos.getText().isEmpty()) {
            String[] coordinates = startPos.getText().split(",");

            startPosX = Double.parseDouble(coordinates[0]);
            startPosY = Double.parseDouble(coordinates[1]);
        } else {
            startPosX = 0.0;
            startPosY = 0.0;
            System.out.println("No starting point provided");
        }


        int size = 0;
        String sizeChoice = sizeOfType.getValue().toString();

        if (sizeChoice == "Small") {
            size = 1;
        }
        if (sizeChoice == "Medium") {
            size = 2;
        }
        if (sizeChoice == "Large") {
            size = 3;
        }


        int typeChoice = Integer.parseInt("" + userChoice.charAt(0));


        ArrayList<Node> ret = figures.init(typeChoice, gc, colorChoice, startPosX, startPosY, size);

        for (Node item : ret) {
            System.out.println(item);
            this.itemsDrawn.add(item);
            System.out.println(itemsDrawn.toString());
            main.getRoot().getChildren().add(item);
        }



        /*   Example calling one draw function often */
/*
        double x = 0;
        double y  = 0;

        for (int i = 0; i < 100 ; i++) {


            if(i > 50){
                x = 50 + i * 10;
                y = 50 + i * 15;
            }
            else{
                x = 500 - i * 10;
                y = 600 - i * 15;
            }

            if(y > 280){
                y = 250;
            }

            Node ret2 = figures.init(4,gc,colorChoice,x,y);
            main.getRoot().getChildren().add(ret2);

            this.itemsDrawn.add(ret2);
        }
        */


            //TODO
            drawArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override

                public void handle(MouseEvent event) {
                    System.out.println("X" + event.getSceneX());
                    System.out.println("Y" + event.getSceneY());


                }
            });

    }

    @FXML
    public void clearCanvas() {
        System.out.println("pressed");

        for (Node item : itemsDrawn) {
            main.getRoot().getChildren().remove(item);
        }
    }




}
