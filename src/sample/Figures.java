package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;



public class Figures {
    private Figure figure;
    private Polygon poly;

    public void init(int type, GraphicsContext gc, String colorChoice){
        //TODO switch case which calls different figurre methods (dreieck, quadrat etc)
        switch (type){
            case 1:
                line(gc, colorChoice);
                break;
            case 2:
                triangle(gc, colorChoice);
                break;
            case 3:
                point(gc, colorChoice);
                break;
            case 4:
                square(gc, colorChoice);
                break;
            case 5:
                circle(gc, colorChoice);
                break;
            case 6:
                rectangle(gc,colorChoice);
                break;


            default:
                // THROW ERRORsssadsad
                break;
        }
    }



    private void line(GraphicsContext gc, String color){
        System.out.println("line");
        gc.strokeLine(200,250,600,250);
        gc.setStroke(Color.valueOf(color));
    }
    private void circle(GraphicsContext gc, String color){
        System.out.println("circle");
        gc.strokeOval(100,150,500,150);
        gc.setStroke(Color.valueOf(color));
    }
    private void triangle(GraphicsContext gc, String color){
        System.out.println("triangle");
        gc.beginPath();
        gc.moveTo(30.5, 30.5);
        gc.lineTo(150.5, 30.5);
        gc.lineTo(150.5, 150.5);
        gc.lineTo(30.5, 30.5);
        gc.setStroke(Color.valueOf(color));
        gc.stroke();
    }



    private void point(GraphicsContext gc, String color)
    {

        System.out.println("point");
        gc.strokeOval(200,250, 10, 10);
        gc.fillOval(200,250,10,10);
        gc.setStroke(Color.valueOf(color));
    }

    private void square(GraphicsContext gc, String color)
    {
        System.out.println("square");
        gc.strokeRect(200,250,50,50);
        gc.setStroke(Color.valueOf(color));

    }

    private void rectangle(GraphicsContext gc, String color)
    {
        System.out.println("rectangle");
        gc.strokeRect(200,250,50, 100);
        gc.fillRect(200,250,50,100);
        gc.setStroke(Color.valueOf(color));
    }
    private void mandelbrot(){}


}
