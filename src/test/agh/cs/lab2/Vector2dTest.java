package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        Vector2d testVector1 = new Vector2d(1,1);
        Vector2d testVector2 = new Vector2d(1,1);
        Vector2d testVector3 = new Vector2d(2,1);
        Vector2d testVector4 = new Vector2d(2,2);

        assertEquals(testVector1, testVector2);
        assertNotEquals(testVector1, testVector3);
        assertNotEquals(testVector1, testVector4);
    }
    @Test
    public void toStringTest(){
        Vector2d testVector1 = new Vector2d(1,1);

        Assert.assertEquals(testVector1.toString(), "(1,1)");

    }
    @Test
    public void testPrecedes(){
        Vector2d testVector1 = new Vector2d(1,1);
        Vector2d testVector2 = new Vector2d(1,1);
        Vector2d testVector3 = new Vector2d(2,1);
        Vector2d testVector4 = new Vector2d(2,3);

        Assert.assertTrue(testVector2.precedes(testVector1));
        Assert.assertFalse(testVector4.precedes(testVector3));

        Assert.assertTrue(testVector1.precedes(testVector3));
        Assert.assertTrue(testVector1.precedes(testVector4));


    }
    @Test
    public void testFollows() {
        Vector2d testVector1 = new Vector2d(1, 1);
        Vector2d testVector2 = new Vector2d(1, 1);
        Vector2d testVector3 = new Vector2d(2, 1);
        Vector2d testVector4 = new Vector2d(2, 3);

        Assert.assertTrue(testVector2.follows(testVector1));
        Assert.assertTrue(testVector4.follows(testVector3));

        Assert.assertFalse(testVector1.follows(testVector3));
        Assert.assertFalse(testVector1.follows(testVector4));


    }
    @Test
    public void testAdd(){
        Vector2d testVector1 = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(2,1);
        Vector2d testVector3 = new Vector2d(3,3);

        Assert.assertEquals(testVector1.add(testVector2), testVector3);
    }

    @Test
    public void testSubtract(){
        Vector2d testVector1 = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(2,1);
        Vector2d testVector3 = new Vector2d(-1,1);

        Assert.assertEquals(testVector1.subtract(testVector2), testVector3);
    }

    @Test
    public void testLowerLeft(){
        Vector2d testVector1 = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(2,1);
        Vector2d testVector3 = new Vector2d(1,1);

        Assert.assertEquals(testVector1.lowerLeft(testVector2), testVector3);
    }

    @Test
    public void testUpperRight(){
        Vector2d testVector1 = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(2,1);
        Vector2d testVector3 = new Vector2d(2,2);

        Assert.assertEquals(testVector1.upperRight(testVector2), testVector3);
    }
    @Test
    public void testOpposite(){
        Vector2d testVector1 = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(-1,-2);

        Assert.assertEquals(testVector1.opposite(), testVector2);
    }
}
