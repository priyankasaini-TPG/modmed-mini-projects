import drivemodes.RobotArcadeDrive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        RobotArcadeDrive d = new RobotArcadeDrive();
        File file = new File("C:\\Users\\priyanka.saini\\Documents\\GitHub\\modmed-mini-projects\\Robot\\src\\inputfiles\\arcadeInput.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String character = sc.nextLine();
            if (character.equalsIgnoreCase("F"))
                d.moveForward();
            else if (character.equalsIgnoreCase("B"))
                d.moveBackward();
            else if (character.equalsIgnoreCase("L"))
                d.moveLeft();
            else if (character.equalsIgnoreCase("R"))
                d.moveRight();
            else
                System.out.println("You enter an invalid instruction!");
        }


    }
}