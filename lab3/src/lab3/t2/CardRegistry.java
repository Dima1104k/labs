package lab3.t2;

import java.util.HashMap;
import java.util.Map;

public class CardRegistry {
    private final Map<String, Card> issuedCards = new HashMap<>();
//    private  final List<Card> cards = new ArrayList<>();

    //знач треба карту випустити нову
    public void issueCard(Card card) {
        issuedCards.put(card.getCardId(), card);
        System.out.println("Випущено нову картку " + card);
    }
    //знач треба знайти карту по id
    public Card findById(String id){
        return issuedCards.get(id);
    }

    public void displayAllCards() {
        if (issuedCards.isEmpty()) {
            System.out.println("В системі ще немає жодної картки");
            return;
        }
        System.out.println("Всі випущені картки:");
        for (Card card : issuedCards.values()) {
            System.out.println(card.toString());
        }
    }
}
