package lab4;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Car<? extends Person>> carsInRoad = new ArrayList<>();
    public int getCountOfHumans(){
        int totalHumans = 0;
        for(Car<? extends Person> car : carsInRoad){
            totalHumans += car.getOccupiedSeats();
        }
        return totalHumans;
    }
    public void addCarToRoad(Car<? extends Person> car){
        carsInRoad.add(car);
        System.out.println("Автомобіль " + car.getClass().getSimpleName() + " додано на дорогу");
    }
}