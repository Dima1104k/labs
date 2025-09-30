package T2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JournalManager journal = new JournalManager();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1. Додати студента");
            System.out.println("2. Показати всіх студентів");
            System.out.println("3. Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    journal.addStudent();
                    break;
                case "2":
                    journal.display();
                    break;
                case "3":
                    System.out.println("До побачення!");
                    return;
                default:
                    System.out.println("Невірний вибір!");
            }
        }
    }
}