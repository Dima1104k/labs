import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        int n = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введи число");
                n = sc.nextInt();
                break;
            } catch (Exception ex) {
                System.out.println("Помилка, треба ввести саме число, а не текст!");
                sc.nextLine();
            }
        }
        System.out.println("Дільники числа " + n + ":");

        for(int i = 1; i < n; i++) {
            List<Integer> pay = new ArrayList<>();
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    pay.add(j);
                }
            }
            int sum = 0;
            for(int m : pay){
                sum+=m;

            }
            if (sum == i && i > 1) {
                System.out.println("Число " + i + " є досконалим!");
            }
        }
        sc.close();
    }
}