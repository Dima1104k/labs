package lab5;

import java.io.*;
import java.util.Scanner;

public class Task3Encryption {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Що робимо?");
            System.out.println("1 - Зашифрувати файл");
            System.out.println("2 - Розшифрувати файл");
            System.out.print("Ваш вибір: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Введіть ключовий символ: ");
            char key = scanner.nextLine().charAt(0);

            if (choice == 1) {
                encrypt(scanner, key);
            } else if (choice == 2) {
                decrypt(scanner, key);
            } else {
                System.out.println("Невірний вибір!");
            }

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // метод для шифрування
    public static void encrypt(Scanner scanner, char key) {
        System.out.print("Введіть шлях до файлу який треба зашифрувати: ");
        String inputFile = scanner.nextLine();

        System.out.print("Введіть шлях куди зберегти зашифрований файл: ");
        String outputFile = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter fileWriter = new FileWriter(outputFile);
             EncryptWriter writer = new EncryptWriter(fileWriter, key)) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write("\n"); // додаємо перенос рядка
            }

            System.out.println("Файл зашифровано успішно!");

        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + inputFile);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    // метод для дешифрування
    public static void decrypt(Scanner scanner, char key) {
        System.out.print("Введіть шлях до зашифрованого файлу: ");
        String inputFile = scanner.nextLine();

        System.out.print("Введіть шлях куди зберегти розшифрований файл: ");
        String outputFile = scanner.nextLine();

        try (FileReader fileReader = new FileReader(inputFile);
             DecryptReader reader = new DecryptReader(fileReader, key);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }

            System.out.println("Файл розшифровано успішно!");

        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + inputFile);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
//D:\IdeaProjects\jav\src\lab5\input.txt
// D:\IdeaProjects\jav\src\lab5\encrypted.txt
