package T2;

public class Validator {
    public static boolean isValidName(String name){
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        boolean prov = name.matches("[a-zA-Za-яА-ЯіІЇєЄ]+");
        return prov;
    }

    public static boolean isValidDate(String date){
        if(date == null) return false;

        if(!date.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) return false;

        String[] parts = date.split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if(day<1 || day>31) return false;
        if(month<1 || month>12) return false;
        if(year<1900 || year>2009) return false;
        return true;
    }
    public static boolean isValidPhone(String phone){
        if(phone == null) return false;

        boolean prov = phone.matches("\\+380\\d{9}");
        return prov;
    }
    public static boolean isValidStreet(String street) {
        return street != null && !street.trim().isEmpty();
    }
    public static boolean isValidHouse(String house){
        if(house == null) return false;
        return house.matches("\\d+[A-Яа-я]?");
    }
    public static boolean isValidApartment(String apartment) {
        if (apartment == null) return false;
        return apartment.matches("\\d+");
    }
}
