package ru.sbt.test.refactoring;

import ru.sbt.test.refactoring.AbstractOrientationFactory;

public class OrientationFactory implements AbstractOrientationFactory {
    @Override
    public Orientation createOrientation(String type) throws IllegalArgumentException {
        switch (type) {
            case "NORTH":
                return new NorthOrientation(type);
            case "WEST":
                return new WestOrientation(type);
            case "SOUTH":
                return new SouthOrientation(type);
            case "EAST":
                return new EastOrientation(type);
            default:
                throw new IllegalArgumentException("Invalid orientation type: " + type);
        }
    }
}
