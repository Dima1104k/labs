package lab10;

import lab5.DecryptReader;
import lab5.EncryptWriter;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;

public class Task3Encryption {
    private static final Logger LOGGER = Logger.getLogger(Task3Encryption.class.getName());
    private static Locale currentLocale = new Locale("uk");
    private static ResourceBundle bundle = ResourceBundle.getBundle("location.data", currentLocale);

    private static void setupLogger() {
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(consoleHandler);
        try {
            FileHandler fileHandler = new FileHandler("log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.warning("Не вдалося створити файл логу: " + e.getMessage());
        }
    }

    private static void changeLanguage(Scanner scanner) {
        System.out.println(bundle.getString("language.title"));
        System.out.println(bundle.getString("language.option.uk"));
        System.out.println(bundle.getString("language.option.en"));
        System.out.println(bundle.getString("menu.prompt"));
        int choice = scanner.nextInt();
        scanner.nextLine();
        LOGGER.fine("Користувач вибрав опцію: " + choice);

        if (choice == 1) {
            currentLocale = new Locale("uk", "UA");
        } else if (choice == 2) {
            currentLocale = new Locale("en", "US");
        } else {
            LOGGER.warning("Невірний вибір меню: " + choice);
            System.out.println(bundle.getString("menu.invalid"));
            return;
        }

        bundle = ResourceBundle.getBundle("location.data", currentLocale);


    }

    public static void main(String[] args) {
        setupLogger();

        LOGGER.info("Програма запущена");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            try {
                System.out.println(bundle.getString("menu.title"));
                System.out.println(bundle.getString("menu.option.encrypt"));
                System.out.println(bundle.getString("menu.option.decrypt"));
                System.out.println(bundle.getString("menu.option.language"));
                System.out.println(bundle.getString("menu.option.exit"));
                System.out.println(bundle.getString("menu.prompt"));

                int choice;
                if (scanner.hasNextInt()){
                    choice = scanner.nextInt();
                } else {
                    scanner.nextLine();
                    choice = -1;
                }
                scanner.nextLine();
                LOGGER.fine("Користувач вибрав опцію: " + choice);


                if (choice == 1) {
                    System.out.println(bundle.getString("prompt.key"));
                    char key = scanner.nextLine().charAt(0);
                    encrypt(scanner, key);
                } else if (choice == 2) {
                    System.out.println(bundle.getString("prompt.key"));
                    char key = scanner.nextLine().charAt(0);
                    decrypt(scanner, key);
                } else if (choice == 3) {
                    changeLanguage(scanner);
                } else if (choice == 0) {
                    running = false;
                } else {
                    LOGGER.warning("Невірний вибір меню: " + choice);
                    System.out.println(bundle.getString("menu.invalid"));

                }

            } catch (Exception e) {
                LOGGER.severe("Критична помилка в main" + e.getMessage());
                System.out.println(bundle.getString("message.error.general") + e.getMessage());
            }
        }
        LOGGER.info("Програма завершує роботу");
        scanner.close();
    }

    // метод для шифрування
    public static void encrypt(Scanner scanner, char key) {
        LOGGER.info("Початок шифрування");
        System.out.println(bundle.getString("prompt.input.encrypt"));

        String inputFile = scanner.nextLine();

        System.out.println(bundle.getString("prompt.output.encrypt"));
        String outputFile = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter fileWriter = new FileWriter(outputFile);
             EncryptWriter writer = new EncryptWriter(fileWriter, key)) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write("\n"); // додаємо перенос рядка
            }
            LOGGER.info("Файл успішно зашифровано" + outputFile);
            System.out.println(bundle.getString("message.success.encrypt"));

        } catch (FileNotFoundException e) {
            LOGGER.warning("Файл для шифрування не знайдено: " + inputFile);
            System.out.println(bundle.getString("message.error.filenotfound") + inputFile);

        } catch (IOException e) {
            LOGGER.severe("Критична помилка вводу/виводу" + e.getMessage());
            System.out.println(bundle.getString("message.error.general") + e.getMessage());
        }
    }


    // метод для дешифрування
    public static void decrypt(Scanner scanner, char key) {
        LOGGER.info("Початок процесу дешифрування");
        System.out.println(bundle.getString("prompt.input.decrypt"));
        String inputFile = scanner.nextLine();
        System.out.println(bundle.getString("prompt.output.decrypt"));
        String outputFile = scanner.nextLine();

        try (FileReader fileReader = new FileReader(inputFile);
             DecryptReader reader = new DecryptReader(fileReader, key);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
            LOGGER.info("Файл успішно розшифровано: " + outputFile);
            System.out.println(bundle.getString("message.success.decrypt"));

        } catch (FileNotFoundException e) {
            LOGGER.warning("Файл для дешифрування не знайдено: " + inputFile);
            System.out.println(bundle.getString("message.error.filenotfound") + inputFile);
        } catch (IOException e) {
            LOGGER.severe("Критична помилка запису/читання при дешифруванні" + e.getMessage());
            System.out.println(bundle.getString("message.error.general") + e.getMessage());
        }
    }


}
//D:\IdeaProjects\jav\src\lab5\input.txt
// D:\IdeaProjects\jav\src\lab5\encrypted.txt
