package lab4;

public class Policeman extends Person {
    public Policeman(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Поліцейський: " + getName();
    }
}
