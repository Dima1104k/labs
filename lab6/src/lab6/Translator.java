package lab6;

import java.util.HashMap;
import java.util.Map;

public class Translator {
    private HashMap<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
        System.out.println("Перекладач створено, словник порожній");
    }
    public void addWord(String english, String ukrainian) {
        dictionary.put(english, ukrainian);
        System.out.println("Додано: " + english + " - " + ukrainian);
    }

    public String translate(String input) {
        String[] words = input.split(" ");
        StringBuilder translated = new StringBuilder();
        for (String word : words) {
            String translatedWord = dictionary.get(word.toLowerCase());
            if (translatedWord != null) {
                translated.append(translatedWord).append(" ");
            } else {
                translated.append(word).append(" ");
            }
        }
        return translated.toString();
    }
    public void showDictionary() {
        System.out.println("\nСловник");
        if (dictionary.isEmpty()) {
            System.out.println("Словник порожній");
            return;
        }
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

    }
}
