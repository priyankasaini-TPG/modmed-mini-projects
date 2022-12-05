package gd.rf.priyankasaini;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ParkingLot.setpLName("PS Parking Lot");
        ParkingLot.setFull(true);
        Scanner sc = new Scanner(System.in);
        customerOrOwner:while (true) {
            System.out.println("\nWelcome to " + ParkingLot.getpLName());
            System.out.println("Are you here?\n1. To Park(Customers)\n2. To Leave(Customers)\n3. To Upgrade(Owner of Parking Lot)");
            System.out.print("Select a Option from above: ");
            int customerOrOwner = sc.nextInt();
            option:switch (customerOrOwner) {
                case 1:
                    System.out.println("\nSelect a appropriate Vehicle type for your vehicle so that we can assign you a parking spot.");
                    System.out.println("1. CompactSpot(Car, Van) | Available: "+CompactSpot.getCountOfFreeSpot());
                    System.out.println("2. LargeSpot(Trucks) | Available: "+LargeSpot.getCountOfFreeSpot());
                    System.out.println("3. HandicappedSpot(For their Rides) | Available: "+HandicappedSpot.getCountOfFreeSpot());
                    System.out.println("4. MotorcycleSpot(Motorcycles) | Available: "+MotorcycleSpot.getCountOfFreeSpot());
                    System.out.println("TYPE -1 to GO BACK TO MENU");
                    System.out.print("Enter Option: "); int vehicleChoice = sc.nextInt(); if(vehicleChoice == -1) continue customerOrOwner;

                    boolean needCharging = false;
                    while(true) {
                        System.out.print("Do you need a spot with charging panel?(y/n): "); String sEPanel = sc.next();
                        if (sEPanel.equalsIgnoreCase("y") || sEPanel.equalsIgnoreCase("n")) {
                            if (sEPanel.equalsIgnoreCase("y")) needCharging = true;
                            break;
                        } else System.out.println("[Error]: Entered a Invalid Option");
                    }
                    switch (vehicleChoice) {
                        case 1:
                            new CompactSpot().generateTicketForSpot(needCharging);
                            break;
                        case 2:
                            new LargeSpot().generateTicketForSpot(needCharging);
                            break;
                        case 3:
                            new HandicappedSpot().generateTicketForSpot(needCharging);
                            break;
                        case 4:
                            new MotorcycleSpot().generateTicketForSpot(needCharging);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\nEnter your Ticket ID:");
                    System.out.print("Enter Ticker ID: "); int ticketID = sc.nextInt(); if(ticketID == -1) continue customerOrOwner;
                    if(Ticket.findTicket(ticketID)) {
                        new Ticket().collectTicket(ticketID);
                        System.out.println("Ticket Collected");
                    } else {
                        System.out.println("Ticket Not Found");
                    }
                    break;
                case 3:
                    System.out.println("\nWelcome to "+ParkingLot.getpLName()+" Management System");
                    System.out.println("Select a Option from below");
                    System.out.println("1. Floor");
                    System.out.println("2. Spot");
                    System.out.println("TYPE -1 to GO BACK TO MENU");
                    System.out.print("Enter Option: "); int option = sc.nextInt(); if(option == -1) continue customerOrOwner;
                    switch (option) {
                        case 1:
                            System.out.print("\nFloor: "); System.out.println("Select a Option from below");
                            System.out.println("1. Add Floor");
                            System.out.println("2. Delete Floor");
                            System.out.println("3. Get Floors");
                            System.out.println("TYPE -1 to GO BACK TO MENU");
                            System.out.print("Enter Option: "); int floorOption = sc.nextInt(); if(floorOption == -1) continue customerOrOwner;
                            switch (floorOption) {
                                case 1:
                                    int floor = ParkingLot.getpLFloors();
                                    ParkingLot.setpLFloors(++floor);
                                    Floor newFloor = new Floor(floor, new ArrayList<Spot>(), true);
                                    ParkingLot.addFloor(newFloor);
                                    System.out.println("A new Floor Added: "+newFloor);
                                    break;
                                case 2:
                                    if(ParkingLot.getPlFloorList().size() <= 0) {
                                        System.out.println("[Error]: No Floor to Delete");
                                        break;
                                    }
                                    System.out.print("\nDelete Floor: "); System.out.println("Select a Floor from below to delete");
                                    int index = 0; int highestFloor = ParkingLot.getpLFloors();
                                    for (Floor f: ParkingLot.getPlFloorList()) {
                                        System.out.println((index+1)+". "+f);
                                        index++;
                                    }
                                    System.out.println("TYPE -1 to GO BACK TO MENU");
                                    System.out.print("Enter Floor: "); int floorToDelete = sc.nextInt(); if(floorToDelete == -1) continue customerOrOwner;
                                    while(floorToDelete > highestFloor) {
                                        System.out.println("[ERROR]: You Entered a Wrong Floor");
                                        System.out.print("Enter Floor: "); floorToDelete = sc.nextInt();
                                    }
                                    Floor fConfirmToDelete = ParkingLot.getPlFloorList().get(floorToDelete-1);
                                    ParkingLot.removeFloor(fConfirmToDelete, floorToDelete);
                                    ParkingLot.setpLFloors(ParkingLot.getPlFloorList().size());
                                    break;
                                case 3:
                                    if(ParkingLot.getPlFloorList().size() <= 0) {
                                        System.out.println("[Info]: No floor are available");
                                        break;
                                    }
                                    System.out.print("\nGet Floor: "); System.out.println("Details of floors");
                                    int i = 0;
                                    for (Floor f: ParkingLot.getPlFloorList()) {
                                        System.out.println((i+1)+". "+f);
                                        i++;
                                    }
                                    break;
                            }
                            break;
                        case 2:
                            System.out.print("\nSpot: "); System.out.println("Select a Option from below");
                            System.out.println("1. Add Spot");
                            System.out.println("2. Delete Spot");
                            System.out.println("3. Get Spot");
                            System.out.print("Enter Option: "); int spotOption = sc.nextInt();
                            switch (spotOption) {
                                case 1:
                                    String sType;
                                    int sFloor;
                                    double sCost;
                                    boolean sHasEPanel = false;
                                    while(true) {
                                        System.out.print("Enter Spot Type(cs/ls/hs/ms): "); sType = sc.next();
                                        if(sType.equalsIgnoreCase("cs") || sType.equalsIgnoreCase("ls") || sType.equalsIgnoreCase("hs") || sType.equalsIgnoreCase("ms")) break;
                                        else System.out.println("[Error]: Entered a Invalid Option");
                                    }
                                    while(true) {
                                        System.out.print("Enter Spot Floor: "); sFloor = sc.nextInt();
                                        if(sFloor <= ParkingLot.getPlFloorList().size()) break;
                                        else System.out.println("[Error]: Entered a Invalid Floor");
                                    }
                                    while(true) {
                                        System.out.print("Enter Spot Cost: ");  sCost = sc.nextDouble();
                                        break;
                                    }
                                    while(true) {
                                        System.out.print("Does Spot has Electric Panel(y/n): ");
                                        String sEPanel = sc.next();
                                        if (sEPanel.equalsIgnoreCase("y") || sEPanel.equalsIgnoreCase("n")) {
                                            if (sEPanel.equalsIgnoreCase("y")) sHasEPanel = true;
                                            break;
                                        } else System.out.println("[Error]: Entered a Invalid Option");
                                    }
                                    switch (sType) {
                                        case "cs":
                                            CompactSpot cs = new CompactSpot();
                                            cs.createSpot(sFloor, sCost, sHasEPanel);
                                            break;
                                        case "ls":
                                            LargeSpot ls = new LargeSpot();
                                            ls.createSpot(sFloor, sCost, sHasEPanel);
                                            break;
                                        case "hs":
                                            HandicappedSpot hs = new HandicappedSpot();
                                            hs.createSpot(sFloor, sCost, sHasEPanel);
                                            break;
                                        case "ms":
                                            MotorcycleSpot ms = new MotorcycleSpot();
                                            ms.createSpot(sFloor, sCost, sHasEPanel);
                                            break;
                                    }
                                    break;
                                case 2:
                                    int floor;
                                    int fSpot;
                                    while(true) {
                                        System.out.print("\nDelete Spot: "); System.out.print("Enter floor whose spot you want to delete:"); floor = sc.nextInt();
                                        if(floor <= ParkingLot.getPlFloorList().size()) break;
                                        else System.out.println("[Error]: Entered a Invalid Floor");
                                    }
                                    if(ParkingLot.getPlFloorList().get(floor-1).getfSpotList().size() <= 0) {
                                        System.out.println("[Error]: No Spot to Delete on that Floor");
                                        break;
                                    }
                                    System.out.print("\nDelete Spot: "); System.out.println("Select a Spot from below to delete from floor "+floor+":");
                                    int index = 0; int lastSpot = ParkingLot.getPlFloorList().get(floor-1).getfSpotList().size();
                                    for (Spot s: ParkingLot.getPlFloorList().get(floor-1).getfSpotList()) {
                                        System.out.println((index+1)+". "+s);
                                        index++;
                                    }
                                    System.out.print("Enter Spot: "); int spotToDelete = sc.nextInt();
                                    while(spotToDelete > lastSpot) {
                                        System.out.println("[ERROR]: You Entered a Wrong Spot");
                                        System.out.print("Enter Spot: "); spotToDelete = sc.nextInt();
                                    }
                                    Spot sConfirmToDelete = ParkingLot.getPlFloorList().get(floor-1).getfSpotList().get(spotToDelete-1);
                                    if(sConfirmToDelete.getClass().equals(CompactSpot.class)) {
                                        new CompactSpot().deleteSpot(sConfirmToDelete, floor);
                                    } else if(sConfirmToDelete.getClass().equals(LargeSpot.class)) {
                                        new LargeSpot().deleteSpot(sConfirmToDelete, floor);
                                    } else if(sConfirmToDelete.getClass().equals(HandicappedSpot.class)) {
                                        new HandicappedSpot().deleteSpot(sConfirmToDelete, floor);
                                    } else if(sConfirmToDelete.getClass().equals(MotorcycleSpot.class)) {
                                        new MotorcycleSpot().deleteSpot(sConfirmToDelete, floor);
                                    }
                                    break;
                                case 3:
                                    System.out.println(ParkingLot.getPlFloorList());
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
    }
}
