package sample;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;

import java.util.ArrayList;


public class Figures {
    private Figure figure;
    private Polygon poly;
    private GraphicsContext gc;
    private String colorChoice;
    private static final double CANVAS_WIDTH = 697;
    private static final double CANVAS_HEIGHT = 400;



    public ArrayList<Node> init(int type, GraphicsContext gc, String colorChoice, double x, double y, int size){
        this.gc = gc;
        this.colorChoice = colorChoice;
        Node ret = null;

        ArrayList<Node> retArray = new ArrayList<Node>();


        //TODO switch case which calls different figurre methods (dreieck, quadrat etc))
        switch (type){
            case 1:
                if(x == 0.0 && y == 0.0) {
                    ret = line(0, 245, 800, 245, size);
                }
                else{
                    ret = line(x,y, 800, 245, size);  // TODO endX, endY dynamic
                }

                retArray.add(ret);
                break;
            case 2:
                if(x == 0.0 && y == 0.0) {
                    ret = triangle(250, 200, size);
                }
                else{
                    ret = triangle(x ,y, size );
                }
                retArray.add(ret);
                break;
            case 3:
                if(x == 0.0 && y == 0.0) {
                    ret = point(300, 150, size);
                }
                else{
                    ret = point( x , y , size);
                }
                retArray.add(ret);
                break;
            case 4:
                if(x == 0.0 && y == 0.0){
                    ret = square(275, 200, 200, 200, size);
                }
                else{
                    ret = square(x, y, 200, 200, size);        // TODO WIDTH DYNAMIC
                }
                retArray.add(ret);
                break;
            case 5:
                if(x == 0.0 && y == 0.0) {
                    ret = circle(400, 150, size);
                }
                else{
                    ret = circle(x,y, size);
                }
                retArray.add(ret);
                break;
            case 6:
                if(x == 0.0 && y == 0.0) {
                    ret = rectangle(50, 50, size);
                }
                else{
                    ret = rectangle(x,y, size);
                }
                retArray.add(ret);
                break;
            case 7:
                if(x == 0.0 && y == 0.0) {
                    ret = semiCircle(400, 300, size);
                }
                else{
                    ret = semiCircle(x,y, size);
                }
                retArray.add(ret);
                break;
            case 8:
                if(x == 0.0 && y == 0.0) {
                    ret = oval(400, 300, size);
                }
                else{
                    ret = oval(x,y, size);
                }
                retArray.add(ret);
                break;
            case 9:
               retArray = twoSquares();
               break;
            default:
                // THROW ERROR
                break;
        }


        return retArray;
    }



    private Node line(double x, double y, double endX, double endY, int size){
        System.out.println("line");
     //   this.gc.strokeLine(x,y,600,250);
     //   this.gc.setStroke(Color.valueOf(this.colorChoice));

        Line line = new Line();
        line.setStartX(x);
        line.setStartY(y);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStrokeWidth(5.0 * size);
        line.setStroke(Color.valueOf(this.colorChoice));


        return line;

    }

    private Node triangle(double x, double y, int size){
        System.out.println("triangle");

       /* this.gc.beginPath();
        this.gc.moveTo(30.5, 30.5);
        this.gc.lineTo(150.5, 30.5);
        this.gc.lineTo(150.5, 150.5);
        this.gc.lineTo(30.5, 30.5);
        this.gc.setStroke(Color.valueOf(this.colorChoice));
        this.gc.stroke();
        */
        // Create the Triangle

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(x, y,  x-50, y+150, x+50, y+150);
        triangle.setFill(Color.TRANSPARENT);
        triangle.setStrokeWidth(5.0 * size);
        triangle.setStroke(Color.valueOf(this.colorChoice));

        System.out.println(triangle.toString());

        return triangle;
    }

    private Node point(double x, double y, int size){
        System.out.println("point");
       /* gc.strokeOval(300,150, 10, 10);
        gc.setStroke(Color.valueOf(this.colorChoice));
        */
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(x);
        ellipse.setCenterY(y);
        ellipse.setRadiusX(10);
        ellipse.setRadiusY(10);
        ellipse.setStrokeWidth(5.0 * size);
        ellipse.setStroke(Color.valueOf(this.colorChoice));

        return ellipse;

    }

    private Node square(double x, double y, double width, double height, int size){
        System.out.println("square");
       // gc.strokeRect(x,y,50,50);
       // gc.setStroke(Color.valueOf(this.colorChoice));

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setStroke(Color.valueOf(this.colorChoice));
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(5.0 * size);
        return rectangle;
    }

    private Node circle(double x, double y, int size){
        System.out.println("circle");
        //this.gc.strokeOval(100,150,500,150);

        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(50.0f);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.valueOf(this.colorChoice));
        circle.setStrokeWidth(5.0 * size);
        return circle;
    }

    private Node rectangle(double x, double y, int size) {
        System.out.println("rectangle");

     /*   gc.strokeRect(200,250,50, 100);
        gc.fillRect(200,250,50,100);
        gc.setStroke(Color.valueOf(this.colorChoice));
     */

        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(200);
        r.setHeight(100);
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.valueOf(this.colorChoice));
        r.setStrokeWidth(5.0 * size);
        return r;
    }

    private Node semiCircle(double x, double y, int size){
        Arc arc = new Arc(x, y, 200, 200, 0, 180);
        arc.setType(ArcType.OPEN);
     //   arc.setStrokeWidth(10);
        arc.setStroke(Color.valueOf(this.colorChoice));
        arc.setStrokeType(StrokeType.INSIDE);
        arc.setFill(null);
        arc.setStrokeWidth(5.0 * size);
        return arc;
    }

    private ArrayList<Node> twoSquares(){
        ArrayList<Node>itemsDrawn = new ArrayList<Node>();
        Node ret = null;
        itemsDrawn.add(this.line(0,40, 800, 40, 1));

        itemsDrawn.add(this.square(1.0, 40, 400, 400, 1));
        itemsDrawn.add(this.square(401, 40, 400,400, 1));

        return itemsDrawn;
    }

    private Node oval(double x, double y, int size){
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(390.0f);
        ellipse.setCenterY(226.0f);
        ellipse.setRotate(25);

        ellipse.setRadiusX(420.0f);
        ellipse.setRadiusY(125.0f);

        ellipse.setStroke(Color.valueOf(this.colorChoice));
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStrokeWidth(5.0 * size);
        return ellipse;
    }

    private void mandelbrot(){}


}
