package ru.sbt.test.refactoring;

public class EastOrientation extends Orientation {
    public EastOrientation(String type) {
        super(type);
    }

    @Override
    public int[] moveForward(int[] position) {
        return new int[] { position[0] + 1, position[1] };
    }

    @Override
    public Orientation turnClockwise() {
        return orientationFactory.createOrientation("SOUTH");
    }
}
