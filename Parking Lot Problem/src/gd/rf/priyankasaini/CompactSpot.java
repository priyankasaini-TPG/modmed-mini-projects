package gd.rf.priyankasaini;

import java.util.Date;
import java.util.List;

public class CompactSpot extends Spot{

    private static int countOfCompactSpot = 0;
    private static int countOfCompactSpotOccupied = 0;
    private int sId;
    private double sCost;
    private boolean hasElectricPanel;
    private boolean isOccupied;

    public CompactSpot() {
    }

    public CompactSpot(int sId, double sCost, boolean hasElectricPanel, boolean isOccupied) {
        this.sId = sId;
        this.sCost = sCost;
        this.hasElectricPanel = hasElectricPanel;
        this.isOccupied = isOccupied;
    }

    public static int getCountOfCompactSpot() {
        return countOfCompactSpot;
    }

    public static int getCountOfCompactSpotOccupied() {
        return countOfCompactSpotOccupied;
    }

    public static void setCountOfCompactSpotOccupied(int countOfCompactSpotOccupied) {
        CompactSpot.countOfCompactSpotOccupied = countOfCompactSpotOccupied;
    }

    public static void setCountOfCompactSpot(int countOfCompactSpot) {
        CompactSpot.countOfCompactSpot = countOfCompactSpot;
    }

    public static int getCountOfFreeSpot() {
        return getCountOfCompactSpot()-getCountOfCompactSpotOccupied();
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
        CompactSpot.setCountOfCompactSpot(CompactSpot.getCountOfCompactSpot()+1);
    }

    @Override
    public void decreaseCountOfSpot() {
        CompactSpot.setCountOfCompactSpot(CompactSpot.getCountOfCompactSpot()-1);
    }

    @Override
    public void increaseCountOfOccupiedSpot() {
        CompactSpot.setCountOfCompactSpotOccupied(CompactSpot.getCountOfCompactSpotOccupied()+1);
    }

    @Override
    public void decreaseCountOfOccupiedSpot() {
        CompactSpot.setCountOfCompactSpotOccupied(CompactSpot.getCountOfCompactSpotOccupied()-1);
    }

    @Override
    public void generateTicketForSpot(boolean chargeNeeded) {
        Ticket ticket = new Ticket(new CompactSpot(), new Date());
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
        return "CompactSpot{" +
                "sId=" + sId +
                ", sCost=" + sCost +
                ", hasElectricPanel=" + hasElectricPanel +
                ", isOccupied=" + isOccupied +
                '}';
    }

    @Override
    public void createSpot(int floorNo, double cost, boolean ePanel) {
        Floor foorToPutSpotIn = ParkingLot.getPlFloorList().get(floorNo-1);
        int spotID = spotUniqueID;
        CompactSpot obj =  new CompactSpot(spotID, cost, ePanel, false);
        foorToPutSpotIn.setFull(false);
        foorToPutSpotIn.getfSpotList().add(obj);
        System.out.println("Spot Added "+ obj);
        increaseUniqueID();
        increaseCountOfSpot();
    }

    @Override
    public void deleteSpot(Spot spot, int floorIndex) {
        CompactSpot compactSpot = (CompactSpot) spot;
        if(compactSpot.isOccupied() == true) { System.out.println("[Error]: Spot is Occupied, can't be Deleted"); return; }
        Floor floorOfSpot = ParkingLot.getPlFloorList().get(floorIndex-1);
        List<Spot> spotList = floorOfSpot.getfSpotList();
        spotList.remove(spot);
        System.out.println("Spot Removed "+spot);
        decreaseCountOfSpot();
    }

}
