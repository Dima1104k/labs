package lab4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTransport {

    //Пожежна машина приймає тільки пожежників
    @Test
    public void test1_FireCarAcceptsOnlyFirefighter() {
        FireCar fireCar = new FireCar(2);
        Firefighter mike = new Firefighter("Майк");
        fireCar.addPassenger(mike);
        assertEquals(1, fireCar.getOccupiedSeats());
        assertEquals(1, fireCar.getPassengers().size());
        assertTrue(fireCar.getPassengers().contains(mike));
    }

    @Test
    public void test2_PoliceCarAcceptsOnlyPoliceman() {
        // Поліцейська машина приймає тільки поліцейських
        PoliceCar policeCar = new PoliceCar(2);
        Policeman sarah = new Policeman("Сара");

        policeCar.addPassenger(sarah);

        assertEquals(1, policeCar.getOccupiedSeats());
        assertTrue(policeCar.getPassengers().contains(sarah));
    }

    @Test
    public void test3_TaxiAcceptsAnyPassenger() {
        // Таксі/Автобус приймають будь-кого
        Taxi taxi = new Taxi(4);

        Person normal = new Person("Олексій");
        Firefighter mike = new Firefighter("Майк");
        Policeman john = new Policeman("Джон");

        // Можемо посадити всіх типів пасажирів
        taxi.addPassenger(normal);
        taxi.addPassenger(mike);
        taxi.addPassenger(john);

        assertEquals(3, taxi.getOccupiedSeats());
    }

    @Test(expected = IllegalStateException.class)
    public void test5_ThrowsExceptionWhenVehicleFull() {
        // Виключення при переповненні
        Taxi taxi = new Taxi(1);
        Person person1 = new Person("Олексій");
        Person person2 = new Person("Марія");

        taxi.addPassenger(person1);
        taxi.addPassenger(person2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test6_ThrowsExceptionWhenRemovingAbsentPassenger() {
        // Виключення при висадці відсутнього пасажира
        FireCar fireCar = new FireCar(2);
        Firefighter mike = new Firefighter("Майк");
        Firefighter john = new Firefighter("Джон");

        fireCar.addPassenger(mike);
        fireCar.removePassenger(john);  // john не їде
    }

    @Test
    public void test7_RoadCountsHumans() {
        // Підрахунок людей на дорозі
        Road road = new Road();
        // Створюємо різні типи машин з різними пасажирами
        Taxi taxi = new Taxi(5);
        taxi.addPassenger(new Person("Олексій"));
        taxi.addPassenger(new Person("Марія"));

        FireCar fireCar = new FireCar(3);
        fireCar.addPassenger(new Firefighter("Майк"));

        PoliceCar policeCar = new PoliceCar(3);
        policeCar.addPassenger(new Policeman("Сара"));
        policeCar.addPassenger(new Policeman("Джон"));

        road.addCarToRoad(taxi);
        road.addCarToRoad(fireCar);
        road.addCarToRoad(policeCar);

        assertEquals(5, road.getCountOfHumans());
    }

    @Test
    public void test8_RoadCountAfterAddingPassenger() {
        // Спробуємо додати пасажира після додавання машини на дорогу
        Road road = new Road();

        Taxi taxi = new Taxi(5);
        taxi.addPassenger(new Person("Олексій"));

        road.addCarToRoad(taxi);
        assertEquals(1, road.getCountOfHumans());

        // Додаємо ще пасажира у машину на трасі
        taxi.addPassenger(new Person("Марія"));

        assertEquals(2, road.getCountOfHumans());
    }

    public static void main(String[] args) {

        System.out.println("ПОЖЕЖНА МАШИНА:");
        FireCar fireCar = new FireCar(2);
        Firefighter mike = new Firefighter("Майк");
        fireCar.addPassenger(mike);
        System.out.println(" Пожежник посаджений успішно\n");


        System.out.println("ПОЛІЦЕЙСЬКА МАШИНА:");
        PoliceCar policeCar = new PoliceCar(2);
        Policeman sarah = new Policeman("Сара");
        policeCar.addPassenger(sarah);
        System.out.println(" Поліцейський посаджений успішно\n");

        System.out.println("ТАКСІ (приймає всіх):");
        Taxi taxi = new Taxi(4);
        taxi.addPassenger(new Person("Олексій"));
        taxi.addPassenger(new Firefighter("Петро"));
        taxi.addPassenger(new Policeman("Анна"));
        System.out.println("Всі типи пасажирів посаджені\n");


        System.out.println("ТЕСТ ПЕРЕПОВНЕННЯ:");
        try {
            Taxi smallTaxi = new Taxi(1);
            smallTaxi.addPassenger(new Person("Перший"));
            smallTaxi.addPassenger(new Person("Другий"));
        } catch (IllegalStateException e) {
            System.out.println("Виключення спрацювало: " + e.getMessage() + "\n");
        }


        System.out.println("ДОРОГА:");
        Road road = new Road();
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireCar);
        road.addCarToRoad(policeCar);
        System.out.println("Всього людей на дорозі: " + road.getCountOfHumans());
    }
}
