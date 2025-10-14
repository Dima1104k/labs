package lab3.t2;


public class TripBasedCard  extends Card{

    private int remainingTrips;

    public TripBasedCard(String cardType, int trips) {
        super(cardType);
        this.remainingTrips = trips;
    }
    @Override
    public boolean isValid() {

        return remainingTrips > 0;
    }

    @Override
    public void use() {
        if (isValid()) {
            remainingTrips--;
            System.out.println("Поточна кількість поїздок: " + remainingTrips);
        } else {
            System.out.println("Картка не є дійсною!");
        }
    }
    @Override
    public String toString() {
        return super.toString() + ", залишилось поїздок: " + remainingTrips;
    }
}
