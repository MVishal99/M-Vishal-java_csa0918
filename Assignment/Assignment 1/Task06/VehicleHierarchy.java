abstract class Vehicle {
    private final String registration;
    Vehicle(String registration) { this.registration=registration; System.out.println("Vehicle constructor"); }
    String getRegistration() { return registration; }
    abstract double hourlyRate();
}
class Car extends Vehicle {
    Car(String registration) { super(registration); System.out.println("Car constructor"); }
    @Override double hourlyRate() { return 30; }
}
class ElectricCar extends Car {
    ElectricCar(String registration) { super(registration); System.out.println("ElectricCar constructor"); }
}
public class VehicleHierarchy {
    public static void main(String[] args) {
        Vehicle v = new ElectricCar("TN01EV1234");
        System.out.println(v.getRegistration() + " rate=" + v.hourlyRate());
    }
}