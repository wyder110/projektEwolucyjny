package agh.cs.lab2;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    public String toString(){
        String newStr = "Połudie";
        if (this.equals(MapDirection.EAST)) newStr = "Wschód";
        else if (this.equals(MapDirection.WEST)) newStr = "Zachód";
        else if (this.equals(MapDirection.NORTH)) newStr = "Północ";
        return newStr;
    }
    public MapDirection next(){
        if(this.equals(NORTH)) return MapDirection.EAST;
        if(this.equals(WEST)) return MapDirection.NORTH;
        if(this.equals(SOUTH)) return MapDirection.WEST;
        return MapDirection.SOUTH;
    }
    public MapDirection previous(){
        if(this.equals(NORTH)) return MapDirection.WEST;
        if(this.equals(WEST)) return MapDirection.SOUTH;
        if(this.equals(SOUTH)) return MapDirection.EAST;
        return MapDirection.NORTH;
    }
    public Vector2d toUnitVector(){
        int x = 0, y = 0;
        if(this.equals(MapDirection.NORTH)) y = 1;
        if(this.equals(MapDirection.SOUTH)) y = -1;
        if(this.equals(MapDirection.EAST)) x = 1;
        if(this.equals(MapDirection.WEST)) x = -1;
        return new Vector2d(x,y);
    }
}
