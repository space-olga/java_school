package ru.sbt.test.refactoring;

import junit.framework.TestCase;
import ru.sbt.test.refactoring.Tractor;
import ru.sbt.test.refactoring.TractorInDitchException;

public class TractorTest extends TestCase {
    public void testShouldMoveForward(){
        Tractor tractor = new Tractor();
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(1, tractor.getPositionY());
    }

    public void testShouldTurn(){
        Tractor tractor = new Tractor();
        tractor.move("T");
        assertEquals("EAST", tractor.getOrientation().getType());
        tractor.move("T");
        assertEquals("SOUTH", tractor.getOrientation().getType());
        tractor.move("T");
        assertEquals("WEST", tractor.getOrientation().getType());
        tractor.move("T");
        assertEquals("NORTH", tractor.getOrientation().getType());
    }

/*	public void testShouldTurn(){
		ru.sbt.test.refactoring.Tractor tractor = new ru.sbt.test.refactoring.Tractor();
		tractor.move("T");
		assertEquals(ru.sbt.test.refactoring.Orientation.EAST, tractor.getOrientation());
		tractor.move("T");
		assertEquals(ru.sbt.test.refactoring.Orientation.SOUTH, tractor.getOrientation());
		tractor.move("T");
		assertEquals(ru.sbt.test.refactoring.Orientation.WEST, tractor.getOrientation());
		tractor.move("T");
		assertEquals(ru.sbt.test.refactoring.Orientation.NORTH, tractor.getOrientation());
	}*/

    public void testShouldTurnAndMoveInTheRightDirection(){
        Tractor tractor = new Tractor();
        tractor.move("T");
        tractor.move("F");
        assertEquals(1, tractor.getPositionX());
        assertEquals(0, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(1, tractor.getPositionX());
        assertEquals(-1, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(-1, tractor.getPositionY());
        tractor.move("T");
        tractor.move("F");
        assertEquals(0, tractor.getPositionX());
        assertEquals(0, tractor.getPositionY());
    }

    public void testShouldThrowExceptionIfFallsOffPlateau(){
        Tractor tractor = new Tractor();
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        try{
            tractor.move("F");
            fail("ru.sbt.test.refactoring.Tractor was expected to fall off the plateau");
        }catch(TractorInDitchException expected){
        }
    }
}