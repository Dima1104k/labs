package lab3.t1.model;

public abstract class Shape implements Drawable {
    public abstract double calcArea();

    String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    @Override
    public void draw() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Колір: " + shapeColor + ", Площа: " + String.format("%.2f", calcArea());
    }

}
