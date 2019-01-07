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



    public ArrayList<Node> init(int type, GraphicsContext gc, String colorChoice, double x, double y, double endX, double endY, float radius, double height, double width){
        this.gc = gc;
        this.colorChoice = colorChoice;
        Node ret = null;

        ArrayList<Node> retArray = new ArrayList<Node>();


        //TODO switch case which calls different figurre methods (dreieck, quadrat etc))ss
        switch (type){
            case 1:
                if(x == 0.0 && y == 0.0) {
                    ret = line(0, 245, 800, 245, 1);
                }
                else{
                    ret = line(x,y, endX, endY, width);
                }
                retArray.add(ret);
                break;
            case 2:
                if(x == 0.0 && y == 0.0) {
                    ret = triangle(250, 200);
                }
                else{
                    ret = triangle(x,y);
                }
                retArray.add(ret);
                break;
            case 3:
                if(x == 0.0 && y == 0.0) {
                    ret = point(300, 150);
                }
                else{
                    ret = point(x,y);
                }
                retArray.add(ret);
                break;
            case 4:
                if(x == 0.0 && y == 0.0){
                    ret = square(275, 200, 200, 200);
                }
                else{
                    ret = square(x, y, width, height);        // TODO WIDTH DYNAMIC
                }
                retArray.add(ret);
                break;
            case 5:
                if(x == 0.0 && y == 0.0) {
                    ret = circle(400, 150, 50f);
                }
                else{
                    ret = circle(x,y, radius);
                }
                retArray.add(ret);
                break;
            case 6:
                if(x == 0.0 && y == 0.0) {
                    ret = rectangle(50, 50, 100, 200);
                }
                else{
                    ret = rectangle(x,y, height, width);
                }
                retArray.add(ret);
                break;
            case 7:
                if(x == 0.0 && y == 0.0) {
                    ret = semiCircle(400, 300);
                }
                else{
                    ret = semiCircle(x,y);
                }
                retArray.add(ret);
                break;
            case 8:
                if(x == 0.0 && y == 0.0) {
                    ret = oval(390.0f, 226.0f);
                }
                else{
                    ret = oval(x,y);
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



    private Node line(double x, double y, double endX, double endY, double width){
        System.out.println("line");
     //   this.gc.strokeLine(x,y,600,250);
     //   this.gc.setStroke(Color.valueOf(this.colorChoice));

        Line line = new Line();
        line.setStartX(x);
        line.setStartY(y);
        line.setStrokeWidth(width);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(Color.valueOf(this.colorChoice));


        return line;

    }

    private Node triangle(double x, double y){
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
        triangle.setStroke(Color.valueOf(this.colorChoice));

        System.out.println(triangle.toString());

        return triangle;
    }

    private Node point(double x, double y){
        System.out.println("point");
       /* gc.strokeOval(300,150, 10, 10);
        gc.setStroke(Color.valueOf(this.colorChoice));
        */

        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(x);
        ellipse.setCenterY(y);
        ellipse.setRadiusX(1);
        ellipse.setRadiusY(1);
        ellipse.setStroke(Color.valueOf(this.colorChoice));

        return ellipse;

    }

    private Node square(double x, double y, double width, double height){
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

        return rectangle;
    }

    private Node circle(double x, double y, float radius){
        System.out.println("circle");
        //this.gc.strokeOval(100,150,500,150);

        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.valueOf(this.colorChoice));

        return circle;
    }

    private Node rectangle(double x, double y, double height, double width) {
       // System.out.println("rectangle");

     /*   gc.strokeRect(200,250,50, 100);
        gc.fillRect(200,250,50,100);
        gc.setStroke(Color.valueOf(this.colorChoice));
     */

        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.valueOf(this.colorChoice));

        return r;
    }

    private Node semiCircle(double x, double y){
        Arc arc = new Arc(x, y, 200, 200, 0, 180);
        arc.setType(ArcType.OPEN);
     //   arc.setStrokeWidth(10);
        arc.setStroke(Color.valueOf(this.colorChoice));
        arc.setStrokeType(StrokeType.INSIDE);
        arc.setFill(null);

        return arc;
    }

    private ArrayList<Node> twoSquares(){
        ArrayList<Node>itemsDrawn = new ArrayList<Node>();
        Node ret = null;
        itemsDrawn.add(this.line(0,40, 800, 40, 4));

        itemsDrawn.add(this.square(1.0, 40, 400, 400));
        itemsDrawn.add(this.square(401, 40, 400,400));

        return itemsDrawn;
    }

    private Node oval(double x, double y){
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(x);
        ellipse.setCenterY(y);
        ellipse.setRotate(25);

        ellipse.setRadiusX(420.0f);
        ellipse.setRadiusY(125.0f);

        ellipse.setStroke(Color.valueOf(this.colorChoice));
        ellipse.setFill(Color.TRANSPARENT);

        return ellipse;
    }

}
