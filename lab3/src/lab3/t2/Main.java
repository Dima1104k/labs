package lab3.t2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardRegistry cardRegistry = new CardRegistry();
        Turnstile turnstile = new Turnstile(cardRegistry);

        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println(" 1. Випустити нову картку");
            System.out.println(" 2. Прохід через турнікет");
            System.out.println(" 3. Показати всі картки");
            System.out.println(" 4. Показати статистику турнікету");
            System.out.println(" 5. Поповнити накопичувальну картку");
            System.out.println(" 0. Вихід ");
            System.out.print("Ваш вибір: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    issueNewCard(scanner, cardRegistry);
                    break;
                case "2":
                    System.out.print("Введіть ID картки: ");
                    String cardId = scanner.nextLine();
                    turnstile.checkCard(cardId);
                    break;
                case "3":
                    cardRegistry.displayAllCards();
                    break;
                case "4":
                    turnstile.printStatistics();
                    break;
                case "5":
                    rechargeCard(scanner, cardRegistry);
                    break;
                case "0":
                    System.out.println("До побачення!");
                    return;
            }

        }
    }

    private static void rechargeCard(Scanner scanner, CardRegistry cardRegistry) {
        System.out.print("Введіть ID картки для поповнення: ");
        String cardId = scanner.nextLine();
        Card card = cardRegistry.findById(cardId);
        if (card == null) {
            System.out.println("Картка з ID " + cardId + " не знайдена");
            return;
        }
        if (!(card instanceof StoredValueCard)) {
            System.out.println("Поповнити можна лише накопичувальну картку");
            return;
        }
        StoredValueCard storedValueCard = (StoredValueCard) card;
        System.out.print("Введіть суму для поповнення (грн): ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println("Сума має бути додатною");
                return;
            }
            storedValueCard.recharge(amount);
            System.out.println("Картку поповнено. Новий баланс: " + String.format("%.2f", storedValueCard.getBalance()) + " грн.");
        } catch (NumberFormatException e) {
            System.out.println("Некоректне число!");
        }
    }

    private static void issueNewCard(Scanner scanner, CardRegistry cardRegistry) {
        System.out.println("\n ВИПУСК НОВОЇ КАРТКИ ");
        System.out.println("Оберіть тип картки:");
        System.out.println("1 - На термін (місяць/10 днів)");
        System.out.println("2 - На поїздки (5/10 поїздок)");
        System.out.println("3 - Накопичувальна (з балансом)");
        System.out.print("Ваш вибір: ");
        String typeChoice = scanner.nextLine();
        String typeCard = "";
        if (typeChoice.equals("1") || typeChoice.equals("2")) {
            System.out.println("\nОберіть категорію:");
            System.out.println("1 - Учнівська");
            System.out.println("2 - Студентська");
            System.out.println("3 - Звичайна");
            System.out.print("Ваш вибір: ");
            String categoryChoice = scanner.nextLine();
            switch (categoryChoice) {
                case "1" -> typeCard = "Учнівська";
                case "2" -> typeCard = "Студентська";
                case "3" -> typeCard = "Звичайна";
                default -> {
                    System.out.println("Невірний вибір категорії!");
                    return;
                }
            }
        }
        try {
            switch (typeChoice) {
                case "1" -> {
                    System.out.print("\nВведіть термін дії в днях: ");
                    int days = Integer.parseInt(scanner.nextLine());
                    cardRegistry.issueCard(new TermBasedCard(typeCard, days));
                    break;
                }
                case "2" -> {
                    System.out.print("\nВведіть кількість поїздок: ");
                    int trips = Integer.parseInt(scanner.nextLine());
                    cardRegistry.issueCard(new TripBasedCard(typeCard, trips));
                    break;

                }
                case "3" -> {
                    System.out.print("\nСкільки грошей покласти на картку? (грн): ");
                    double balance = Double.parseDouble(scanner.nextLine());
                    cardRegistry.issueCard(new StoredValueCard(balance));
                }

                default -> {
                    System.out.println("Невірний тип картки!");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некоректне число!");
            return;
        }
    }
}