package lab3.t1.model;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

 /*   @Override
    public void draw() {
        System.out.println("Малюємо прямокутник. Площа: " + calcArea() + ", Колір: " + getShapeColor());
    }*/


    @Override
    public String toString() {
        return "Фігура: Прямокутник, " + super.toString() + ", Ширина: " + width + ", Висота: " + height;
    }
}
