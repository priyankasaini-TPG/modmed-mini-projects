package drivemodes;

import helpers.Orientation;
import helpers.Position;
import interfaces.RobotDrive;

public class RobotArcadeDrive implements RobotDrive {

    Position position;
    Orientation orientation;

    public RobotArcadeDrive() {
        this.position = new Position();
        this.orientation = new Orientation();
        System.out.println(position + " " + orientation);
    }

    @Override
    public void moveForward() {
        switch (orientation.getDirection()){
            case "E":
                this.position.setX(this.position.getX()+1);
                break;
            case "W":
                this.position.setX(this.position.getX()-1);
                break;
            case "N":
                this.position.setY(this.position.getY()-1);
                break;
            case "S":
                this.position.setY(this.position.getY()+1);
                break;
        }
        System.out.println(position + " " + orientation);
    }

    @Override
    public void moveBackward() {
        switch (orientation.getDirection()){
            case "E":
                this.position.setX(this.position.getX()-1);
                break;
            case "W":
                this.position.setX(this.position.getX()+1);
                break;
            case "N":
                this.position.setY(this.position.getY()+1);
                break;
            case "S":
                this.position.setY(this.position.getY()-1);
                break;
        }
        System.out.println(position + " " + orientation);
    }

    @Override
    public void moveLeft() {
        switch (orientation.getDirection()){
            case "E":
                this.position.setY(this.position.getY()-1);
                this.position.setX(this.position.getX()+1);
                break;
            case "W":
                this.position.setY(this.position.getY()+1);
                this.position.setX(this.position.getX()-1);
                break;
            case "N":
                this.position.setX(this.position.getX()-1);
                this.position.setY(this.position.getY()-1);
                break;
            case "S":
                this.position.setX(this.position.getX()+1);
                this.position.setY(this.position.getY()+1);
                break;
        }
        this.orientation.leftRotate();
        System.out.println(position + " " + orientation);
    }

    @Override
    public void moveRight() {
        switch (orientation.getDirection()){
            case "E":
                this.position.setY(this.position.getY()+1);
                this.position.setX(this.position.getX()+1);
                break;
            case "W":
                this.position.setY(this.position.getY()-1);
                this.position.setX(this.position.getX()-1);
                break;
            case "N":
                this.position.setX(this.position.getX()+1);
                this.position.setY(this.position.getY()-1);
                break;
            case "S":
                this.position.setX(this.position.getX()-1);
                this.position.setY(this.position.getY()+1);
                break;
        }
        this.orientation.rightRotate();
        System.out.println(position + " " + orientation);
    }

}
