package agh.cs.lab2;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class OptionsParserTest {
    @Test
    public void parserTest(){
        String [] args = {"f", "forward", "b", "backward", "r", "right", "l", "left", "aaa", "fdsfsd", "fdsfs"};
        MoveDirection [] finall = OptionsParser.parse(args);
        MoveDirection [] shoudBe = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        assertEquals(shoudBe.length, finall.length);
        for(int i = 0; i < finall.length; i++) assertEquals(finall[i], shoudBe[i]);

    }
}
