package service.impl;
import db.Database;
import enums.DeliveryStatus;
import models.Delivery;
import service.DeliveryService;
import java.util.ArrayList;
import java.util.List;

public class DeliveryServiceImpl implements DeliveryService {
    @Override
    public String addDelivery(Delivery delivery) {
        Database.deliveries.add(delivery);
        return "Delivery added";
    }

    @Override
    public String deleteDelivery(Long id) {
        for (Delivery delivery : Database.deliveries) {
            if (delivery.getId().equals(id)){
                Database.deliveries.remove(delivery);
                return "Delivery deleted";
            }
        }
        return "Id not found!";
    }

    @Override
    public List<Delivery> getAllDeliveriesByStatus(DeliveryStatus status) {
        List<Delivery> deliveries = new ArrayList<>();
        for (Delivery delivery : Database.deliveries) {
            if (delivery.getDeliveryStatus().equals(status)){
                deliveries.add(delivery);
            }
        }
        return deliveries;
    }

    @Override
    public Delivery getDeliveriesByCourier(Long id) {
        for (Delivery delivery : Database.deliveries) {
            if (delivery.getCourier().getId().equals(id)){
                return delivery;
            }
        }
        return null;
    }

    @Override
    public String updateDeliveryStatus(long id, DeliveryStatus status) {
        for (Delivery delivery : Database.deliveries) {
            if (delivery.getId().equals(id)){
                delivery.setDeliveryStatus(status);
                return "Status successfully updated!";
            }
        }
        return "Delivery not founded!";
    }
}
