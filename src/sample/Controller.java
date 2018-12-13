package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import java.util.*;
import javafx.collections.*;


public class Controller {
    // VIEWS
    @FXML private TextField startCurve;
    @FXML private Button drawButton;
    @FXML private Canvas drawArea;
    @FXML private ColorPicker colorInput;
    @FXML private ChoiceBox choiceBox;


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
    private Figures figures;


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
    }

    @FXML
    public void testMethod(){
       /* String color = colorInput.getText();
        int start_curve = Integer.parseInt(startCurve.getText());

        System.out.println("Color: " + color);
        System.out.println("Start Kurve: " + start_curve);
       */

        //GraphicsContext gc = drawArea.getGraphicsContext2D();
        //drawShapes(gc);
        String userChoice = choiceBox.getValue().toString();


        start();
    }

    public void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
    }


    public void start() {
        paintSet(drawArea.getGraphicsContext2D(),
                MANDELBROT_RE_MIN,
                MANDELBROT_RE_MAX,
                MANDELBROT_IM_MIN,
                MANDELBROT_IM_MAX);

    }

    private void paintSet(GraphicsContext ctx, double reMin, double reMax, double imMin, double imMax) {
        double precision = Math.max((reMax - reMin) / CANVAS_WIDTH, (imMax - imMin) / CANVAS_HEIGHT);
        int convergenceSteps = 50;
        for (double c = reMin, xR = 0; xR < CANVAS_WIDTH; c = c + precision, xR++) {
            for (double ci = imMin, yR = 0; yR < CANVAS_HEIGHT; ci = ci + precision, yR++) {
                double convergenceValue = checkConvergence(ci, c, convergenceSteps);
                double t1 = (double) convergenceValue / convergenceSteps;
                double c1 = Math.min(255 * 2 * t1, 255);
                double c2 = Math.max(255 * (2 * t1 - 1), 0);

                if (convergenceValue != convergenceSteps) {
                    ctx.setFill(Color.color(c2 / 255.0, c1 / 255.0, c2 / 255.0));
                } else {
                    ctx.setFill(colorInput.getValue()); // Convergence Color
                }
                ctx.fillRect(xR, yR, 1, 1);
            }
        }
    }

    private int checkConvergence(double ci, double c, int convergenceSteps) {
        double z = 0;
        double zi = 0;
        for (int i = 0; i < convergenceSteps; i++) {
            double ziT = 2 * (z * zi);
            double zT = z * z - (zi * zi);
            z = zT + c;
            zi = ziT + ci;

            if (z * z + zi * zi >= 4.0) {
                return i;
            }
        }
        return convergenceSteps;
    }
}
