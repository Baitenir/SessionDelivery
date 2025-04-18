package service;
import models.Courier;
import java.util.List;

public interface CourierService {
    String addCourier(Courier courier);
    Courier getAvailableCourier(boolean isTrue);
    List<Courier> getAvailableCouriers();
    List<Courier> getAllCouriers();
    List<Courier> getCouriersByRating(double minRating);
    String updateCourierStatus(Long id, boolean isAvailable);
    void setCourierRating(Long id, double newRating);
}
