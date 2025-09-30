import com.google.gson.Gson;
public class Main {
    public static void main(String[] args) {

        Person original = new Person("Шевченко", "Андрій", 49);

        System.out.println("Оригінальна людина");

        Gson gson = new Gson();
        String json = gson.toJson(original);
        System.out.println("JSON: " + json);

        Person back = gson.fromJson(json,Person.class);
        System.out.println("Об'єкт відновлено з JSON " + back);
        if (original.equals(back)) {
            System.out.println("Об'єкти рівні");
        } else {
            System.out.println("Об'єкти НЕ рівні");
        }

    }
}