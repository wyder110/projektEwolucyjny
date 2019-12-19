package agh.cs.lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {

    public RectangularMap map;
    public Constants constants;
    private PositionFrame positionFrame;
    public boolean isRunning;
    public List<SimulationInformations> simulationInformations;

    public Simulation() throws InterruptedException {
        constants = new Constants();

        map = new RectangularMap(this, constants.width, constants.height);
        positionFrame = new PositionFrame("Symulator", this, 900, 900);


        try {
            placeAdamAndEve(constants.startingAnimalsCount);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        simulationInformations = new ArrayList<SimulationInformations>();
        simulationInformations.add(new SimulationInformations(this));
        positionFrame.start();
        startRunning();
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
                    simulationInformations.add(new SimulationInformations(this));
                    positionFrame.drawNew();
                    Thread.sleep(50);
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


    private void placeAdamAndEve(int numberOfObjects) throws Exception {
        List<Vector2d> foundPlaces = new ArrayList<Vector2d>();
        if(constants.width*constants.height < numberOfObjects) throw new Exception("przy tych parametrów ilość początkowych zwierząt musi być <"+constants.width*constants.height);

        while(foundPlaces.size() < numberOfObjects){
            Vector2d randomVec = Vector2d.createRandomVector(this.map.upperRight.x, this.map.upperRight.y);
            if(!foundPlaces.contains(randomVec)) foundPlaces.add(randomVec);
        }

        for(Vector2d place : foundPlaces){
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
