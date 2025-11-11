package lab7;

import java.util.Scanner;
import java.util.function.IntPredicate;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("ПОШУК ДОСКОНАЛИХ ЧИСЕЛ\n");

        int n = 0;

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введи число (верхню межу діапазону): ");
                n = sc.nextInt();
                if (n <= 0) {
                    System.out.println("Число повинно бути більше 0");
                    continue;
                }
                break;
            } catch (Exception ex) {
                System.out.println("Помилка, треба ввести саме число, а не текст!");
                sc.nextLine();
            }

        }
        System.out.println("\nДосконалі числа від 1 до " + n + " ");
        IntPredicate isPerfect = (num) -> {
            if (num <= 1) return false;
            int sum = 1;/*
            for (int i = 1; i <= num / 2; i++) {*/
            for (int i = 2; i * i<= num; i++) {
                if (num % i == 0) {
                    sum += i;
                    if(i * i != num){
                        sum+= num/i;
                    }
                }
            }
            return sum == num;

        };
        for (int i = 1; i <= n; i++) {
            if (isPerfect.test(i)) {
                System.out.println(i + " - ідеальне число");
            } /*else {
                System.out.println(i);
            }*/
        }
        sc.close();
    }
}
