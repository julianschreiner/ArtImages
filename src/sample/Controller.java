package sample;

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
    @FXML private TextField startCurve;
    @FXML private Button drawButton;
    @FXML private Button clearButton;
    @FXML private Canvas drawArea;
    @FXML private ChoiceBox colorInput;
    @FXML private ChoiceBox choiceBox;
    @FXML private TextField startPos;
    @FXML private TextField layer;
    @FXML private TextField sizeOfSquare;

    // Size of the canvas for the Mandelbrot set
    private static final int CANVAS_WIDTH = 697;
    private static final int CANVAS_HEIGHT = 400;

    // Left and right border
    private static final int X_OFFSET = 25;

    // Top and Bottom border
    private static final int Y_OFFSET = 25;


    // VALUES FOR CURVE POINTS
    private static final double[] a = {0, 0.5, 0.5, 0},
                                  b = {0.5, 0, 0, -0.5},
                                  c = {0.5, 0, 0, -0.5},
                                  d = {0, 0.5, 0.5, 0},
                                  e = {0, 0, 0.5, 1},
                                  f = {0, 0.5, 0.5, 0.5};



    public Main main;
    private ArrayList<Node> itemsDrawn;
    private Coordinates points;


    private Figures figures = new Figures();


    public void setMain(Main main){
        this.main = main;
        initialize();
    }

    @FXML
    private void initialize(){
        drawArea.setHeight(CANVAS_HEIGHT);
        drawArea.setWidth(CANVAS_WIDTH);
        drawArea.setLayoutX(X_OFFSET);
        drawArea.setLayoutY(Y_OFFSET);

        ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10");
        choiceBox.setItems(list);

        ObservableList<String> list2 = FXCollections.observableArrayList("BLUE","BLACK","GREEN","PURPLE","RED","BROWN","CYAN","GREY","PINK","LIME");
        colorInput.setItems(list2);

        itemsDrawn = new ArrayList<Node>();
    }

    @FXML
    public void testMethod(){
        //drawShapes(gc);
        GraphicsContext gc = drawArea.getGraphicsContext2D();
        String userChoice = choiceBox.getValue().toString();
        String colorChoice;

        // FALLBACK COLOR
        if(colorInput.getValue() != null){
            colorChoice = colorInput.getValue().toString();
        }
        else{
            colorChoice = "BLACK";
        }

        Double startPosX;
        Double startPosY;

        if(!startPos.getText().isEmpty()){
            String[] coordinates = startPos.getText().split(",");

            startPosX = Double.parseDouble(coordinates[0]);
            startPosY = Double.parseDouble(coordinates[1]);
        }
        else{
            startPosX = 0.0;
            startPosY = 0.0;
            System.out.println("No starting point provided");
        }



        ArrayList<Node> ret = figures.init(Integer.parseInt(userChoice), gc, colorChoice, startPosX, startPosY, 0.0, 0.0);

        for (Node item: ret) {
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

    }

    @FXML
    public void clearCanvas() {

        System.out.println("pressed");

        /*
        for (int i = 0; i < 150; i++) {
            double randomX = Math.random() * 750 + 50;
            double randomY = Math.random() * 900 + 100;

            calculatePoint(randomX, randomY, 3);
        }
        */
        drawTree(400, 500, -90, 9);


        for (Node item : itemsDrawn) {
      //      main.getRoot().getChildren().remove(item);
        }

    }


    private void calculatePoint(double i, double j, int type){

        double iNew = 0.0;
        double jNew = 0.0;
        Coordinates[] retArray = new Coordinates[4];


        // CALCULATE 4 POINTS
        for(int k = 0; k < 4; k++){
            iNew = 800 * (a[k] * i / 800 + b[k] * j/800 + e[k]);
            jNew = 800 * (c[k] * i / 800 + d[k] * j/800 + f[k]);


            if(iNew >= 770){
                iNew = 520;
            }

            if(jNew >= 420){
                jNew = 420 ;
            }


            points = new Coordinates(iNew, jNew);

            retArray[k] = points;
        }




        // DRAWING
        GraphicsContext gc = drawArea.getGraphicsContext2D();

        ArrayList<Node> lines = figures.init(1, gc, "GREEN", retArray[0].getX(),retArray[0].getY(), retArray[1].getX(), retArray[1].getY());
        lines.addAll(figures.init(1,gc, "GREEN", retArray[2].getX(), retArray[2].getY(), retArray[3].getX(), retArray[3].getY()));

        lines.addAll(figures.init(1,gc, "GREEN", retArray[0].getX(), retArray[0].getY(), retArray[3].getX(), retArray[3].getY()));
        lines.addAll(figures.init(1,gc, "GREEN", retArray[1].getX(), retArray[1].getY(), retArray[2].getX(), retArray[2].getY()));




        ArrayList<Node> ret = new ArrayList<>();

        for(Coordinates item : retArray){
          //  System.out.println(item.toString());

            double x = item.getX();
            double y = item.getY();

           ret.addAll(figures.init(type, gc, "BLACK", x, y, 0.0, 0.0));

        }

        // MERGE 2 ARRAYLIST'S
        // ret.addAll(lines);

        for (Node line: lines){
            if (!ret.contains(line))
                ret.add(line);
        }

        for (Node itemm: ret) {
            System.out.println("ITEM: " +  itemm.toString());
            this.itemsDrawn.add(itemm);
            main.getRoot().getChildren().add(itemm);
        }


    }

    private void drawTree(int x1, int y1, double angle, int depth) {
        if (depth == 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);

        GraphicsContext gc = drawArea.getGraphicsContext2D();
        ArrayList<Node> lines = figures.init(1, gc, "BLACK", x1,y1, x2, y2);


        for (Node itemm: lines) {
          //  System.out.println("ITEM: " +  itemm.toString());
            this.itemsDrawn.add(itemm);
            main.getRoot().getChildren().add(itemm);
        }

        drawTree(x2, y2, angle - 20, depth - 1);
        drawTree(x2, y2, angle + 20, depth - 1);
    }


}
