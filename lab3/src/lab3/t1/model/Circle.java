package lab3.t1.model;

public class Circle extends Shape {
    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * Math.pow(radius, 2);
    }
/*    @Override
    public void draw() {
        System.out.println("Малюємо коло. Площа: " + calcArea() + ", Колір: " + getShapeColor());
    }*/

    @Override
    public String toString() {
        return "Фігура: Коло, " + super.toString() + ", Радіус: " + radius;
    }
}
