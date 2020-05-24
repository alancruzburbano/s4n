package main.operations;

import main.enumerations.Cardinals;

import java.io.FileWriter;
import java.io.IOException;

public class DroneEngine {

    private int xPosition;
    private int yPosition;
    private Cardinals direction;

    public DroneEngine(int xPosition, int yPosition, Cardinals direction) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
    }

    public void executePath(String path, FileWriter report) throws IOException {
        boolean flagPosition = Boolean.TRUE;
        for (int i = 0; i < path.length(); i++) {
            char instruction = path.charAt(i);
            if (direction == Cardinals.N) {
                flagPosition = executeMovementNorth(instruction);
            } else if (direction == Cardinals.S) {
                flagPosition = executeMovementSouth(instruction);
            } else if (direction == Cardinals.E) {
                flagPosition = executeMovementEast(instruction);
            } else if (direction == Cardinals.W) {
                flagPosition = executeMovementWest(instruction);
            }
            if (!flagPosition) {
                initializeCoordinates();
                report.write("delivery failed, drone out of coverage area, restarting position\n");
                i = path.length() - 1;

            }
        }
        if (flagPosition) {
            report.write("(" + xPosition + ", " + yPosition + ") direction " + direction.getDescription() + "\n");
        }
    }

    private boolean executeMovementNorth(char instruction) {
        if (instruction == 'A') {
            yPosition = yPosition + 1;
        }
        if (instruction == 'I') {
            direction = Cardinals.W;
        }
        if (instruction == 'D') {
            direction = Cardinals.E;
        }
        return verifyPosition();
    }

    private boolean executeMovementSouth(char instruction) {
        if (instruction == 'A') {
            yPosition = yPosition - 1;
        }
        if (instruction == 'I') {
            direction = Cardinals.E;
        }
        if (instruction == 'D') {
            direction = Cardinals.W;
        }
        return verifyPosition();
    }

    private boolean executeMovementEast(char instruction) {
        if (instruction == 'A') {
            xPosition = xPosition + 1;
        }
        if (instruction == 'I') {
            direction = Cardinals.N;
        }
        if (instruction == 'D') {
            direction = Cardinals.S;
        }
        return verifyPosition();
    }

    private boolean executeMovementWest(char instruction) {
        if (instruction == 'A') {
            xPosition = xPosition - 1;
        }
        if (instruction == 'I') {
            direction = Cardinals.S;
        }
        if (instruction == 'D') {
            direction = Cardinals.N;
        }
        return verifyPosition();
    }

    private boolean verifyPosition() {
        return Math.abs(xPosition) <= 10 && Math.abs(yPosition) <= 10;
    }

    public void initializeCoordinates() {
        this.xPosition = 0;
        this.yPosition = 0;
        this.direction = Cardinals.N;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Cardinals getDirection() {
        return direction;
    }

    public void setDirection(Cardinals direction) {
        this.direction = direction;
    }
}
