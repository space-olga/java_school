package ru.sbt.test.refactoring;

public class NorthOrientation extends Orientation {
    public NorthOrientation(String type) {
        super(type);
    }

    @Override
    public int[] moveForward(int[] position) {
        return new int[]{position[0], position[1] + 1};
    }

    @Override
    public Orientation turnClockwise() {
        return orientationFactory.createOrientation("EAST");
    }
}
