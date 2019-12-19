package agh.cs.lab2;

import java.util.Iterator;

public class SimulationInformations {
    public String maxGene = "";
    public String averageEnergy = "";
    public String averageChildren = "";
    public String averageAge = "";
    public String allAnimals = "";
    public String allHays = "";
    public String era = "";

    public SimulationInformations(Simulation simulation){
        Car maxCar = null;
        int averageEnergy = 0;
        int averageChildren = 0;
        int averageAge = 0;

        for (Iterator<Car> iterator = simulation.map.carList.iterator(); iterator.hasNext();) {
            Car currentCar = iterator.next();
            if(maxCar == null || maxCar.energy < currentCar.energy) maxCar = currentCar;
            averageEnergy += currentCar.energy;
            averageChildren += currentCar.kidList.size();
            averageAge += currentCar.age;
        }
        if(maxCar == null){
            this.averageEnergy = "0";
            this.averageChildren = "0";
            this.averageAge = "0";
            this.maxGene = "";
        }
        else{
            this.averageEnergy = String.format("%.02f", (float) averageEnergy/(float) simulation.map.carList.size());
            this.averageChildren = String.format("%.02f", (float) averageChildren/(float) simulation.map.carList.size());
            this.averageAge = String.format("%.02f", (float) averageAge/(float) simulation.map.carList.size());
            this.maxGene = maxCar.genes.toString();
        }

        era = Integer.toString(simulation.map.era);
        allAnimals = Integer.toString(simulation.map.carList.size());
        allHays = Integer.toString(simulation.map.hays.size());

    }
}
