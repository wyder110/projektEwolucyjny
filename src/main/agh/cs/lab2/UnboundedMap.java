package agh.cs.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnboundedMap extends AbstractWorldMap implements IWorldMap {

    //private ArrayList<HayStack> listHays;
    private Map<Vector2d,HayStack> hays = new HashMap<>();
    private MapBoundary mapBoundary;

    public UnboundedMap(ArrayList<HayStack> hays){
        mapBoundary = new MapBoundary(this);
        for(int i = 0; i < hays.size(); i++){
            if(this.hays.get(hays.get(i).getPosition()) != null) throw new IllegalArgumentException("Place: " + hays.get(i).getPosition().toString() + " is occupied");

            this.hays.put(hays.get(i).getPosition(), hays.get(i));
            mapBoundary.elementAdded(hays.get(i));
        }
    }

    @Override
    public boolean place(Car car) {
        if(!super.place(car)) return false;
        mapBoundary.elementAdded(car);
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        HayStack searchedObject = hays.get(position);
        if(searchedObject != null) return searchedObject;
        return super.objectAt(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        super.positionChanged(oldPosition, newPosition);
        mapBoundary.positionChanged(oldPosition, newPosition);

    }
    public Vector2d getLowerLeft(){
        return mapBoundary.getLowerBoundary();
    }
    public Vector2d getUpperRight(){
        return mapBoundary.getUpperBoundary();
    }
}
