package gd.rf.priyankasaini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static String pLName;
    private static int pLFloors;
    private static boolean isFull;
    private static List<Floor> plFloorList = new ArrayList<Floor>();
    private static Map<Integer, Spot> pLTicket2Vehicle = new HashMap<>();

    public ParkingLot() {

    }

    public ParkingLot(String pLName, int pLFloors, boolean isFull, List<Floor> plFloorList, Map<Integer, Spot> pLTicket2Vehicle) {
        ParkingLot.pLName = pLName;
        ParkingLot.pLFloors = pLFloors;
        ParkingLot.isFull = isFull;
        ParkingLot.plFloorList = plFloorList;
        ParkingLot.pLTicket2Vehicle = pLTicket2Vehicle;
    }

    public static String getpLName() {
        return pLName;
    }

    public static void setpLName(String pLName) {
        ParkingLot.pLName = pLName;
    }

    public static int getpLFloors() {
        return pLFloors;
    }

    public static void setpLFloors(int pLFloors) {
        ParkingLot.pLFloors = pLFloors;
    }

    public static boolean isFull() {
        return isFull;
    }

    public static void setFull(boolean full) {
        ParkingLot.isFull = full;
    }

    public static List<Floor> getPlFloorList() {
        return plFloorList;
    }

    public static void setPlFloorList() {
        ParkingLot.plFloorList = plFloorList;
    }

    public static Map<Integer, Spot> getpLTicket2Vehicle() {
        return pLTicket2Vehicle;
    }

    public static void setpLTicket2Vehicle(Map<Integer, Spot> pLTicket2Vehicle) {
        ParkingLot.pLTicket2Vehicle = pLTicket2Vehicle;
    }

//  Methods
    public static void addFloor(Floor floor) {
        System.out.println("Floor Added");
        plFloorList.add(floor);
    }
    public static void removeFloor(Floor floor, int floorIndex) {
        System.out.println("Floor Removed "+floorIndex);
        plFloorList.remove(floor);
        int index = 0;
        for (Floor f: plFloorList) {
            if(index == floorIndex-1) {
                f.setfNo(floorIndex);
            } else if(index > floorIndex-1){
                f.setfNo(f.getfNo()-1);
            }
            index++;
        }

    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "pLName='" + pLName + '\'' +
                ", pLFloors=" + pLFloors +
                ", isFull=" + isFull +
                ", plFloorList=" + plFloorList +
                ", pLTicket2Vehicle=" + pLTicket2Vehicle +
                '}';
    }
}
