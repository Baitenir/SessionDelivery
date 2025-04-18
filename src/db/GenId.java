package db;

public class GenId {
    private static Long deliveryId = 0L;
    private static Long courierId = 0L;
    private static Long packageId = 0L;

    public static long delId(){
        return ++deliveryId;
    }

    public static long courierId(){
        return ++courierId;
    }

    public static long packageId(){
        return ++packageId;
    }
}
