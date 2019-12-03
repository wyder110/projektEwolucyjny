package agh.cs.lab2;

public class OptionsParser {
    static public MoveDirection[] parse(String[] args){
        int dirAmount = 0;
        for(int i = 0; i < args.length; i++){

            args[i] = args[i].toLowerCase();

            if(args[i].equals("f") || args[i].equals("forward") || args[i].equals("b") || args[i].equals("backward") || args[i].equals("l") || args[i].equals("left") || args[i].equals("r") || args[i].equals("right"))
                dirAmount++;
            else throw new IllegalArgumentException(args[i] + " is not legal move specification");
        }
        MoveDirection parsed[] = new MoveDirection [dirAmount];
        for(int i = 0; i < dirAmount; i++){

            if(args[i].equals("f") || args[i].equals("forward")) parsed[i] = MoveDirection.FORWARD;
            else if(args[i].equals("b") || args[i].equals("backward")) parsed[i] = MoveDirection.BACKWARD;
            else if(args[i].equals("l") || args[i].equals("left")) parsed[i] = MoveDirection.LEFT;
            else if(args[i].equals("r") || args[i].equals("right")) parsed[i] = MoveDirection.RIGHT;
        }
        return parsed;
    }
}
