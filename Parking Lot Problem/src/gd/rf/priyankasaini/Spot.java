package gd.rf.priyankasaini;

public abstract class Spot {
    public static int spotUniqueID = 1;
    public abstract void createSpot(int floorToCreateOn, double spotCost, boolean hasElectricPanel);
    public abstract void deleteSpot(Spot spot, int floor);
    public abstract void increaseCountOfSpot();
    public abstract void decreaseCountOfSpot();
    public abstract void increaseCountOfOccupiedSpot();
    public abstract void decreaseCountOfOccupiedSpot();
    public abstract void generateTicketForSpot(boolean needCharging);


    public void increaseUniqueID() {
        spotUniqueID++;
    }

}
