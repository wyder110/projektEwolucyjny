package agh.cs.lab2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;

public class Simulation {

    RectangularMap map;

    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public Double jugnleRatio;
    public int energyToBreed;
    public int width;
    public int height;
    private PositionFrame positionFrame;
    private int startingAnimalsCount;
    public boolean isRunning;

    public String maxGene = "";
    public String averageEnergy = "";
    public String averageChildren = "";
    public String averageAge = "";

    public Simulation() throws InterruptedException {
        setConstants();
        map = new RectangularMap(this, width, height);
        placeAdamAndEve(startingAnimalsCount);

        positionFrame.start();
        startRunning();
    }

    private void setConstants(){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("parameters.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            width = Math.toIntExact((long) jsonObject.get("width"));
            height = Math.toIntExact((long) jsonObject.get("height"));
            startEnergy = Math.toIntExact((long) jsonObject.get("startEnergy"));
            moveEnergy = Math.toIntExact((long) jsonObject.get("moveEnergy"));
            plantEnergy = Math.toIntExact((long) jsonObject.get("plantEnergy"));
            jugnleRatio = (Double) jsonObject.get("jugnleRatio");
            energyToBreed = Math.toIntExact((long) jsonObject.get("energyToBreed"));
            positionFrame = new PositionFrame("Symulator", this, 800, 800);
            startingAnimalsCount = Math.toIntExact((long) jsonObject.get("startingAnimalsCount"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void run() throws InterruptedException {
        Thread runThread = new Thread(() -> {
            try {
                while(isRunning){
                    map.era++;
                    map.moveObjects();
                    map.growHays();
                    map.eat();
                    map.breed();
                    map.deleteDeadAnimals();
                    map.calculateInformations();
                    positionFrame.drawNew();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        runThread.start();
    }

    public synchronized void startRunning() throws InterruptedException {
        System.out.println("START");
        isRunning = true;
        run();
    }
    public synchronized void stopRunning() throws InterruptedException {
        System.out.println("STOP");
        isRunning = false;
    }


    private void placeAdamAndEve(int numberOfObjects){
        for(int i = 0; i < numberOfObjects; i++){
            Vector2d place = Vector2d.createRandomVector(this.map.upperRight.x, this.map.upperRight.y);
            map.place(new Car(map, place, Genes.randomGenes()), true);

        }
    }

    @Override
    public String toString() {
        String str = "Liczba żywych zwieraąt: " + map.carList.size();
        Collections.sort(map.carList, Collections.reverseOrder());
        for(int i = 0; i < map.carList.size(); i++){
            str += "\nEnergia " + map.carList.get(i).energy + " geny " + map.carList.get(i).genes+" pozycja "+map.carList.get(i).getPosition();
        }
        return str;
    }
}
