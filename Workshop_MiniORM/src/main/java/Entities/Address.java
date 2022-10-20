package Entities;

import anotations.Column;
import anotations.Entity;
import anotations.Id;

@Entity(name = "addresses")
public class Address {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "town")
    private String town;

    @Column(name = "country")
    private String country;

    public Address(){}

    public Address(int id, String street, String town, String country) {
        this.id = id;
        this.street = street;
        this.town = town;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", town='" + town + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
