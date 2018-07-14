package ru.sbt.test.refactoring;

public abstract class Orientation {
    private final String orientationType;
    OrientationFactory orientationFactory;

    protected Orientation(String orientationType) {
        this.orientationType = orientationType;
        this.orientationFactory = new OrientationFactory();
    }

    public String getType() {
        return orientationType;
    }

    public abstract int[] moveForward(int[] position);
    public abstract Orientation turnClockwise();
}


// public enum ru.sbt.test.refactoring.Orientation {

//	NORTH, WEST, SOUTH, EAST;

// }

