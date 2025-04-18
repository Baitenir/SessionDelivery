import db.Database;
import db.GenId;
import enums.DeliveryStatus;
import enums.PackageType;
import models.Courier;
import models.Delivery;
import models.Package1;
import service.CourierService;
import service.DeliveryService;
import service.Package1Service;
import service.impl.CourierServiceImpl;
import service.impl.DeliveryServiceImpl;
import service.impl.Package1ServiceImpl;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DeliveryService deliveryService = new DeliveryServiceImpl();
        CourierService courierService = new CourierServiceImpl();
        Package1Service packageService = new Package1ServiceImpl();

        while (true) {
            System.out.println("""
                    \n===== MAIN MENU =====
                    1. About Package1
                    2. About Courier
                    3. About Delivery
                    0. Exit
                    """);
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handlePackageMenu(scanner, packageService);
                case "2" -> handleCourierMenu(scanner, courierService);
                case "3" -> handleDeliveryMenu(scanner, deliveryService, courierService, packageService);
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // === PACKAGE MENU ===
    private static void handlePackageMenu(Scanner scanner, Package1Service packageService) {
        while (true) {
            System.out.println("""
                    \n--- Package1 Menu ---
                    1. Add Package
                    2. Get Packages by Type
                    3. Get Packages by Max Weight
                    0. Back to Main Menu
                    """);
            System.out.print("Choose option: ");
            String option = scanner.nextLine();
            try {
                switch (option) {
                    case "1" -> {
                        Package1 p = new Package1();
                        p.setId(GenId.packageId());
                        System.out.print("Enter weight: ");
                        p.setWeight(Double.parseDouble(scanner.nextLine()));
                        System.out.println("Choose package type: DOCUMENT, BOX, FOOD");
                        p.setPackage1Type(PackageType.valueOf(scanner.nextLine().toUpperCase()));
                        System.out.println(packageService.createPackage(p));
                    }
                    case "2" -> {
                        System.out.print("Enter package type: ");
                        PackageType type = PackageType.valueOf(scanner.nextLine().toUpperCase());
                        List<Package1> list = packageService.sortPackageByType(type);
                        list.forEach(System.out::println);
                    }
                    case "3" -> {
                        System.out.print("Enter max weight: ");
                        List<Package1> list = packageService.sortPackageByWeight(Double.parseDouble(scanner.nextLine()));
                        list.forEach(System.out::println);
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // === COURIER MENU ===
    private static void handleCourierMenu(Scanner scanner, CourierService courierService) {
        while (true) {
            System.out.println("""
                    \n--- Courier Menu ---
                    1. Add Courier
                    2. Get All Couriers
                    3. Get Available Couriers
                    4. Get Couriers by Rating
                    5. Update Courier Availability
                    6. Set Courier Rating
                    0. Back to Main Menu
                    """);
            System.out.print("Choose option: ");
            String option = scanner.nextLine();
            try {
                switch (option) {
                    case "1" -> {
                        Courier c = new Courier();
                        c.setId(GenId.courierId());
                        System.out.print("Enter full name: ");
                        c.setFullName(scanner.nextLine());
                        System.out.print("Enter rating: ");
                        c.setRating(Double.parseDouble(scanner.nextLine()));
                        System.out.print("Is available (true/false): ");
                        c.setAvailable(Boolean.parseBoolean(scanner.nextLine()));
                        System.out.println(courierService.addCourier(c));
                    }
                    case "2" -> Database.couriers.forEach(System.out::println);
                    case "3" -> courierService.getAvailableCouriers().forEach(System.out::println);
                    case "4" -> {
                        System.out.print("Enter min rating: ");
                        courierService.getCouriersByRating(Double.parseDouble(scanner.nextLine()))
                                .forEach(System.out::println);
                    }
                    case "5" -> {
                        System.out.print("Enter courier ID: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        System.out.print("Available? (true/false): ");
                        System.out.println(courierService.updateCourierStatus(id, Boolean.parseBoolean(scanner.nextLine())));
                    }
                    case "6" -> {
                        System.out.print("Enter courier ID: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        System.out.print("Enter new rating: ");
                        courierService.setCourierRating(id, Double.parseDouble(scanner.nextLine()));
                        System.out.println("Rating updated.");
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // === DELIVERY MENU ===
    private static void handleDeliveryMenu(
            Scanner scanner,
            DeliveryService deliveryService,
            CourierService courierService,
            Package1Service packageService
    ) {
        while (true) {
            System.out.println("""
                    \n--- Delivery Menu ---
                    1. Add Delivery
                    2. Delete Delivery
                    3. Get Deliveries by Status
                    4. Get Delivery by Courier ID
                    5. Update Delivery Status
                    0. Back to Main Menu
                    """);
            System.out.print("Choose option: ");
            String option = scanner.nextLine();
            try {
                switch (option) {
                    case "1" -> {
                        Delivery d = new Delivery();
                        d.setId(GenId.delId());

                        System.out.print("Enter name: ");
                        d.setName(scanner.nextLine());

                        System.out.print("Enter date (yyyy-MM-dd): ");
                        d.setDeliveryDate(LocalDate.parse(scanner.nextLine()));

                        System.out.print("Enter price: ");
                        d.setPrice(Double.parseDouble(scanner.nextLine()));

                        System.out.print("Enter status (WAITING, IN_TRANSIT, DELIVERED): ");
                        d.setDeliveryStatus(DeliveryStatus.valueOf(scanner.nextLine().toUpperCase()));

                        System.out.print("Enter courier ID: ");
                        Long courierId = Long.parseLong(scanner.nextLine());
                        Courier courier = Database.couriers.stream()
                                .filter(c -> c.getId().equals(courierId))
                                .findFirst().orElse(null);
                        if (courier == null) {
                            System.out.println("Courier not found.");
                            break;
                        }

                        System.out.print("Enter package ID: ");
                        Long packageId = Long.parseLong(scanner.nextLine());
                        Package1 pack = Database.package1s.stream()
                                .filter(p -> p.getId().equals(packageId))
                                .findFirst().orElse(null);
                        if (pack == null) {
                            System.out.println("Package not found.");
                            break;
                        }

                        d.setCourier(courier);
                        d.setaPackage(pack);

                        System.out.println(deliveryService.addDelivery(d));
                    }

                    case "2" -> {
                        System.out.print("Enter delivery ID: ");
                        System.out.println(deliveryService.deleteDelivery(Long.parseLong(scanner.nextLine())));
                    }

                    case "3" -> {
                        System.out.print("Enter status (WAITING, IN_TRANSIT, DELIVERED): ");
                        DeliveryStatus status = DeliveryStatus.valueOf(scanner.nextLine().toUpperCase());
                        deliveryService.getAllDeliveriesByStatus(status).forEach(System.out::println);
                    }

                    case "4" -> {
                        System.out.print("Enter courier ID: ");
                        Delivery d = deliveryService.getDeliveriesByCourier(Long.parseLong(scanner.nextLine()));
                        System.out.println(d != null ? d : "No delivery for this courier.");
                    }

                    case "5" -> {
                        System.out.print("Enter delivery ID: ");
                        long id = Long.parseLong(scanner.nextLine());
                        System.out.print("Enter new status: ");
                        DeliveryStatus newStatus = DeliveryStatus.valueOf(scanner.nextLine().toUpperCase());
                        System.out.println(deliveryService.updateDeliveryStatus(id, newStatus));
                    }

                    case "0" -> { return; }
                    default -> System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

