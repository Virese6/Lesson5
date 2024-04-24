package app.model;

import java.math.BigDecimal;

public class Car {
    private Long id;
    private String brand;
    private BigDecimal price;
    private int year;

    public Car(String brand, BigDecimal price, int year) {
        this.brand = brand;
        this.price = price;
        this.year = year;
    }

    public Car(Long id, String brand, BigDecimal price, int year) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.year = year;
    }

    public Car() {

    }


    public void setYear(int year) {
        this.year = year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (year != car.year) return false;
        if (id != null ? !id.equals(car.id) : car.id != null) return false;
        if (brand != null ? !brand.equals(car.brand) : car.brand != null) return false;
        if (price != null ? !price.equals(car.price) : car.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Car: ID - %d, brand - %s, price - %.2f, year - %d", id, brand, price, year);
    }
}
