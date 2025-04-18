package models;

import enums.PackageType;

public class Package1 {
    private Long id;
    private double weight;
    private PackageType package1Type;

    public Package1() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public PackageType getPackage1Type() {
        return package1Type;
    }

    public void setPackage1Type(PackageType package1Type) {
        this.package1Type = package1Type;
    }

    @Override
    public String toString() {
        return "Package1{" +
                "id=" + id +
                ", weight=" + weight +
                ", package1Type=" + package1Type +
                '}';
    }
}
