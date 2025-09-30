package T2;

import java.util.ArrayList;
import java.util.Scanner;
public class JournalManager {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addStudent(){
        System.out.println("\n Додавання студента");

        String lastName =inputLastName();
        String firstName = inputFirstName();
        String birthDate = inputBirthDate();
        String phone = inputPhone();

        System.out.println("\nАдреса");
        String street = inputStreet();
        String house = inputHouse();
        String apartment = inputApartment();

        Address address = new Address(street, house, apartment);
        Student student = new Student(lastName, firstName, birthDate, phone, address);
        students.add(student);

        System.out.println("\nСтудента додано успішно!");
    }
    public void display(){
        if(students.isEmpty()){
            System.out.println("\nЖурнал порожній");
            return;
        }
        System.out.println("\nЖурнал куратора");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
    private String inputLastName() {
        while (true){
            System.out.println("Прізвище: ");
            String input = scanner.nextLine().trim();
            if(Validator.isValidName(input)){
                return input;
            } else {
                System.out.println("Неправильне прізвище");
            }
        }
    }
    private String inputFirstName() {
        while (true) {
            System.out.print("Ім'я: ");
            String input = scanner.nextLine().trim();

            if (Validator.isValidName(input)) {
                return input;
            } else {
                System.out.println("Неправильне ім'я ");
            }
        }
    }
    private String inputBirthDate() {
        while (true){
            System.out.println("Дата народження (дд.мм.рррр): ");
            String input = scanner.nextLine().trim();
            if(Validator.isValidDate(input)){
                return input;
            } else {
                System.out.println("Некоректна дата");
            }
        }
    }
    private String inputPhone() {
        while (true) {
            System.out.print("Телефон: ");
            String input = scanner.nextLine().trim();

            if (Validator.isValidPhone(input)) {
                return input;
            } else {
                System.out.println("Некоректний телефон. Формат: +380501234567");
            }
        }
    }


    private String inputStreet() {
        while (true) {
            System.out.print("Вулиця: ");
            String input = scanner.nextLine().trim();

            if (Validator.isValidStreet(input)) {
                return input;
            } else {
                System.out.println("Вулиця не може бути порожньою");
            }
        }
    }

    private String inputHouse() {
        while (true) {
            System.out.print("Будинок: ");
            String input = scanner.nextLine().trim();

            if (Validator.isValidHouse(input)) {
                return input;
            } else {
                System.out.println("Некоректний номер будинку. Приклади: 5, 5А, 12Б");
            }
        }
    }

    private String inputApartment() {
        while (true) {
            System.out.print("Квартира: ");
            String input = scanner.nextLine().trim();

            if (Validator.isValidApartment(input)) {
                return input;
            } else {
                System.out.println("Некоректний номер квартири");
            }
        }
    }
}
