package lab3.t1.controller;



import lab3.t1.model.Circle;
import lab3.t1.model.Rectangle;
import lab3.t1.model.Shape;
import lab3.t1.model.Triangle;
import lab3.t1.view.ShapeView;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ShapeController {
    private Shape[] shapes;
    private ShapeView view;
    public ShapeController() {
        this.view = new ShapeView();
        this.shapes = generate();
    }

    private Shape[] createShapes() {
        Shape[] shapes = new Shape[12];
        shapes[0] = new Circle("Червоний", 5.0);
        shapes[1] = new Circle("Синій", 3.5);
        shapes[2] = new Circle("Зелений", 7.2);
        shapes[3] = new Rectangle("Жовтий", 4.0, 6.0);
        shapes[4] = new Rectangle("Червоний", 5.5, 3.0);
        shapes[5] = new Rectangle("Синій", 8.0, 2.5);
        shapes[6] = new Triangle("Чорний", 3.0, 4.0, 5.0);
        shapes[7] = new Triangle("Білий", 5.0, 5.0, 5.0);
        shapes[8] = new Triangle("Зелений", 6.0, 8.0, 10.0);
        shapes[9] = new Circle("Фіолетовий", 4.5);
        shapes[10] = new Rectangle("Помаранчевий", 3.0, 7.0);
        shapes[11] = new Triangle("Червоний", 7.0, 8.0, 9.0);
        return shapes;
    }
    public void displayAllShapes() {
        view.displayShapes(shapes);
    }
    public void calculateTotalArea(){
        double total = 0;
        for(int i = 0; i < shapes.length; i++){
            total += shapes[i].calcArea();
        }
        view.displayTotalArea(total);
    }
    public void calculateAreaByType(String typeName){
        double total = 0;
        for(int i = 0; i < shapes.length; i++){
            if(typeName.equals("Коло") && shapes[i] instanceof Circle ){
                total += shapes[i].calcArea();
            } else if(typeName.equals("Прямокутник") && shapes[i] instanceof Rectangle ){
                total += shapes[i].calcArea();
            } else if(typeName.equals("Трикутник") && shapes[i] instanceof Triangle ){
                total += shapes[i].calcArea();
            }
        }
        view.displayAreaByType(typeName, total);

    }
    public void sortByArea(){
        Shape[] shape = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(shape, (s1, s2) -> Double.compare(s1.calcArea(), s2.calcArea()));
        view.displaySortedShapes(shape, "за площею");
    }
    public void sortByColor(){
        Shape[] shape = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(shape, (s1, s2) -> s1.getShapeColor().compareTo(s2.getShapeColor()));
        view.displaySortedShapes(shape, "за кольором");
    }
    public void run(){

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println(" 1. Показати всі фігури");
            System.out.println(" 2. Загальна площа всіх фігур");
            System.out.println(" 3. Площа кіл");
            System.out.println(" 4. Площа трикутників");
            System.out.println(" 5. Площа прямокутників");
            System.out.println(" 6. Сортувати за площею");
            System.out.println(" 7. Сортувати за кольором");
            System.out.println(" 0. Вихід ");
            System.out.print("Ваш вибір: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    displayAllShapes();
                    break;
                case "2":
                    calculateTotalArea();
                    break;
                case "3":
                    calculateAreaByType("Коло");
                    break;
                case "4":
                    calculateAreaByType("Трикутник");
                    break;
                case "5":
                    calculateAreaByType("Прямокутник");
                    break;
                case "6":
                    sortByArea();
                    break;
                case "7":
                    sortByColor();
                    break;
                case "0":
                    System.out.println("До побачення!");
                    return;
                default:
                    System.out.println("Невірний вибір!");
            }
        }

    }
    private Shape[] generate(){
        Random rand = new Random();
        Shape[] shapes = new Shape[12];
        String[] colors = {"Червоний", "Синій", "Зелений", "Жовтий",
                "Чорний", "Білий", "Помаранчевий", "Фіолетовий"};
        for( int i = 0; i < shapes.length; i++){
            String color = colors[rand.nextInt(colors.length)];
            int shapeType = rand.nextInt(3);
            switch (shapeType){
                case 0:
                    double radius = 1 + rand.nextInt(10);
                    shapes[i] = new Circle(color,radius);
                    break;
                case 1:
                    double width = 1 + rand.nextInt(10);
                    double height = 1 + rand.nextInt(10);
                    shapes[i] = new Rectangle(color,width,height);
                    break;
                case 2:
                    double sideA = 1 + rand.nextInt(10);
                    double sideB = 1 + rand.nextInt(10);
                    double sideC = 1 + rand.nextInt(10);

                    while (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
                        sideA = 1 + rand.nextInt(10);
                        sideB = 1 + rand.nextInt(10);
                        sideC = 1 + rand.nextInt(10);
                    }
                    shapes[i] = new Triangle(color,sideA,sideB,sideC);
                    break;
            }
        }
        return shapes;
    }


}
