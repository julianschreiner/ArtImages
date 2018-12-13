package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;



public class Figures {
    private Figure figure;
    private Polygon poly;

    public void init(int type, GraphicsContext gc){
        //TODO switch case which calls different figurre methods (dreieck, quadrat etc)
        switch (type){
            case 1:
                line(gc);
                break;
            default:
                // THROW ERROR
                break;
        }
    }



    private void line(GraphicsContext gc){
        System.out.println("line");
        gc.strokeLine(200,250,600,250);
    }

    private void mandelbrot(){}


}
