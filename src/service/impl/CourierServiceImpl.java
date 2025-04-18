package service.impl;
import db.Database;
import models.Courier;
import service.CourierService;
import java.util.LinkedList;
import java.util.List;

public class CourierServiceImpl implements CourierService {
    @Override
    public String addCourier(Courier courier) {
        Database.couriers.add(courier);
        return "Courier successfully added";
    }

    @Override
    public Courier getAvailableCourier(boolean isTrue) {
        for (Courier courier : Database.couriers) {
            if (courier.getAvailable()) return courier;
        }
        return null;
    }

    @Override
    public List<Courier> getAvailableCouriers() {
        List<Courier> couriers = new LinkedList<>();
        for (Courier courier : Database.couriers) {
            if (courier.getAvailable()){
                couriers.add(courier);
            }
        }
        return couriers;
    }

    @Override
    public List<Courier> getAllCouriers() {
        return Database.couriers;
    }

    @Override
    public List<Courier> getCouriersByRating(double minRating) {
        List<Courier> couriers = new LinkedList<>();
        for (Courier courier : Database.couriers) {
            if (courier.getRating() == minRating){
                couriers.add(courier);
            }
        }
        return couriers;
    }

    @Override
    public String updateCourierStatus(Long id, boolean isAvailable) {
        for (Courier courier : Database.couriers) {
            if (courier.getId().equals(id)){
                courier.setAvailable(isAvailable);
            }
        }
        return "Courier status successfully updated";
    }

    @Override
    public void setCourierRating(Long id, double newRating) {
        for (Courier courier : Database.couriers) {
            if (courier.getId().equals(id)){
                courier.setRating(newRating);
                System.out.println("Success");
                break;
            }
        }
    }
}
