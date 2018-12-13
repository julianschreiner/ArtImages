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
            case 2:
                triangle(gc);
            case 3:
                point(gc);
                break;
            case 4:
                square(gc);
                break;
            case 5:
                circle(gc);
                break;
            case 6:
                rectangle(gc);
                break;

                break;

            default:
                // THROW ERRORsssadsad
                break;
        }
    }



    private void line(GraphicsContext gc){
        System.out.println("line");
        gc.strokeLine(200,250,600,250);
    }
    private void circle(GraphicsContext gc){
        System.out.println("circle");
        gc.strokeOval(100,150,500,150);
    }
    private void triangle(GraphicsContext gc){
        System.out.println("triangle");
        gc.beginPath();
        gc.moveTo(30.5, 30.5);
        gc.lineTo(150.5, 30.5);
        gc.lineTo(150.5, 150.5);
        gc.lineTo(30.5, 30.5);
        gc.stroke();
    }



    private void point(GraphicsContext gc)
    {

        System.out.println("point");
        gc.strokeOval(200,250, 10, 10);
        gc.fillOval(200,250,10,10);
    }

    private void square(GraphicsContext gc)
    {
        System.out.println("square");
        gc.strokeRect(200,250,50,50);

    }

    private void rectangle(GraphicsContext gc)
    {
        System.out.println("rectangle");
        gc.strokeRect(200,250,50, 100);
        gc.fillRect(200,250,50,100);
    }
    private void mandelbrot(){}


}
