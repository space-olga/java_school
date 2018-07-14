package ru.sbt.test.refactoring;

import ru.sbt.test.refactoring.Orientation;
import ru.sbt.test.refactoring.OrientationFactory;

public class Tractor {
    int[] position, field;
    Orientation orientation;

    //** int[] position = new int[] { 0, 0 };
    //** int[] field = new int[] { 5, 5 };
    //** ru.sbt.test.refactoring.Orientation orientation = ru.sbt.test.refactoring.Orientation.NORTH;

    public Tractor()
    {
        this.position = new int[] { 0, 0 };
        this.field = new int[] { 5, 5 };
        this.orientation = (new OrientationFactory()).createOrientation("NORTH");
    }

    public void move(String command) {
        switch (command) {
            case "F":
                position = this.orientation.moveForward(this.position);
                if (position[0] > field[0] || position[1] > field[1]) {
                    throw new TractorInDitchException();
                }
                break;
            case "T":
                this.orientation = this.orientation.turnClockwise();
                break;
        }

       /* if (command == "F") {
			//** moveForwards();
			position = this.orientation.moveForward(this.position);

			if (position[0] > field[0] || position[1] > field[1]) {
				throw new ru.sbt.test.refactoring.TractorInDitchException();
			}
		} else if (command == "T") {
        	this.orientation.turnClockwise();
		}*/
    }

    /** public void moveForwards() {
     if (orientation == ru.sbt.test.refactoring.Orientation.NORTH) {
     position = new int[] { position[0], position[1] + 1 };
     } else if (orientation == ru.sbt.test.refactoring.Orientation.EAST) {
     position = new int[] { position[0] + 1, position[1] };
     } else if (orientation == ru.sbt.test.refactoring.Orientation.SOUTH) {
     position = new int[] { position[0], position[1] - 1 };
     } else if (orientation == ru.sbt.test.refactoring.Orientation.WEST) {
     position = new int[] { position[0] - 1, position[1] };
     }
     if (position[0] > field[0] || position[1] > field[1]) {
     throw new ru.sbt.test.refactoring.TractorInDitchException();
     }
     }*/

   /* public void turnClockwise() {
		if (orientation == ru.sbt.test.refactoring.Orientation.NORTH) {
			orientation = ru.sbt.test.refactoring.Orientation.EAST;
		} else if (orientation == ru.sbt.test.refactoring.Orientation.EAST) {
			orientation = ru.sbt.test.refactoring.Orientation.SOUTH;
		} else if (orientation == ru.sbt.test.refactoring.Orientation.SOUTH) {
			orientation = ru.sbt.test.refactoring.Orientation.WEST;
		} else if (orientation == ru.sbt.test.refactoring.Orientation.WEST) {
			orientation = ru.sbt.test.refactoring.Orientation.NORTH;
		}
	}*/

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public Orientation getOrientation() {
        return orientation;
    }
}