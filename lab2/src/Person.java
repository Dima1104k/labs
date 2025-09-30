import java.util.Objects;

public class Person {



    private final String lastName;
    private final String firstName;
    private final int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + age + " років";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person other = (Person) o;

        if (age != other.age) return false;
     /*   if (!Objects.equals(lastName, person.lastName)) return false;
        return Objects.equals(firstName, person.firstName);*/
        if (lastName == null) {
            if (other.lastName != null) return false;
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }

        if (firstName == null) {
            if (other.firstName != null) return false;
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
    //або
   /* @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, age);
    }*/
}