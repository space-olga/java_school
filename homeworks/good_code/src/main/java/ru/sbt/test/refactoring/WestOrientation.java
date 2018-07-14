package ru.sbt.test.refactoring;

public class WestOrientation extends Orientation {
    public WestOrientation(String type) {
        super(type);
    }

    public int[] moveForward(int[] position) {
        return new int[] { position[0] - 1, position[1] };
    }

    public Orientation turnClockwise() {
        return orientationFactory.createOrientation("NORTH");
    }
}
