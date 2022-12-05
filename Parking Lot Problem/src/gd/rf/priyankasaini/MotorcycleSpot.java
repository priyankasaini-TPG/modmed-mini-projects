package gd.rf.priyankasaini;

import java.util.Date;
import java.util.List;

public class MotorcycleSpot extends Spot {

    private static int countOfMotorcycleSpot = 0;
    private static int countOfMotorcycleOccupied = 0;
    private int sId;
    private double sCost;
    private boolean hasElectricPanel;
    private boolean isOccupied;

    public MotorcycleSpot() { }

    public MotorcycleSpot(int sId, double sCost, boolean hasElectricPanel, boolean isOccupied) {
        this.sId = sId;
        this.sCost = sCost;
        this.hasElectricPanel = hasElectricPanel;
        this.isOccupied = isOccupied;
    }

    public static int getCountOfMotorcycleSpot() {
        return countOfMotorcycleSpot;
    }

    public static void setCountOfMotorcycleSpot(int countOfMotorcycleSpot) {
        MotorcycleSpot.countOfMotorcycleSpot = countOfMotorcycleSpot;
    }

    public static int getCountOfMotorcycleOccupied() {
        return countOfMotorcycleOccupied;
    }

    public static void setCountOfMotorcycleOccupied(int countOfMotorcycleOccupied) {
        MotorcycleSpot.countOfMotorcycleOccupied = countOfMotorcycleOccupied;
    }

    public static int getCountOfFreeSpot() {
        return getCountOfMotorcycleSpot()-getCountOfMotorcycleOccupied();
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
        MotorcycleSpot.setCountOfMotorcycleSpot(MotorcycleSpot.getCountOfMotorcycleSpot()+1);
    }

    @Override
    public void decreaseCountOfSpot() {
        MotorcycleSpot.setCountOfMotorcycleSpot(MotorcycleSpot.getCountOfMotorcycleSpot()-1);
    }

    @Override
    public void increaseCountOfOccupiedSpot() {
        MotorcycleSpot.setCountOfMotorcycleOccupied(MotorcycleSpot.getCountOfMotorcycleOccupied()+1);
    }

    @Override
    public void decreaseCountOfOccupiedSpot() {
        MotorcycleSpot.setCountOfMotorcycleOccupied(MotorcycleSpot.getCountOfMotorcycleOccupied()-1);
    }

    @Override
    public void generateTicketForSpot(boolean chargeNeeded) {
        Ticket ticket = new Ticket(new MotorcycleSpot(), new Date());
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
        return "MotorcycleSpot{" +
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
        MotorcycleSpot obj =  new MotorcycleSpot(spotID, cost, ePanel, false);
        foorToPutSpotIn.setFull(false);
        foorToPutSpotIn.getfSpotList().add(obj);
        System.out.println("Spot Added "+ obj);
        increaseUniqueID();
        increaseCountOfSpot();
    }

    @Override
    public void deleteSpot(Spot spot, int floorIndex) {
        MotorcycleSpot motorcycleSpot = (MotorcycleSpot) spot;
        if(motorcycleSpot.isOccupied() == true) { System.out.println("[Error]: Spot is Occupied, can't be Deleted"); return; }
        Floor floorOfSpot = ParkingLot.getPlFloorList().get(floorIndex-1);
        List<Spot> spotList = floorOfSpot.getfSpotList();
        spotList.remove(spot);
        System.out.println("Spot Removed "+spot);
        decreaseCountOfSpot();
    }
}
