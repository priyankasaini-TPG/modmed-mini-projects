package helpers;

public class Orientation {
    private String direction;

    public Orientation(){
        this.direction = "E";
    }

    public Orientation(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void leftRotate(){
        switch (direction){
            case "E":
                this.direction = "N";
                break;
            case "W":
                this.direction = "S";
                break;
            case "N":
                this.direction = "W";
                break;
            case "S":
                this.direction = "E";
                break;
        }
    }

    public void rightRotate(){
        switch (direction){
            case "E":
                this.direction = "S";
                break;
            case "W":
                this.direction = "N";
                break;
            case "N":
                this.direction = "E";
                break;
            case "S":
                this.direction = "W";
                break;
        }
    }

    @Override
    public String toString() {
        return direction;
    }
}
