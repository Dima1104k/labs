package lab6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Translator translator = new Translator();
        System.out.println("\nНаповнення словника вручну");
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("java", "джава");
        boolean isRunning = true;
        while (isRunning) {

            System.out.println("\nГоловне меню перекладача");
            System.out.println("1. Додати нове слово");
            System.out.println("2. Перекласти фразу");
            System.out.println("3. Показати весь словник");
            System.out.println("4. Вийти");
            System.out.print("Ваш вибір (1-4): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Введіть англійське слово: ");
                    String eng = scanner.nextLine().trim().toLowerCase();;
                    System.out.print("Українське слово: ");
                    String ukr = scanner.nextLine().trim();
                    translator.addWord(eng, ukr);
                    break;
                case "2":
                    System.out.print("Введіть фразу англійською для перекладу: ");
                    String phraseToTranslate = scanner.nextLine();
                    String result = translator.translate(phraseToTranslate);
                    System.out.println("---------------------------------");
                    System.out.println("Оригінал: " + phraseToTranslate);
                    System.out.println("Переклад: " + result);
                    System.out.println("---------------------------------");
                    break;
                case "3":
                    translator.showDictionary();
                    break;
                case "4":

                    isRunning = false;
                    System.out.println("До побачення!");
                    break;

                default:
                    System.out.println("Помилка! Будь ласка, введіть число від 1 до 4");
            }
        }
        scanner.close();
    }
}

