import java.util.*;
class ParkingSystem {
 private final Map<String,int[]> active=new HashMap<>();
 private final String[][] floors;
 ParkingSystem(int... capacities){floors=new String[capacities.length][];for(int i=0;i<capacities.length;i++)floors[i]=new String[capacities[i]];}
 int[] enter(String reg){if(active.containsKey(reg))throw new IllegalArgumentException("Duplicate registration");for(int f=0;f<floors.length;f++)for(int s=0;s<floors[f].length;s++)if(floors[f][s]==null){floors[f][s]=reg;active.put(reg,new int[]{f,s});return new int[]{f,s};}return null;}
 boolean exit(String reg){int[] p=active.remove(reg);if(p==null)return false;floors[p[0]][p[1]]=null;return true;}
 public static void main(String[] args){ParkingSystem p=new ParkingSystem(2,1);System.out.println(Arrays.toString(p.enter("TN01AA0001")));System.out.println(Arrays.toString(p.enter("TN02BB0002")));System.out.println(p.exit("TN01AA0001"));System.out.println(Arrays.toString(p.enter("TN03CC0003")));}
}