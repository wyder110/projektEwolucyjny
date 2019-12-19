package agh.cs.lab2;

import java.util.Random;

public class Vector2d {
    final public int x;
    final public int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return("("+x+","+y+")");
    }
    boolean precedes(Vector2d other){
        return this.x<=other.x && this.y <= other.y;
    }
    boolean follows(Vector2d other){
        return this.x>=other.x && this.y >= other.y;
    }
    public Vector2d upperRight(Vector2d other){
        int newX = this.x;
        int newY = this.y;
        if(other.x>newX) newX = other.x;
        if(other.y>newY) newY = other.y;
        return new Vector2d(newX, newY);
    }
    public Vector2d lowerLeft(Vector2d other){
        int newX = this.x;
        int newY = this.y;
        if(other.x<newX) newX = other.x;
        if(other.y<newY) newY = other.y;
        return new Vector2d(newX, newY);
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x, this.y+other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x, this.y-other.y);
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return that.x == this.x && that.y == this.y;
    }
    public Vector2d opposite(){
        return new Vector2d(this.x*-1, this.y*-1);
    }

    public static Vector2d createRandomVector(int width, int heigth){
        return new Vector2d(new Random().nextInt(width), new Random().nextInt(heigth));
    }
    public static Vector2d createRandomVectorOutsideJungle(Vector2d start, Vector2d end, Vector2d startJungle, Vector2d endJungle){
        while(true){
            Vector2d pom = new Vector2d(new Random().nextInt(end.x-start.x)+start.x, new Random().nextInt(end.y-start.y)+start.y);
            if(!(pom.follows(startJungle) && pom.precedes(endJungle))) return pom;
        }
    }
    public Vector2d returnNotOutOfBounds(Vector2d upperRight){
        int xx = this.x;
        int yy = this.y;
        if(xx >= upperRight.x) xx -= upperRight.x;
        if(xx < 0) xx += upperRight.x;
        if(y >= upperRight.y) yy -= upperRight.y;
        if(y < 0) yy += upperRight.y;
        return new Vector2d(xx,yy);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
