package models;
import enums.DeliveryStatus;

import java.time.LocalDate;

public class Delivery {
    private Long id;
    private String name;
    private LocalDate deliveryDate;
    private double price;
    private DeliveryStatus deliveryStatus;
    private Package1 aPackage;
    private Courier courier;

    public Delivery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Package1 getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package1 aPackage) {
        this.aPackage = aPackage;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", price=" + price +
                ", deliveryStatus=" + deliveryStatus +
                ", aPackage=" + aPackage +
                ", courier=" + courier +
                '}';
    }
}
