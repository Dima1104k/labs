package lab4;

import java.util.ArrayList;
import java.util.List;

public class Vehicle<T extends Person> {
    private int maxSeats;
    private List<T> passengers;

    public Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути > 0");
        }
        this.maxSeats = maxSeats;
        this.passengers = new ArrayList<>();
    }

    public int getMaxSeats() {
        return maxSeats;
    }
    public int getOccupiedSeats() {
        return passengers.size();
    }
    public void addPassenger(T passenger) {
        if (getOccupiedSeats() >= maxSeats) {
            throw new IllegalStateException("Автомобіль повний");
        }
        passengers.add(passenger);
        System.out.println(passenger.getName() + " сів у автомобіль типу " + getClass().getSimpleName());
    }
    public void removePassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new IllegalArgumentException("Пасажир " + passenger.getName() + " не знаходиться у транспортному засобі");
        }
        System.out.println(passenger.getName() + " вийшов з автомобіля типу" + getClass().getSimpleName());
    }
    public List<T> getPassengers() {
        return new ArrayList<>(passengers);
    }
}
