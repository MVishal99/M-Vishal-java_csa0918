class SafeSlot {
    private final int floor;
    private final int slot;
    private String registration;
    SafeSlot(int floor, int slot) {
        if (floor<0 || slot<0) throw new IllegalArgumentException("Invalid slot position");
        this.floor=floor; this.slot=slot;
    }
    public int getFloor() { return floor; }
    public int getSlot() { return slot; }
    public String getRegistration() { return registration; }
    public void occupy(String reg) {
        if (registration!=null) throw new IllegalStateException("Slot occupied");
        if (reg==null || reg.trim().isEmpty()) throw new IllegalArgumentException("Registration required");
        registration=reg;
    }
    public void release() {
        if (registration==null) throw new IllegalStateException("Slot already free");
        registration=null;
    }
}
public class EncapsulationDemo {
    public static void main(String[] args) {
        SafeSlot s=new SafeSlot(1,4); s.occupy("TN01AB1234");
        System.out.println(s.getFloor()+":"+s.getSlot()+" "+s.getRegistration());
    }
}