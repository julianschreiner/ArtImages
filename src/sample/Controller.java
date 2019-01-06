package sample;

import javafx.application.Platform;
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
import java.util.concurrent.TimeUnit;

import javafx.collections.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.shape.Line;

import static java.lang.Thread.sleep;

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
    private static final double CANVAS_WIDTH = 697;
    private static final double CANVAS_HEIGHT = 400;

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



    private static final String POINTALG = "Points (Logofatu Alg.)";
    private static final String TREE = "Tree";
    private static final String CIRCLE = "Endless Circle";
    private static final String SPONGE = "Sponge";
    private static final String ROTSQUARE = "Squares";


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

        ObservableList<String> list = FXCollections.observableArrayList(POINTALG,TREE,CIRCLE,SPONGE, ROTSQUARE);
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

        Integer curveStart;

        if(startCurve.getText().isEmpty()){
            curveStart = 5;
        }
        else{
            curveStart = Integer.parseInt(startCurve.getText().toString());
        }
        System.out.println("Curve Start: " + curveStart);



        switch(userChoice){
            case POINTALG:
                calculatePoint(800, 500, 3, 800);
                break;
            case TREE:
                drawTree(380, 300, -90, 9);
                break;
            case CIRCLE:
                drawCircle(380,260, 190.0f);
                break;
            case SPONGE:
                double size = CANVAS_WIDTH > CANVAS_HEIGHT ? (int) (CANVAS_HEIGHT * 0.8) : (int) (CANVAS_WIDTH * 0.8);
                sponge(5,size, size, CANVAS_WIDTH / 2 - size / 2, CANVAS_HEIGHT / 2 - size / 2);
                break;
            case ROTSQUARE:
                double sizeRotSquare = CANVAS_WIDTH > CANVAS_HEIGHT ? CANVAS_HEIGHT / 3 : CANVAS_WIDTH / 3;
                System.out.println("First Drawing with count 1");
                rotatedSquare(
                        CANVAS_WIDTH / 2 - sizeRotSquare / 2,
                        CANVAS_HEIGHT / 2 - sizeRotSquare / 2,
                        sizeRotSquare,
                        sizeRotSquare,
                        1);

                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int i = 1;
                                              @Override
                                              public void run() {
                                                  Platform.runLater(() -> {
                                                      // your code here
                                                      System.out.println("Count: " + i++);

                                                      if(i > 7){
                                                          timer.cancel();
                                                          timer.purge();
                                                      }

                                                      rotatedSquare(
                                                              CANVAS_WIDTH / 2 - sizeRotSquare / 2,
                                                              CANVAS_HEIGHT / 2 - sizeRotSquare / 2,
                                                              sizeRotSquare,
                                                              sizeRotSquare,
                                                              i++);

                                                  });
                                              }
                                          },  5000,5000
                );



            default:
                break;
        }



     //   ArrayList<Node> ret = figures.init(Integer.parseInt(userChoice), gc, colorChoice, startPosX, startPosY, 0.0, 0.0, 0, 0 ,0); // TODO HEIGHT WIDTH
/*
        for (Node item: ret) {
            System.out.println(item);
            this.itemsDrawn.add(item);
            System.out.println(itemsDrawn.toString());
            main.getRoot().getChildren().add(item);
        }
*/



    }

    @FXML
    public void clearCanvas() {

        System.out.println("CLEARING...");
/*
        CustomThread t1 = new CustomThread("ABC THREAD");

        t1.start();
*/


   //


        for (Node item : itemsDrawn) {
            main.getRoot().getChildren().remove(item);
        }
    }


    private void calculatePoint(double i, double j, int type, double calc){

        double iNew = 0.0;
        double jNew = 0.0;
        Coordinates[] retArray = new Coordinates[4];


        // CALCULATE 4 POINTS
        for(int k = 0; k < 4; k++){
            iNew = calc * (a[k] * i / calc + b[k] * j/calc + e[k]);
            jNew = calc * (c[k] * i / calc + d[k] * j/calc + f[k]);


            iNew += (double) (Math.cos(Math.toRadians(-90)) * 6 * 5.0);
            jNew += (double) (Math.cos(Math.toRadians(-90)) * 5 * 5.0);


            if(iNew >= 770){
                iNew = Math.random() * 770 + 20;
            }

            if(jNew >= 420){
                jNew = Math.random() * 420 + 1;
            }



            points = new Coordinates(iNew, jNew);

            retArray[k] = points;
        }




        // DRAWING
        GraphicsContext gc = drawArea.getGraphicsContext2D();

        ArrayList<Node> lines = figures.init(1, gc, "GREEN", retArray[0].getX(),retArray[0].getY(), retArray[1].getX(), retArray[1].getY(),0, 0, 0);
        lines.addAll(figures.init(1,gc, "GREEN", retArray[2].getX(), retArray[2].getY(), retArray[3].getX(), retArray[3].getY(),0, 0, 0));

        lines.addAll(figures.init(1,gc, "GREEN", retArray[0].getX(), retArray[0].getY(), retArray[3].getX(), retArray[3].getY(), 0, 0, 0));
        lines.addAll(figures.init(1,gc, "GREEN", retArray[1].getX(), retArray[1].getY(), retArray[2].getX(), retArray[2].getY(), 0, 0, 0));




        ArrayList<Node> ret = new ArrayList<>();

        for(Coordinates item : retArray){
          //  System.out.println(item.toString());

            double x = item.getX();
            double y = item.getY();

           ret.addAll(figures.init(type, gc, "BLACK", x, y, 0.0, 0.0, 0, 0, 0));

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
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 5.0);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 5.0);

        GraphicsContext gc = drawArea.getGraphicsContext2D();
        ArrayList<Node> lines = figures.init(1, gc, "BLACK", x1,y1, x2, y2, 0, 0, 0);


        for (Node itemm: lines) {
          //  System.out.println("ITEM: " +  itemm.toString());
            this.itemsDrawn.add(itemm);
            main.getRoot().getChildren().add(itemm);
        }

        drawTree(x2, y2, angle - 20, depth - 1);
        drawTree(x2, y2, angle + 20, depth - 1);
    }

    private void drawCircle(double x, double y, float radius){
        GraphicsContext gc = drawArea.getGraphicsContext2D();
        ArrayList<Node> lines = figures.init(5, gc, "BLACK", x,y, 0, 0, radius, 0,0);

        for (Node itemm: lines) {
            //  System.out.println("ITEM: " +  itemm.toString());
            this.itemsDrawn.add(itemm);
            main.getRoot().getChildren().add(itemm);
        }

        if(radius > 2) {
            radius *= 0.75f;
           // drawCircle(x, y, radius);

            /*
            drawCircle(x + radius/2, y, radius/2);
            drawCircle(x - radius/2, y, radius/2);
            */

            drawCircle(x + radius/2, y, radius/2);
            drawCircle(x - radius/2, y, radius/2);
            drawCircle(x, y + radius/2, radius/2);
            drawCircle(x, y - radius/2, radius/2);

            //x 380 - y 260
            // + 150  - 150
            // 530     110

            drawCircle(x + radius/2 + 150, y, radius/2);
            drawCircle(x - radius/2 + 150, y, radius/2);
            drawCircle(x, y + radius/2 - 150, radius/2);
            drawCircle(x, y - radius/2 - 150, radius/2);


            drawCircle(x + radius/2 - 150, y, radius/2);
            drawCircle(x - radius/2 - 150, y, radius/2);
            drawCircle(x, y + radius/2 + 50, radius/2);
            drawCircle(x, y - radius/2 + 50, radius/2);


        }

    }

    private void sponge(int count, double width, double height, double x, double y) {
        System.out.println(x + " , " + y);
        GraphicsContext gc = drawArea.getGraphicsContext2D();
        if(count < 1) {
           // g.drawRect(r.x, r.y, r.width, r.height);

            ArrayList<Node> lines = figures.init(6, gc, "BLACK", x,y, 0, 0, 0, height, width);

            for (Node itemm: lines) {
                //  System.out.println("ITEM: " +  itemm.toString());
                this.itemsDrawn.add(itemm);
                main.getRoot().getChildren().add(itemm);
            }

            return;
        }

        double width2 = width / 3;
        double height2 = height / 3;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!(i == 1 && j == 1)) {
                   // fractal(new Rectangle(r.x + (int) (i * width), r.y + (int) (j * height), (int) width, (int) height), count - 1);

                    sponge(count - 1, width2, height2, x + (int) (i * width2), y + (int) (j * height2));
                }
            }
        }
    }


    private void rotatedSquare(double x, double y, double width, double height, int count){
        //g.drawRect(r.x, r.y, r.width, r.height);
        GraphicsContext gc = drawArea.getGraphicsContext2D();

        ArrayList<Node> rectangles = figures.init(6, gc, "BLACK", x,y, 0, 0, 0, height, width);

        for (Node item: rectangles) {
            this.itemsDrawn.add(item);
            this.main.getRoot().getChildren().add(item);
        }

        if(count < 1)
            return;

        double newSize = width / 2;

        rotatedSquare(x - newSize / 2, y, newSize, newSize, count - 1);
        rotatedSquare(x + width - newSize, y - newSize / 2, newSize, newSize, count - 1);
        rotatedSquare(x + width - newSize / 2, y + height - newSize, newSize, newSize, count - 1);
        rotatedSquare(x, y + height - newSize / 2, newSize, newSize, count - 1);
    }
}
