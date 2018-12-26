package sample;/**
 * Advanced Object Oriented Programming with Java, WS 2018 * Problem: Coordinates
 * Link: PDF Exercises
 *
 * @author Julian Schreiner
 * @version 1.0, 10/23/2018
 * Method : Coordinates
 * Status : Accepted
 * Runtime: 0.102
 */

import java.util.*;

public class Coordinates {
    private double x;
    private double y;

    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
    }

    public  double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public  double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String toString(){
        return Double.toString(this.x) + " , " + Double.toString(this.y);
    }
}
