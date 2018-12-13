package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Figures {
    private Figure figure;
    private Polygon poly;

    public void init(int type, GraphicsContext gc, double x1, double y1, double x2, double y2){
        //TODO switch case which calls different figurre methods (dreieck, quadrat etc)
        switch (type){
            case 1:
                line(gc,x1,y1,x2,y2);

                break;
            default:
                // THROW ERRORsssadsad
                break;
        }
    }



    private void line(GraphicsContext gc, double x1, double y1, double x2, double y2){
        System.out.println("line");
        gc.strokeLine(x1,y1,x2,y2);
    }

    private void mandelbrot(){}


}
