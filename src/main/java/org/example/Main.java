package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    interface Drawable{
        void draw();
    }

    abstract static class Shape implements Drawable{
        String shapeColor;

        public Shape(String shapeColor)
        {
            this.shapeColor = shapeColor;
        }
        public String getShapeColor(){
            return shapeColor;
        }
        abstract public double calcArea();
        @Override
        public String toString(){
            return "Shape color:" + shapeColor;
        }

    }

    static class Rectangle extends Shape{
        double width;
        double height;
        public Rectangle(String shapeColor, double width, double height){
            super(shapeColor);
            this.width = width;
            this.height = height;
        }
        @Override
        public double calcArea() {
            return width * height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing rectangle with color: " + shapeColor);
        }
        @Override
        public String toString() {
            return "Rectangle with color: " + shapeColor + ". Width: " + width + ". Height: " + height;
        }
    }
    static class Triangle extends Shape {
        double base, height;

        public Triangle(String shapeColor, double base, double height) {
            super(shapeColor);
            this.base = base;
            this.height = height;
        }

        @Override
        public double calcArea() {
            return (base * height) / 2;
        }

        @Override
        public void draw() {
            System.out.println("Drawing triangle with color: " + shapeColor);
        }

        @Override
        public String toString() {
            return "Triangle with color: " + shapeColor + ". Base: " + base + ". Height: " + height;
        }
    }
    class Circle extends Shape{
        double radius;
        public Circle(String shapeColor, double radius){
            super(shapeColor);
            this.radius = radius;
        }
        @Override
        public double calcArea() {
            return Math.PI * radius * radius;
        }
        @Override
        public void draw() {
            System.out.println("Drawing circle with color: " + shapeColor);
        }
        @Override
        public String toString() {
            return "Circle with color: " + shapeColor + ". Radius: " + radius;
        }

    }
    class ShapeCollection{
        Shape[] shapes;
        public ShapeCollection(Shape[] shapes){
            this.shapes = shapes;
        }

        @Override
        public String toString(){
            double totalArea = 0;
            double totalRectangle = 0;
            double totalTriangle = 0;
            double totalCircle = 0;
            for (Shape shape : shapes){
                totalArea += shape.calcArea();
                if (shape instanceof Rectangle){
                    totalRectangle += shape.calcArea();
                }
                if (shape instanceof Triangle){
                    totalTriangle += shape.calcArea();
                }
                if (shape instanceof Circle){
                    totalCircle += shape.calcArea();
                }
            }
            return "Total area of all shapes: " +totalArea + ". " +
                    "\nTotal area of rectangles: " + totalRectangle + ". " +
                    "\nTotal area of triangles: " + totalTriangle + ". "  +
                    "\nTotal area of circles: " + totalCircle;
        }

        public String compareArea() {
            Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
            StringBuilder result = new StringBuilder("\nShapes sorted by area:\n");
            for (Shape shape : shapes) {
                result.append(shape.toString()).append("\n");
            }
            return result.toString();
        }
        public String compareShapeColor() {
            Arrays.sort(shapes, Comparator.comparing(Shape::getShapeColor));
            StringBuilder result = new StringBuilder("Shapes sorted by color:\n");
            for (Shape shape : shapes) {
                result.append(shape.toString()).append("\n");
            }
            return result.toString();
        }
    }
    public static void main(String[] args) {
        Main shape = new Main();
        Shape[] shapes = new Shape[10];
        shapes[0] = new Rectangle("Red", 4.0, 5.0);
        shapes[1] = shape.new Circle("Blue", 3.0);
        shapes[2] = new Triangle("Green", 3.0, 4.0);
        shapes[3] = new Rectangle("Yellow", 6.0, 7.0);
        shapes[4] = shape.new Circle("Black", 2.0);
        shapes[5] = new Triangle("White", 5.0, 6.0);
        shapes[6] = new Rectangle("Purple", 8.0, 9.0);
        shapes[7] = shape.new Circle("Pink", 1.0);
        shapes[8] = new Triangle("Orange", 4.0, 5.0);
        shapes[9] = new Rectangle("Brown", 10.0, 11.0);

        ShapeCollection shapeCollection = shape.new ShapeCollection(shapes);

        System.out.println(shapeCollection);

        System.out.println(shapeCollection.compareArea());

        System.out.println(shapeCollection.compareShapeColor());

    }
}

