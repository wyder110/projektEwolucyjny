package agh.cs.lab2;

import java.util.Comparator;

public class Vector2dYcomparator implements Comparator {

    @Override
    public int compare(Object o, Object t) {
        if(o instanceof Vector2d && t instanceof Vector2d){
            Vector2d o1 = (Vector2d) o;
            Vector2d t1 = (Vector2d) t;
            if((o1.y-t1.y) != 0) return (o1.y-t1.y);
            return o1.x-t1.x;

        }
        return 0;
    }

}
