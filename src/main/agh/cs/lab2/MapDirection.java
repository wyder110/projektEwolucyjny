package agh.cs.lab2;

import java.util.Random;

public enum MapDirection {
    NORTH(0),
    NORTHEAST(1),
    EAST(2),
    SOUTHEAST(3),
    SOUTH(4),
    SOUTHWEST(5),
    WEST(6),
    NORTHWEST(7);

    public int value;
    MapDirection(int value) {
        this.value = value;
    }

    public String toString(){
        String newStr = "Połudie";
        if (this.equals(MapDirection.EAST)) newStr = "Wschód";
        else if (this.equals(MapDirection.WEST)) newStr = "Zachód";
        else if (this.equals(MapDirection.NORTH)) newStr = "Północ";
        else if (this.equals(MapDirection.NORTHEAST)) newStr = "Północny-wschód";
        else if (this.equals(MapDirection.NORTHWEST)) newStr = "Północny-zachód";
        else if (this.equals(MapDirection.SOUTHEAST)) newStr = "Południowy-wchód";
        else if (this.equals(MapDirection.SOUTHWEST)) newStr = "Południowy-zachód";


        return newStr;
    }
    public MapDirection next(){
        if(this.equals(NORTH)) return MapDirection.NORTHEAST;
        if(this.equals(NORTHEAST)) return MapDirection.EAST;
        if(this.equals(EAST)) return MapDirection.SOUTHEAST;
        if(this.equals(SOUTHEAST)) return MapDirection.SOUTH;
        if(this.equals(SOUTH)) return MapDirection.SOUTHWEST;
        if(this.equals(SOUTHWEST)) return MapDirection.WEST;
        if(this.equals(WEST)) return MapDirection.NORTHWEST;
        return MapDirection.NORTH;
    }
    public MapDirection previous(){
        if(this.equals(NORTH)) return MapDirection.NORTHWEST;
        if(this.equals(NORTHWEST)) return MapDirection.WEST;
        if(this.equals(WEST)) return MapDirection.SOUTHWEST;
        if(this.equals(SOUTHWEST)) return MapDirection.SOUTH;
        if(this.equals(SOUTH)) return MapDirection.SOUTHEAST;
        if(this.equals(SOUTHEAST)) return MapDirection.EAST;
        if(this.equals(EAST)) return MapDirection.NORTHEAST;
        return MapDirection.NORTH;
    }
    public Vector2d toUnitVector(){
        int x = 0, y = 0;
        if(this.equals(MapDirection.NORTH) || this.equals(MapDirection.NORTHEAST) || this.equals(MapDirection.NORTHWEST)) y = 1;
        if(this.equals(MapDirection.SOUTH) || this.equals(MapDirection.SOUTHEAST) || this.equals(MapDirection.SOUTHWEST)) y = -1;
        if(this.equals(MapDirection.NORTHWEST) || this.equals(MapDirection.WEST) || this.equals(MapDirection.SOUTHWEST)) x = -1;
        if(this.equals(MapDirection.EAST) || this.equals(MapDirection.NORTHEAST) || this.equals(MapDirection.SOUTHEAST)) x = 1;
        return new Vector2d(x,y);
    }
    public static MapDirection randomMapDirection(){
        int rand = new Random().nextInt(8);
        return MapDirection.values()[rand];
    }
}
