package lab11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQL {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try (Connection connection = DataBase.getConnection()) {
            while (true) {
                System.out.println("\nГОЛОВНЕ МЕНЮ");
                System.out.println("1. Показати всіх співробітників");
                System.out.println("2. Показати всі завдання");
                System.out.println("3. Співробітники конкретного відділу");
                System.out.println("4. Додати завдання для деякого співробітника");
                System.out.println("5. Для зазначеного співробітника отримати список його завдань");
                System.out.println("6. Видалити співробітника");
                System.out.println("0. ВИХІД");
                System.out.print("Ваш вибір: ");

                int choice;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Треба вводити цифру");
                    scanner.nextLine();
                    continue;
                }

                if (choice == 0) {
                    System.out.println("До побачення телебачення");
                    break;
                }

                switch (choice) {
                    case 1:
                        getAllEmployees(connection);
                        break;
                    case 2:
                        getAllTasks(connection);
                        break;
                    case 3:
                        getEmployeesByDepartment(connection, scanner);
                        break;
                    case 4:
                        addTask(connection, scanner);
                        break;
                    case 5:
                        getTasksByEmployee(connection, scanner);
                        break;
                    case 6:
                        deleteEmployee(connection, scanner);
                        break;
                    default:
                        System.out.println("Немає такої команди!");
                }
            }

        } catch (Exception e) {
            System.out.println("Помилка підключення до бази: " + e.getMessage());
        }
    }


    // отримати список всіх співробітників
    public static void getAllEmployees(Connection connection) throws Exception {
        String sql = "SELECT * FROM employees";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            System.out.println("\nСПИСОК СПІВРОБІТНИКІВ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String surname = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String position = rs.getString("position");

                System.out.println(id + " | " + surname + " " + firstName + " | " + position);
            }
        }
    }

    // отримати список всіх завдань
    public static void getAllTasks(Connection connection) throws Exception {
        String sql = "SELECT * FROM tasks";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            System.out.println("\nСПИСОК ВСІХ ЗАВДАНЬ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int empId = rs.getInt("emp_id");

                System.out.println("Завдання" + id + " | " + description + " (Виконує ID: " + empId + ")");
            }
        }
    }

    // отримати список співробітників зазначеного відділу
    public static void getEmployeesByDepartment(Connection connection, Scanner scanner) throws Exception {
        String sql = "SELECT * FROM employees WHERE dept_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            System.out.print("Введіть ID відділу: ");
            int departmentId = scanner.nextInt();
            scanner.nextLine();
            statement.setInt(1, departmentId);
            try (ResultSet rs = statement.executeQuery()) {
                System.out.println("\nСПИСОК СПІВРОБІТНИКІВ ЗА ЗАДАНИМ ВІДДІЛОМ");
                System.out.println("\nСпівробітники відділу №" + departmentId);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String surname = rs.getString("last_name");
                    String firstName = rs.getString("first_name");
                    String position = rs.getString("position");

                    System.out.println(id + " | " + surname + " " + firstName + " | " + position);
                }
            }
        }
    }

    // додати завдання для деякого співробітника
    private static void addTask(Connection connection, Scanner scanner) throws Exception {
        System.out.println("\nДОДАВАННЯ ЗАВДАННЯ");
        System.out.print("Введіть опис завдання: ");
        String description = scanner.nextLine();
        System.out.print("Введіть ID співробітника: ");
        int empId = scanner.nextInt();
        scanner.nextLine();
        String sql = "INSERT INTO tasks (description, emp_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, description);
            pstmt.setInt(2, empId);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long id = generatedKeys.getLong(1);
                        System.out.println("Завдання успішно додано! Йому присвоєно ID: " + id);
                    } else {
                        throw new Exception("Завдання додано, але ID не отримано.");
                    }
                }
            }
        }
    }

    // для зазначеного співробітника отримати список його завдань
    private static void getTasksByEmployee(Connection connection, Scanner scanner) throws Exception {
        System.out.print("Введіть ID співробітника: ");
        int empId = scanner.nextInt();
        scanner.nextLine();
        String sql = "SELECT * FROM tasks WHERE emp_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, empId);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\nЗАВДАННЯ СПІВРОБІТНИКА №" + empId);

                boolean hasTasks = false;
                while (rs.next()) {
                    hasTasks = true;
                    String desc = rs.getString("description");
                    int taskId = rs.getInt("id");
                    System.out.println("Завдання " + taskId + "|" + desc);
                }

                if (!hasTasks) {
                    System.out.println("У цього співробітника немає активних завдань лінтяй");
                }
            }
        }
    }

    // видалити співробітника
    private static void deleteEmployee(Connection connection, Scanner scanner) throws Exception {
        System.out.println("\nЗВІЛЬНЕННЯ СПІВРОБІТНИКА");
        System.out.print("Введіть ID співробітника, якого треба видалити: ");
        int empId = scanner.nextInt();
        scanner.nextLine();
        String deleteTasksSql = "DELETE FROM tasks WHERE emp_id = ?";

        try (PreparedStatement pstmtTasks = connection.prepareStatement(deleteTasksSql)) {
            pstmtTasks.setInt(1, empId);
            int tasksDeleted = pstmtTasks.executeUpdate();
            System.out.println("Видалено пов'язаних завдань " + tasksDeleted);
        }
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmtEmp = connection.prepareStatement(sql)) {
            pstmtEmp.setInt(1, empId);
            int rows = pstmtEmp.executeUpdate();
            if (rows > 0) {
                System.out.println("Співробітника успішно видалено з бази");
            } else {
                System.out.println("Співробітника з таким ID не знайдено");
            }
        }
    }

}