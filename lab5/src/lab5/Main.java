package lab5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
        int code = 66+68;
        char letter = (char) code;
        System.out.println("Character for ASCII code " + code + " is: " + letter);
    }

}
