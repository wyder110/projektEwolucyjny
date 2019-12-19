package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public class Car implements IMapElement, Comparable<Car> {

    RectangularMap map;

    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position;
    private MapDirection direction;
    public Genes genes;
    public int energy;
    public int age = 0;

    public int eraBorn = 0;
    public int eraDeath = -1;

    public List<Car> kidList;

    public Car(RectangularMap map, Vector2d position, Genes genes) {
        this.map = map;
        this.position = position;
        this.genes = genes;
        this.energy = map.simulation.constants.startEnergy;
        this.direction = MapDirection.NORTH;

        kidList = new ArrayList<Car>();
    }


    public void move() {
        energy -= map.simulation.constants.moveEnergy;
        int turn = genes.takeRand();

        for(int i = 0; i < turn; i++)
            this.direction = this.direction.next();

        Vector2d newPosition = direction.toUnitVector().add(position);
        newPosition = newPosition.returnNotOutOfBounds(map.upperRight);
        Vector2d oldPosition = this.position;
        position = newPosition;
        positionChanged(oldPosition);
    }

    @Override
    public String toString() {
        String ret = "";
        if (direction == MapDirection.NORTH) ret = "N";
        else if (direction == MapDirection.WEST) ret = "W";
        else if (direction == MapDirection.EAST) ret = "E";
        else if (direction == MapDirection.SOUTH) ret = "S";
        else if (direction == MapDirection.NORTHEAST) ret = "NE";
        else if (direction == MapDirection.NORTHWEST) ret = "NW";
        else if (direction == MapDirection.SOUTHEAST) ret = "SE";
        else if (direction == MapDirection.SOUTHWEST) ret = "SW";
        return ret;
    }

    public Vector2d getPosition() {
        return new Vector2d(position.x, position.y);
    }

    public Car createBaby(Car parent) {
        Car baby =  new Car(map, Vector2d.createRandomVector(map.upperRight.x, map.upperRight.y) ,genes.createNewGenes(parent.genes));
        baby.energy = this.energy/4 + parent.energy/4;
        this.energy -= this.energy/4;
        parent.energy -= parent.energy/4;
        return baby;
    }

    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition) {
//        System.out.println(observers.size());
//        for (IPositionChangeObserver observer : observers) {
//            observer.positionChanged(this, oldPosition, this.position);
//        }
        observers.get(0).positionChanged(this, oldPosition, this.position);
//        for (Iterator<IPositionChangeObserver> iterator = observers.iterator(); iterator.hasNext();) {
//            IPositionChangeObserver next = iterator.next();
//            next.positionChanged(this, oldPosition, this.position);
//        }
    }

    public int kidAmountInEra(int era){
        int count = 0;
        for(Car i : kidList){
            if(i.eraBorn <= era) count++;
        }
        return count;
    }



    @Override
    public int compareTo(Car car) {
        return this.energy - car.energy;
    }


}
