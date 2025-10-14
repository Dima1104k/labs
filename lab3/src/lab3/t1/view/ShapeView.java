package lab3.t1.view;

import lab3.t1.model.Shape;

public class ShapeView {
    public void displayShapes(Shape[] shapes) {
        System.out.println("\n СПИСОК ФІГУР");
        for (int i = 0; i < shapes.length; i++) {
            System.out.print((i+1) + ".");
            shapes[i].draw();
        }
    }
    public void displayTotalArea(double totalArea) {
        System.out.printf("\nЗагальна площа фігур: %.2f\n", totalArea);
    }
    public void displayAreaByType(String typeName, double area) {
        System.out.println("\n ПЛОЩА ФІГУР ТИПУ: " + typeName);
        System.out.printf("Сумарна площа: %.2f\n", area);
    }
    public void displaySortedShapes(Shape[] shapes, String sortType) {
        System.out.println("\n ВІДСОРТОВАНІ ФІГУРИ (" + sortType + ")");
        for (int i = 0; i < shapes.length; i++) {
            System.out.print((i + 1) + ". ");
            shapes[i].draw();
        }
    }
}
