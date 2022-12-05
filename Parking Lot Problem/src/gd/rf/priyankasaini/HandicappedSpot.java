package gd.rf.priyankasaini;

import java.util.Date;
import java.util.List;

public class HandicappedSpot extends Spot {

    private static int countOfHandicappedSpot = 0;
    private static int countOfHandicappedOccupied = 0;
    private int sId;
    private double sCost;
    private boolean hasElectricPanel;
    private boolean isOccupied;

    public HandicappedSpot() { }

    public HandicappedSpot(int sId, double sCost, boolean hasElectricPanel, boolean isOccupied) {
        this.sId = sId;
        this.sCost = sCost;
        this.hasElectricPanel = hasElectricPanel;
        this.isOccupied = isOccupied;
    }

    public static int getCountOfHandicappedSpot() {
        return countOfHandicappedSpot;
    }

    public static void setCountOfHandicappedSpot(int countOfHandicappedSpot) {
        HandicappedSpot.countOfHandicappedSpot = countOfHandicappedSpot;
    }

    public static int getCountOfHandicappedOccupied() {
        return countOfHandicappedOccupied;
    }

    public static void setCountOfHandicappedOccupied(int countOfHandicappedOccupied) {
        HandicappedSpot.countOfHandicappedOccupied = countOfHandicappedOccupied;
    }

    public static int getCountOfFreeSpot() {
        return getCountOfHandicappedSpot()-getCountOfHandicappedOccupied();
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
        HandicappedSpot.setCountOfHandicappedSpot(HandicappedSpot.getCountOfHandicappedSpot()+1);
    }

    @Override
    public void decreaseCountOfSpot() {
        HandicappedSpot.setCountOfHandicappedSpot(HandicappedSpot.getCountOfHandicappedSpot()-1);
    }

    @Override
    public void increaseCountOfOccupiedSpot() {
        HandicappedSpot.setCountOfHandicappedOccupied(HandicappedSpot.getCountOfHandicappedOccupied()+1);
    }

    @Override
    public void decreaseCountOfOccupiedSpot() {
        HandicappedSpot.setCountOfHandicappedOccupied(HandicappedSpot.getCountOfHandicappedOccupied()-1);
    }

    @Override
    public void generateTicketForSpot(boolean chargeNeeded) {
        Ticket ticket = new Ticket(new HandicappedSpot(), new Date());
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
        return "HandicappedSpot{" +
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
        HandicappedSpot obj =  new HandicappedSpot(spotID, cost, ePanel, false);
        foorToPutSpotIn.setFull(false);
        foorToPutSpotIn.getfSpotList().add(obj);
        System.out.println("Spot Added "+ obj);
        increaseUniqueID();
        increaseCountOfSpot();
    }

    @Override
    public void deleteSpot(Spot spot, int floorIndex) {
        HandicappedSpot handicappedSpot = (HandicappedSpot) spot;
        if(handicappedSpot.isOccupied() == true) { System.out.println("[Error]: Spot is Occupied, can't be Deleted"); return; }
        Floor floorOfSpot = ParkingLot.getPlFloorList().get(floorIndex-1);
        List<Spot> spotList = floorOfSpot.getfSpotList();
        spotList.remove(spot);
        System.out.println("Spot Removed "+spot);
        decreaseCountOfSpot();
    }
}
