package lab4;

import java.util.List;

public abstract class Car<T extends Person> extends Vehicle<T> {

    public Car(int maxSeats) {
        super(maxSeats);
    }
}