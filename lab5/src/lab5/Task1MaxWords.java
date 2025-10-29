package lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Task1MaxWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть шлях до файлу:");
        String filePath = scanner.nextLine();
        String lineWithMaxWords = null;
        int maxWordCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                int wordCount = words.length;
                if (wordCount > maxWordCount) {
                    maxWordCount = wordCount;
                    lineWithMaxWords = line;
                }
            }

            if (lineWithMaxWords != null) {
                System.out.println("Рядок з найбільшою кількістю слів:");
                System.out.println(lineWithMaxWords);
                System.out.println("Кількість слів: " + maxWordCount);
            } else {
                System.out.println("Файл порожній або не містить слів");
            }
            //D:\IdeaProjects\jav\src\lab5\test.txt приклад
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}
