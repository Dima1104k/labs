package lab3.t1.model;

public class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(String shapeColor, double sideA, double sideB, double sideC) {
        super(shapeColor);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calcArea() {
        double s = (sideA + sideB + sideC) / 2;
        // Формула Герона
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

/*
    @Override
    public void draw() {
        System.out.println("Малюємо трикутник. Площа: " + calcArea() + ", Колір: " + getShapeColor());
    }
*/

    @Override
    public String toString() {
        return "Фігура: Трикутник, " + super.toString() + ", сторони: " + sideA + ", " + sideB + ", " + sideC;
    }

}
