package agh.cs.lab2;

import java.util.ArrayList;

public class World {

    public static void main(String[] args){
        try{
            ArrayList<HayStack> listHays = new ArrayList<HayStack>();
            listHays.add(new HayStack(new Vector2d(-4,-4)));
            listHays.add(new HayStack(new Vector2d(7,7)));
            listHays.add(new HayStack(new Vector2d(6,7)));
            listHays.add(new HayStack(new Vector2d(2,0)));

            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new UnboundedMap(listHays);


            map.place(new Car(map,2,2));
            map.place(new Car(map,3,3));

            System.out.println(map.toString());

            map.run(directions);
            System.out.println(map.toString());
        } catch (IllegalArgumentException ex){

            System.out.println(ex.toString());
        }
    }
}

