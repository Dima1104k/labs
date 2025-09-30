package T2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String phoneNumber;
    private Address address;

    public Student(String lastName, String firstName, String birthDate, String phoneNumber, Address address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return "----------------------------------------\n" +
                "Студент:   " + lastName + " " + firstName + "\n" +
                "Дата нар.: " + birthDate+ "\n" +
                "Телефон:   " + phoneNumber + "\n" +
                "Адреса:    " + address.toString() + "\n" +
                "----------------------------------------";
    }
}