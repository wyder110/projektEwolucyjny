package agh.cs.lab2;

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

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }
}
