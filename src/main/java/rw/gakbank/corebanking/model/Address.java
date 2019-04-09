package rw.gakbank.corebanking.model;

import java.util.Objects;

public class Address {
    private String state;
    private String city;
    private String zip;
    private String apartmentNo;

    public Address(String state, String city, String zip, String apartmentNo) {
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.apartmentNo = apartmentNo;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(state, address.state) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zip, address.zip) &&
                Objects.equals(apartmentNo, address.apartmentNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, city, zip, apartmentNo);
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", apartmentNo='" + apartmentNo + '\'' +
                '}';
    }
}
