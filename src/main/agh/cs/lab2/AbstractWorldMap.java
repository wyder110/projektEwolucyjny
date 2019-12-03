package agh.cs.lab2;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{


    public Map<Vector2d,Car> cars = new HashMap<>();


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Car searchedObject = cars.get(position);
        if(searchedObject == null) return null;
        return searchedObject;
    }
    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }

    @Override
    public boolean place(Car car) {
        if(!canMoveTo(car.getPosition())) {
            throw new IllegalArgumentException("Place: " + car.getPosition().toString() + " is occupied");
        }

        cars.put(car.getPosition(),car);
        car.addObserver(this);
        //listCars.add(car);
        return true;
    }


    @Override
    public void run(MoveDirection[] directions) {
        Car[] cars = this.cars.values().toArray(new Car[this.cars.values().size()]);
        for(int i = 0; i < directions.length; i++) {
//            System.out.println(cars[i%cars.length].getPosition().toString() + " move in direction: " + directions[i].toString());
//            this.cars.remove(cars[i%cars.length].getPosition());
            cars[i%cars.length].move(directions[i]);
//            System.out.println(cars[i%cars.length].getPosition().toString() + " moved in: " + directions[i].toString());
//            this.cars.put(cars[i%cars.length].getPosition(),cars[i%cars.length]);
        }

    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        cars.put(newPosition, cars.remove(oldPosition));
    }
}
