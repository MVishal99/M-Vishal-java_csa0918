interface Payable { double amountDue(); }
abstract class AbstractVehicle {
    private final String registration;
    AbstractVehicle(String registration) { this.registration=registration; }
    String registration() { return registration; }
    abstract double parkingFee(int minutes);
}
class VisitorCar extends AbstractVehicle implements Payable {
    VisitorCar(String registration) { super(registration); }
    public double parkingFee(int minutes) { return ((minutes+59)/60)*30.0; }
    public double amountDue() { return parkingFee(90); }
}
public class AbstractionDemo {
    public static void main(String[] args) {
        Payable p=new VisitorCar("TN01AB1234");
        System.out.println("Amount due: Rs "+p.amountDue());
    }
}