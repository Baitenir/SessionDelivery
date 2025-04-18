package service.impl;
import db.Database;
import enums.PackageType;
import models.Package1;
import service.Package1Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class Package1ServiceImpl implements Package1Service {


    @Override
    public String createPackage(Package1 aPackage) {
        Database.package1s.add(aPackage);
        return "success";
    }

    @Override
    public List<Package1> sortPackageByType(PackageType type) {
        return Database.package1s.stream()
                .filter(p -> p.getPackage1Type() == type)
                .sorted(Comparator.comparing(Package1::getId)) // можно поменять на другой критерий
                .collect(Collectors.toList());
    }

    @Override
    public List<Package1> sortPackageByWeight(double maxWeight) {
        return Database.package1s.stream()
                .filter(p -> p.getWeight() <= maxWeight)
                .sorted(Comparator.comparingDouble(Package1::getWeight))
                .collect(Collectors.toList());
    }
}
