package agh.cs.lab2;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    final private Vector2d lowerLeft;
    final private Vector2d upperRight;

    public RectangularMap(int width, int height) {
        lowerLeft =  new Vector2d(0,0);
        upperRight = new Vector2d(width,height);

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(getUpperRight().x >= position.x && position.x >= getLowerLeft().x){
            if(getUpperRight().y >= position.y && position.y >= getLowerLeft().y){
                return super.canMoveTo(position);
            }
        }
        return false;
    }
    public Vector2d getLowerLeft(){
        return lowerLeft;
    }
    public Vector2d getUpperRight(){
        return upperRight;
    }

}
