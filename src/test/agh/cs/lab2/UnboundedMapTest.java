package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UnboundedMapTest {
//    listHays.add(new HayStack(new Vector2d(-4,-4)));
//        listHays.add(new HayStack(new Vector2d(7,7)));
//        listHays.add(new HayStack(new Vector2d(3,6)));
//        listHays.add(new HayStack(new Vector2d(2,0)));

    private UnboundedMap map;
    @Before
    public void initialize(){
        ArrayList<HayStack> xd = new ArrayList<>();
        xd.add(new HayStack(new Vector2d(7,7)));
        xd.add(new HayStack(new Vector2d(-4,-4)));
        xd.add(new HayStack(new Vector2d(3,6)));
        xd.add(new HayStack(new Vector2d(2,0)));

        map = new UnboundedMap(xd);

        map.place(new Car(map,3,4));
    }
    @Test
    public void canMoveToTest(){
        Assert.assertTrue(map.canMoveTo(new Vector2d(2,2)));
        Assert.assertFalse(map.canMoveTo(new Vector2d(-4,-4)));

    }
    @Test
    public void isOccupiedTest(){
        Assert.assertFalse(map.isOccupied(new Vector2d(2,2)));
        Assert.assertTrue(map.isOccupied(new Vector2d(-4,-4)));
    }
    @Test
    public void objectAtTest(){
        Assert.assertTrue(map.objectAt(new Vector2d(3,4)) instanceof Car);
        Assert.assertTrue(map.objectAt(new Vector2d(7,7)) instanceof HayStack);
        //Assert.assertEquals(map.objectAt(new Vector2d(7,7)), new HayStack(new Vector2d(7,7)));

    }
    @Test
    public void runTest(){
        System.out.println(map.toString());
        String args[] = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        map.run(directions);
        System.out.println(map.toString());

    }
}
