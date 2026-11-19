import java.time.LocalDateTime;
import java.util.Objects;

class Vehicle {
    final String registration;
    final String type;
    Vehicle(String registration, String type) {
        if (registration == null || registration.trim().isEmpty()) throw new IllegalArgumentException("Registration required");
        this.registration = registration; this.type = Objects.requireNonNull(type);
    }
}
class ParkingSlot {
    final int floor, number;
    Vehicle occupant;
    ParkingSlot(int floor, int number) { this.floor=floor; this.number=number; }
    boolean isFree() { return occupant == null; }
}
class ParkingTicket {
    final long ticketNo;
    final Vehicle vehicle;
    final LocalDateTime entryTime;
    final ParkingSlot slot;
    ParkingTicket(long ticketNo, Vehicle vehicle, ParkingSlot slot) {
        this.ticketNo=ticketNo; this.vehicle=vehicle; this.slot=slot; this.entryTime=LocalDateTime.now();
    }
}
public class ParkingModel {
    public static void main(String[] args) {
        ParkingSlot slot = new ParkingSlot(0, 1);
        Vehicle car = new Vehicle("TN01AB1234", "CAR");
        slot.occupant = car;
        ParkingTicket ticket = new ParkingTicket(1, car, slot);
        System.out.println(ticket.ticketNo + " " + ticket.vehicle.registration + " " + ticket.slot.isFree());
    }
}