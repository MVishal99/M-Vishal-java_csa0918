public class DataRepresentationDemo {
    public static void main(String[] args) {
        int[] slotsPerFloor = {20, 30, 50}; // jagged structure supports unequal floors
        String[][] slots = new String[slotsPerFloor.length][];
        for (int f=0; f<slotsPerFloor.length; f++) slots[f] = new String[slotsPerFloor[f]];
        int minutes = 61;
        int billableHours = (minutes + 59) / 60; // ceiling using integer arithmetic
        double hourlyRate = 30.0;
        double fee = billableHours * hourlyRate;
        System.out.println("Floors: " + slots.length);
        System.out.println("Slots on floor 2: " + slots[2].length);
        System.out.println("Fee: Rs " + fee);
        System.out.println("Wrong integer division example: " + (minutes / 60));
    }
}