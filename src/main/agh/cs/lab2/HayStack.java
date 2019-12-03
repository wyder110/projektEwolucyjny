package agh.cs.lab2;

public class HayStack implements IMapElement{
    private Vector2d position;
    public HayStack(Vector2d position) {
        this.position = position;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public String toString(){
        return "s";
    }
}
