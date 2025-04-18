package service;
import enums.DeliveryStatus;
import models.Delivery;
import java.util.List;

public interface DeliveryService {
    String addDelivery(Delivery delivery);
    String deleteDelivery(Long id);
    List<Delivery> getAllDeliveriesByStatus(DeliveryStatus status);
    Delivery getDeliveriesByCourier(Long id);
    String updateDeliveryStatus(long id, DeliveryStatus status);
 }
