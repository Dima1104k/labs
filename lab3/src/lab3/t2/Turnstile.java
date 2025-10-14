package lab3.t2;

import java.util.HashMap;
import java.util.Map;

public class Turnstile {
    private final CardRegistry cardRegistry;
    // для статистики можна додати лічильник
    private int totalAllowed;  // дозволили
    private int totalDenied; // заборонили
    // і два лічильники по категоріях
    private HashMap<String, Integer> allowedByCategory;
    private HashMap<String, Integer> deniedByCategory;


    public Turnstile(CardRegistry cardRegistry) {
        this.totalAllowed = 0;
        this.totalDenied = 0;
        this.allowedByCategory = new HashMap<>();
        this.deniedByCategory = new HashMap<>();
        this.cardRegistry = cardRegistry;

    }


    public void checkCard(String cardId) {
        Card card = cardRegistry.findById(cardId);
        if (card == null) {
            System.out.println("Картка з ID " + cardId + " не знайдена");
            totalDenied++;
            return;
        }

        System.out.println("Перевірка картки... " + card);
        if (card.isValid()) {
            card.use();
            totalAllowed++;
            allow(card.typeCard);
            System.out.println("Прохід дозволено");
            if (card instanceof StoredValueCard) {
                StoredValueCard storedValueCard = (StoredValueCard) card;
                double balance = storedValueCard.getBalance();
                System.out.println("Залишок на балансі: " + String.format("%.2f", balance) + " грн.");
            }
        } else {
            System.out.println("Прохід заборонено");
            totalDenied++;
            denied(card.typeCard);
        }
    }

    private void denied(String typeCard) {
        if (deniedByCategory.containsKey(typeCard)) {
            int current = deniedByCategory.get(typeCard);
            int newcount = current + 1;
            deniedByCategory.put(typeCard, newcount);
        } else {
            deniedByCategory.put(typeCard, 1);
        }
    }

    private void allow(String typeCard) {
        if (allowedByCategory.containsKey(typeCard)) {
            int current = allowedByCategory.get(typeCard);
            int newcount = current + 1;
            allowedByCategory.put(typeCard, newcount);
        } else {
            allowedByCategory.put(typeCard, 1);
        }

    }

    public void printStatistics() {
        System.out.println("Статистика турнікета:");
        System.out.println("Загальна кількість дозволених проходів: " + totalAllowed);
        System.out.println("Загальна кількість заборонених проходів: " + totalDenied);
        System.out.println("Дозволено по категоріях:");
        for (Map.Entry<String, Integer> entry : allowedByCategory.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Заборонено по категоріях:");
        for (Map.Entry<String, Integer> entry : deniedByCategory.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
