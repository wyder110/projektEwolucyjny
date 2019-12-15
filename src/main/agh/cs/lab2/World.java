package agh.cs.lab2;

public class World {

    public static void main(String[] args){

        try{

            Simulation simulation = new Simulation();
        } catch (IllegalArgumentException | InterruptedException ex){

            System.out.println(ex.toString());
        }
    }
}

