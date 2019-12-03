package agh.cs.lab2;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver {

    private SortedSet<Vector2d> xSortedElements;
    private SortedSet<Vector2d> ySortedElements;

    private UnboundedMap map;

    public MapBoundary(UnboundedMap map){

        xSortedElements = new TreeSet<Vector2d>(new Vector2dXcomparator());
        ySortedElements = new TreeSet<Vector2d>(new Vector2dYcomparator());
        this.map = map;

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xSortedElements.remove(oldPosition);
        ySortedElements.remove(oldPosition);

        xSortedElements.add(newPosition);
        ySortedElements.add(newPosition);

    }

    public void elementAdded(IMapElement newElement){
        xSortedElements.add(newElement.getPosition());
        ySortedElements.add(newElement.getPosition());
    }

    public Vector2d getUpperBoundary(){

        Vector2d xLast = (Vector2d) xSortedElements.last();
        Vector2d yLast = (Vector2d) ySortedElements.last();
        return new Vector2d(xLast.x,yLast.y);
    }

    public Vector2d getLowerBoundary(){

        Vector2d xFirst = (Vector2d) xSortedElements.first();
        Vector2d yFirst = (Vector2d) ySortedElements.first();

        return new Vector2d(xFirst.x,yFirst.y);
    }

}
