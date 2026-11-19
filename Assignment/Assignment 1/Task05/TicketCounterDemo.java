class TicketCounter {
    private static long nextNumber = 1000;
    private TicketCounter() {}
    static synchronized long nextTicketNumber() { return ++nextNumber; }
}
public class TicketCounterDemo {
    public static void main(String[] args) {
        for (int i=0; i<3; i++) System.out.println(TicketCounter.nextTicketNumber());
    }
}