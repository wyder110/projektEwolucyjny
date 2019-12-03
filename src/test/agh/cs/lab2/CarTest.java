//package agh.cs.lab2;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//public class CarTest {
//    @Test
//    public void toStringTest(){
//        Car newCar = new Car();
//        Assert.assertEquals(newCar.toString(),"(2,2) in: Północ"  );
//    }
//    @Test
//    public void moveTest(){
//        Car newCar = new Car();
//        newCar.move(MoveDirection.FORWARD);
//        Assert.assertEquals(newCar.toString(),"(2,3) in: Północ");
//
//
//        newCar.move(MoveDirection.RIGHT);
//        Assert.assertEquals(newCar.toString(),"(2,3) in: Wschód");
//
//
//        newCar.move(MoveDirection.BACKWARD);
//        Assert.assertEquals(newCar.toString(),"(1,3) in: Wschód");
//
//
//        newCar.move(MoveDirection.LEFT);
//        Assert.assertEquals(newCar.toString(),"(1,3) in: Północ");
//
//
//        newCar.move(MoveDirection.BACKWARD);
//        Assert.assertEquals(newCar.toString(),"(1,2) in: Północ");
//
//        newCar.move(MoveDirection.BACKWARD);
//        Assert.assertEquals(newCar.toString(),"(1,1) in: Północ");
//
//        newCar.move(MoveDirection.BACKWARD);
//        Assert.assertEquals(newCar.toString(),"(1,0) in: Północ");
//
//        newCar.move(MoveDirection.BACKWARD);
//        Assert.assertEquals(newCar.toString(),"(1,0) in: Północ");
//
//
//        newCar.move(MoveDirection.BACKWARD);
//        Assert.assertEquals(newCar.toString(),"(1,0) in: Północ");
//
//
//        newCar.move(MoveDirection.LEFT);
//        Assert.assertEquals(newCar.toString(),"(1,0) in: Zachód");
//
//    }
//}
