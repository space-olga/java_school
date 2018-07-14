package ru.sbt.test.refactoring;

public class SouthOrientation extends Orientation {
    public SouthOrientation(String type) {
        super(type);
    }

    public int[] moveForward(int[] position) {
        return new int[] { position[0], position[1] - 1 };
    }

    public Orientation turnClockwise() {
        return orientationFactory.createOrientation("WEST");
    }
}
