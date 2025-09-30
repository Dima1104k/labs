package T2;
public class Address {
    private String street;
    private String houseNumber;
    private String apartmentNumber;

    public Address(String street, String houseNumber, String apartmentNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString() {
        return "вул. " + street + ", буд. " + houseNumber + ", кв. " + apartmentNumber;
    }
}