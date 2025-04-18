package service;
import enums.PackageType;
import models.Package1;
import java.util.List;

public interface Package1Service {
    String createPackage(Package1 aPackage);
    List<Package1> sortPackageByType(PackageType type);
    List<Package1> sortPackageByWeight(double weight);
}
