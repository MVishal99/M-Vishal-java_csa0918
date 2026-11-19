import java.time.LocalDateTime;
public class ParkingTicket {
    private final long ticketNumber;
    private final String registration;
    private final LocalDateTime entryTime;
    public ParkingTicket(long ticketNumber, String registration) {
        if (ticketNumber <= 0) throw new IllegalArgumentException("Ticket number must be positive");
        if (registration == null || registration.trim().isEmpty()) throw new IllegalArgumentException("Registration required");
        this.ticketNumber=ticketNumber; this.registration=registration; this.entryTime=LocalDateTime.now();
    }
    public ParkingTicket(ParkingTicket other) { this(other.ticketNumber, other.registration); }
    public long getTicketNumber() { return ticketNumber; }
    public String getRegistration() { return registration; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public static void main(String[] args) {
        ParkingTicket t = new ParkingTicket(101, "TN01AB1234");
        ParkingTicket copy = new ParkingTicket(t);
        System.out.println(copy.getTicketNumber() + " " + copy.getRegistration());
    }
}