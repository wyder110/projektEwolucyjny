package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public class Car implements IMapElement{

    IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;

    public Car(IWorldMap map) {
        this.map = map;
    }

    public Car(IWorldMap map, Vector2d position) {
        this.map = map;
        this.position = position;
    }

    public Car(IWorldMap map, int x, int y) {
        this.map = map;
        this.position = new Vector2d(x, y);
    }


    public String toString() {

        String ret = "";
        if (direction == MapDirection.NORTH) ret = "N";
        else if (direction == MapDirection.WEST) ret = "W";
        else if (direction == MapDirection.EAST) ret = "E";
        else if (direction == MapDirection.SOUTH) ret = "S";
        return ret;
    }

    public void move(MoveDirection turn) {
        if (turn == MoveDirection.RIGHT)
            this.direction = this.direction.next();
        else if (turn == MoveDirection.LEFT)
            this.direction = this.direction.previous();
        else {
            Vector2d newPosition = direction.toUnitVector();
            if (turn == MoveDirection.FORWARD) {
                newPosition = position.add(newPosition);
            } else if (turn == MoveDirection.BACKWARD) {
                newPosition = position.subtract(newPosition);
            }
            if (map.canMoveTo(newPosition)) {
                Vector2d oldPosition = this.position;
                position = newPosition;
                positionChanged(oldPosition);

            }
        }
    }

    public Vector2d getPosition() {
        return new Vector2d(position.x, position.y);
    }


    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, this.position);
        }
    }
}
