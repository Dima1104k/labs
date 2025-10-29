package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4TagCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть URL сайту: ");
            String url = scanner.nextLine();
            Map<String, Integer> tags = countTags(url);
            if (tags.isEmpty()) {
                System.out.println("Теги не знайдені!");
                return;
            }
            System.out.println("Результат\n");


            System.out.println("Теги по алфавіту");
            printByAlphabet(tags);

            System.out.println("\nТеги по частоті");
            printByFrequency(tags);

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Map<String, Integer> countTags(String urlString) throws IOException {
        Map<String, Integer> tagCount = new HashMap<>();
        Pattern pattern = Pattern.compile("</?([a-zA-Z][a-zA-Z0-9]*)");

        URL url = new URL(urlString);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String tag = matcher.group(1).toLowerCase(); // назва тегу

                    if (tagCount.containsKey(tag)) {
                        tagCount.put(tag, tagCount.get(tag) + 1);
                    } else {
                        tagCount.put(tag, 1);
                    }
                }
            }
        }

        return tagCount;
    }

    // Виведення по алфавіту
    public static void printByAlphabet(Map<String, Integer> tags) {
        // TreeMap автоматично сортує по ключах (алфавіту)
        TreeMap<String, Integer> sorted = new TreeMap<>(tags);

        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println("<" + entry.getKey() + "> : " + entry.getValue() + " разів");
        }
    }

    // Виведення по частоті
    public static void printByFrequency(Map<String, Integer> tags) {

        List<Map.Entry<String, Integer>> list = new ArrayList<>(tags.entrySet());

        // Сортуємо по частоті
      list.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println("<" + entry.getKey() + "> : " + entry.getValue() + " разів");
        }
    }
}
//https://sport.ua/uk/football