package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

public class MapDirectionTest {
    @Test
    public void testNext() {
        Assert.assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        Assert.assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        Assert.assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        Assert.assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);

    }
    @Test
    public void testPrevious() {
        Assert.assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        Assert.assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
        Assert.assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        Assert.assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
    }

}
