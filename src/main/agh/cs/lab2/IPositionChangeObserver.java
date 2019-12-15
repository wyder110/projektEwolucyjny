package agh.cs.lab2;

public interface IPositionChangeObserver {
    void positionChanged(Car car, Vector2d oldPosition, Vector2d newPosition);
}
