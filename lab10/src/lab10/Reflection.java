package lab10;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Reflection {
    public static void main(String[] args) throws Exception {
        String s1 = "Java";
        System.out.print("Введіть будь-який рядок (для s2): ");
        Scanner scanner = new Scanner(System.in);
        String s2 = new String(scanner.nextLine());
        System.out.println("\nДО ЗМІНИ");
        System.out.println("s1 (літерал): " + s1);
        System.out.println("s2 (введений): " + s2);

        System.out.print("\nВведіть текст, на який будемо міняти: ");
        String replaceText = scanner.nextLine();
        changeString(s1, replaceText);
        changeString(s2, replaceText);
        System.out.println("\nПІСЛЯ ЗМІНИ");
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);

        String s3 = "Java";
        System.out.println("s3 (новий літерал 'Java'): " + s3);
        scanner.close();
    }
    public static void changeString(String s, String newText) throws Exception {
        Field field = s.getClass().getDeclaredField("value");
        field.setAccessible(true);

        field.set(s, newText.getBytes());
        System.out.println(s);

    }
}
