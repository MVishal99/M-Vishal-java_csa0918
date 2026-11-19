import java.util.Objects;
class ParkedVehicle {
    private final String regNo;
    private final String type;
    ParkedVehicle(String regNo, String type) { this.regNo=Objects.requireNonNull(regNo); this.type=type; }
    double calculateFee(int minutes) {
        if (minutes < 0) throw new IllegalArgumentException("Negative duration");
        return ((minutes+59)/60) * ("CAR".equals(type) ? 30 : 10);
    }
    @Override public boolean equals(Object o) {
        return o instanceof ParkedVehicle && regNo.equals(((ParkedVehicle)o).regNo);
    }
    @Override public int hashCode() { return regNo.hashCode(); }
    @Override public String toString() { return regNo + " " + type; }
}
public class VehicleEqualityDemo {
    public static void main(String[] args) {
        ParkedVehicle a=new ParkedVehicle("TN01AB1234","CAR");
        ParkedVehicle b=new ParkedVehicle("TN01AB1234","CAR");
        System.out.println(a.equals(b));
        System.out.println(a.calculateFee(61));
    }
}