package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;


public class Controller {
    // VIEWS
    @FXML private TextField colorInput, startCurve;
    @FXML private Button drawButton;
    @FXML private Canvas drawArea;


    public Main main;


    public void setMain(Main main){
        this.main = main;
    }

    @FXML
    public void testMethod(){
       /* String color = colorInput.getText();
        int start_curve = Integer.parseInt(startCurve.getText());

        System.out.println("Color: " + color);
        System.out.println("Start Kurve: " + start_curve);
       */

        GraphicsContext gc = drawArea.getGraphicsContext2D();
        drawShapes(gc);
    }

    public void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
    }
}
