package lab3.t2;

import java.util.UUID;

public abstract class Card {
    protected final String cardId;
    protected  final String typeCard;

    public Card(String typeCard) {
        this.cardId = UUID.randomUUID().toString().substring(0, 8);
        this.typeCard = typeCard;
    }
    public String getCardId() {
        return cardId;
    }
    public String getTypeCard() {
        return typeCard;
    }
    public abstract boolean isValid();

    public abstract void use();

    @Override
    public String toString() {
        return "ID: " + cardId + ", Тип: " + typeCard;
    }
}
