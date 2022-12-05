package gd.rf.priyankasaini;

import java.util.Date;
import java.util.List;

public class LargeSpot extends Spot {

    private static int countOfLargeSpot = 0;
    private static int countOfLargeSpotOccupied = 0;
    private int sId;
    private double sCost;
    private boolean hasElectricPanel;
    private boolean isOccupied;

    public LargeSpot() { }

    public LargeSpot(int sId, double sCost, boolean hasElectricPanel, boolean isOccupied) {
        this.sId = sId;
        this.sCost = sCost;
        this.hasElectricPanel = hasElectricPanel;
        this.isOccupied = isOccupied;
    }

    public static int getCountOfLargeSpot() {
        return countOfLargeSpot;
    }

    public static void setCountOfLargeSpot(int countOfLargeSpot) {
        LargeSpot.countOfLargeSpot = countOfLargeSpot;
    }

    public static int getCountOfLargeSpotOccupied() {
        return countOfLargeSpotOccupied;
    }

    public static void setCountOfLargeSpotOccupied(int countOfLargeSpotOccupied) {
        LargeSpot.countOfLargeSpotOccupied = countOfLargeSpotOccupied;
    }

    public static int getCountOfFreeSpot() {
        return getCountOfLargeSpot()-getCountOfLargeSpotOccupied();
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public double getsCost() {
        return sCost;
    }

    public void setsCost(double sCost) {
        this.sCost = sCost;
    }

    public boolean isHasElectricPanel() {
        return hasElectricPanel;
    }

    public void setHasElectricPanel(boolean hasElectricPanel) {
        this.hasElectricPanel = hasElectricPanel;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public void increaseCountOfSpot() {
        LargeSpot.setCountOfLargeSpot(LargeSpot.getCountOfLargeSpot()+1);
    }

    @Override
    public void decreaseCountOfSpot() {
        LargeSpot.setCountOfLargeSpot(LargeSpot.getCountOfLargeSpot()-1);
    }

    @Override
    public void increaseCountOfOccupiedSpot() {
        LargeSpot.setCountOfLargeSpotOccupied(LargeSpot.getCountOfLargeSpotOccupied()+1);
    }

    @Override
    public void decreaseCountOfOccupiedSpot() {
        LargeSpot.setCountOfLargeSpotOccupied(LargeSpot.getCountOfLargeSpotOccupied()-1);
    }

    @Override
    public void generateTicketForSpot(boolean chargeNeeded) {
        Ticket ticket = new Ticket(new LargeSpot(), new Date());
        Spot spotFound = ticket.generateTicket(chargeNeeded);
        if(spotFound == null) {
            System.out.println("[Error]: Can't Find a spot with specified details, try taking spot without a Charging Panel");
            return;
        }
        ticket.setSpotType(spotFound);
        System.out.println(ticket);
        this.increaseCountOfOccupiedSpot();
    }

    @Override
    public String toString() {
        return "LargeSpot{" +
                "sId=" + sId +
                ", sCost=" + sCost +
                ", hasElectricPanel=" + hasElectricPanel +
                ", isOccupied=" + isOccupied +
                '}';
    }

    @Override
    public void createSpot(int floorNo, double cost, boolean ePanel) {
        Floor foorToPutSpotIn = ParkingLot.getPlFloorList().get(floorNo-1);
        int spotID = foorToPutSpotIn.getfSpotList().size()+1;
        LargeSpot obj =  new LargeSpot(spotID, cost, ePanel, false);
        foorToPutSpotIn.setFull(false);
        foorToPutSpotIn.getfSpotList().add(obj);
        System.out.println("Spot Added "+ obj);
        increaseUniqueID();
        increaseCountOfSpot();
    }

    @Override
    public void deleteSpot(Spot spot, int floorIndex) {
        LargeSpot largeSpot = (LargeSpot) spot;
        if(largeSpot.isOccupied() == true) { System.out.println("[Error]: Spot is Occupied, can't be Deleted"); return; }
        Floor floorOfSpot = ParkingLot.getPlFloorList().get(floorIndex-1);
        List<Spot> spotList = floorOfSpot.getfSpotList();
        spotList.remove(spot);
        System.out.println("Spot Removed "+spot);
        decreaseCountOfSpot();
    }
}
