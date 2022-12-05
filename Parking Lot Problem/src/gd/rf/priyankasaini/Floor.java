package gd.rf.priyankasaini;

import java.util.List;

public class Floor {
    private int fNo;
    private List<Spot> fSpotList;
    private boolean isFull;

    public Floor() { }

    public Floor(int fNo, List<Spot> fSpotList, boolean isFull) {
        this.fNo = fNo;
        this.fSpotList = fSpotList;
        this.isFull = isFull;
    }

    public int getfNo() {
        return fNo;
    }

    public void setfNo(int fNo) {
        this.fNo = fNo;
    }

    public List<Spot> getfSpotList() {
        return fSpotList;
    }

    public void setfSpotList(List<Spot> fSpotList) {
        this.fSpotList = fSpotList;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "fNo=" + fNo +
                ", fSpotList=" + fSpotList +
                ", isFull=" + isFull +
                '}';
    }
}
