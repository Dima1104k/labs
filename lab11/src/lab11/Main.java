package lab11;

import java.sql.Connection;

import static lab11.DataBase.getConnection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            System.out.println("Підключення успішне!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
