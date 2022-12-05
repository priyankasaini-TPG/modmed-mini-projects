package gd.rf.priyankasaini;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket {


    private  static  int ticketUniqueId = 0;
    private int tId;
    private int floorNumber;
    private Spot spotType;
    private Date generatedOn;

    public Ticket() {
    }

    public Ticket(Spot spotType, Date generatedOn) {
        this.spotType = spotType;
        this.generatedOn = generatedOn;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public static int getTicketUniqueId() {
        return ticketUniqueId;
    }

    public static void setTicketUniqueId(int ticketUniqueId) {
        Ticket.ticketUniqueId = ticketUniqueId;
    }

    public Spot getSpotType() {
        return spotType;
    }

    public void setSpotType(Spot spotType) {
        this.spotType = spotType;
    }

    public Date getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(Date generatedOn) {
        this.generatedOn = generatedOn;
    }

    public Spot generateTicket(boolean needCharge) {
        List<Floor> floorList = ParkingLot.getPlFloorList();
        for (Floor f: floorList) {
            List<Spot> spots =  f.getfSpotList();
            for(Spot s: spots) {
//                System.out.println(s.getClass().toString());
//                System.out.println(spotType.getClass().toString());
                if(this.spotType.getClass().toString().equals(s.getClass().toString()) && this.spotType.getClass().equals(CompactSpot.class)) {
                    CompactSpot cs = (CompactSpot) s;
                    if(cs.isHasElectricPanel() == needCharge && cs.isOccupied() == false) {
                        cs.setOccupied(true);
                        this.setFloorNumber(f.getfNo());
                        Ticket.setTicketUniqueId(getTicketUniqueId()+1);
                        this.settId(getTicketUniqueId());
                        Map<Integer, Spot> map =  new HashMap<>();
                        map.put(getTicketUniqueId(), s);
                        ParkingLot.setpLTicket2Vehicle(map);
                        return s;
                    }
                } else if(this.spotType.getClass().toString().equals(s.getClass().toString()) && this.spotType.getClass().equals(LargeSpot.class)) {
                    LargeSpot cs = (LargeSpot) s;
                    if(cs.isHasElectricPanel() == needCharge && cs.isOccupied() == false) {
                        cs.setOccupied(true);
                        this.setFloorNumber(f.getfNo());
                        Ticket.setTicketUniqueId(getTicketUniqueId()+1);
                        this.settId(getTicketUniqueId());
                        Map<Integer, Spot> map =  new HashMap<>();
                        map.put(getTicketUniqueId(), s);
                        ParkingLot.setpLTicket2Vehicle(map);
                        return s;
                    }
                } else if(this.spotType.getClass().toString().equals(s.getClass().toString()) && this.spotType.getClass().equals(HandicappedSpot.class)) {
                    HandicappedSpot cs = (HandicappedSpot) s;
                    if(cs.isHasElectricPanel() == needCharge && cs.isOccupied() == false) {
                        cs.setOccupied(true);
                        this.setFloorNumber(f.getfNo());
                        Ticket.setTicketUniqueId(getTicketUniqueId()+1);
                        this.settId(getTicketUniqueId());
                        Map<Integer, Spot> map =  new HashMap<>();
                        map.put(getTicketUniqueId(), s);
                        ParkingLot.setpLTicket2Vehicle(map);
                        return s;
                    }
                } else if(this.spotType.getClass().toString().equals(s.getClass().toString()) && this.spotType.getClass().equals(MotorcycleSpot.class)) {
                    MotorcycleSpot cs = (MotorcycleSpot) s;
                    if(cs.isHasElectricPanel() == needCharge && cs.isOccupied() == false) {
                        cs.setOccupied(true);
                        this.setFloorNumber(f.getfNo());
                        Ticket.setTicketUniqueId(getTicketUniqueId()+1);
                        this.settId(getTicketUniqueId());
                        Map<Integer, Spot> map =  new HashMap<>();
                        map.put(getTicketUniqueId(), s);
                        ParkingLot.setpLTicket2Vehicle(map);
                        return s;
                    }
                }
            }
        }
        return null;
    }

    public void collectTicket(int id) {
        Spot s = ParkingLot.getpLTicket2Vehicle().get(id);
        if(s.getClass().equals(CompactSpot.class)) {
            CompactSpot cs = (CompactSpot) s;
            if(cs.isOccupied() == true) {
                cs.setOccupied(false);
                ParkingLot.getpLTicket2Vehicle().remove(id);
            }
        } else if(s.getClass().equals(LargeSpot.class)) {
            LargeSpot cs = (LargeSpot) s;
            if(cs.isOccupied() == true) {
                cs.setOccupied(false);
                ParkingLot.getpLTicket2Vehicle().remove(id);
            }
        } else if(s.getClass().equals(HandicappedSpot.class)) {
            HandicappedSpot cs = (HandicappedSpot) s;
            if(cs.isOccupied() == true) {
                cs.setOccupied(false);
                ParkingLot.getpLTicket2Vehicle().remove(id);
            }
        } else if(s.getClass().equals(MotorcycleSpot.class)) {
            MotorcycleSpot cs = (MotorcycleSpot) s;
            if(cs.isOccupied() == true) {
                cs.setOccupied(false);
                ParkingLot.getpLTicket2Vehicle().remove(id);
            }
        }
    }

    public static boolean findTicket(int id) {
        if(ParkingLot.getpLTicket2Vehicle().get(id) != null) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "tId=" + tId +
                ", floorNumber=" + floorNumber +
                ", spotType=" + spotType +
                ", generatedOn=" + generatedOn +
                '}';
    }
}
