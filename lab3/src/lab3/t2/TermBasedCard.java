package lab3.t2;

import java.time.LocalDate;

public class TermBasedCard extends Card {

    private final LocalDate expiryDate;

    public TermBasedCard(String typeCard, int daysValid) {
        super(typeCard);
        this.expiryDate = LocalDate.now().plusDays(daysValid);
    }

    @Override
    public boolean isValid() {

        return !LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public void use() {
        if (isValid()) {
            System.out.println("Картка дійсна до: " + expiryDate);
        } else {
            System.out.println("Картка не є дійсною!");
        }

    }
    @Override
    public String toString() {
        return super.toString() + ", дійсна до: " + expiryDate;
    }
}
