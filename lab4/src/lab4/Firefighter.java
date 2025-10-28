package lab4;

public class Firefighter extends Person {

    public Firefighter(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return "Пожежник: " + getName();
    }
}
