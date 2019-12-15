package agh.cs.lab2;

import java.util.*;

public class RectangularMap implements IPositionChangeObserver {

    final public Vector2d lowerLeft;
    final public Vector2d upperRight;
    Simulation simulation;
    public Map<Vector2d,List<Car>> cars = new HashMap<>();
    public Map<Vector2d,HayStack> hays = new HashMap<>();
    public List<Car> carList = new ArrayList<>();
    public List<Car> deadCarList = new ArrayList<>();

    final public Vector2d lowerLeftJungle;
    final public Vector2d upperRightJungle;

    public int era = 0;

    public RectangularMap(Simulation simulation, int width, int height) {
        lowerLeft =  new Vector2d(0,0);
        upperRight = new Vector2d(width,height);
        this.simulation = simulation;

        int jungleXWidth = (int) (simulation.jugnleRatio*upperRight.x/2);
        int jungleXStart = upperRight.x/2 - jungleXWidth;
        int jungleXEnd = upperRight.x/2 + jungleXWidth;

        int jungleYWidth = (int) (simulation.jugnleRatio*upperRight.y/2);
        int jungleYStart = upperRight.x/2 - jungleYWidth;
        int jungleYEnd = upperRight.x/2 + jungleYWidth;

        lowerLeftJungle = new Vector2d(jungleXStart, jungleYStart);
        upperRightJungle  =new Vector2d(jungleXEnd, jungleYEnd);
    }

    public List<Car> carAt(Vector2d position) {
        List<Car> searchedObject = cars.get(position);
        if(searchedObject == null) return null;
        if(searchedObject.size() == 0){
            cars.remove(position);
            return null;
        }
        return searchedObject;
    }
    public HayStack hayAt(Vector2d position) {
        HayStack searchedObject = hays.get(position);
        if(searchedObject == null) return null;
        return searchedObject;
    }
    public boolean inJungle(Vector2d tested){
        return tested.follows(lowerLeftJungle) && tested.precedes(upperRightJungle);
    }
    public boolean isOccupied(Vector2d position){
        return !(hayAt(position) == null && carAt(position) == null);
    }


    public void positionChanged(Car car, Vector2d oldPosition, Vector2d newPosition) {
        List<Car> list = carAt(oldPosition);

        if(list != null){
            for(int i = 0; i < list.size(); i++){
                if(car == list.get(i)){
                    list.remove(i);
                    break;
                }
            }
        }
        place(car, false);
    }

    public void place(Car car, boolean addToList) {
        if(addToList) {
            carList.add(car);
        }
        List<Car> list = carAt(car.getPosition());

        car.addObserver(this);
        if(list == null){
            List<Car> newList = new ArrayList<Car>();
            newList.add(car);
            cars.put(car.getPosition(), newList);
        }
        else {
            list.add(car);
        }
    }
    private void removeAnimalFromList(Car car){
        List<Car> searchedObject = cars.get(car.getPosition());
        for (Iterator<Car> iterator = searchedObject.iterator(); iterator.hasNext();) {
            Car currentCar = iterator.next();
            if(currentCar == car){
                iterator.remove();
                return;
            }
        }
    }

    public void deleteDeadAnimals() {
        for (Iterator<Car> iterator = carList.iterator(); iterator.hasNext();) {
            Car currentCar = iterator.next();
            if(currentCar.energy <= 0){
                removeAnimalFromList(currentCar);
                deadCarList.add(currentCar);
                currentCar.eraDeath = era;
                iterator.remove();
            }
        }
    }

    public void calculateInformations(){
        Car maxCar = null;
        int averageEnergy = 0;
        int averageChildren = 0;
        int averageAge = 0;

        for (Iterator<Car> iterator = carList.iterator(); iterator.hasNext();) {
            Car currentCar = iterator.next();
            if(maxCar == null || maxCar.energy < currentCar.energy) maxCar = currentCar;
            averageEnergy += currentCar.energy;
            averageChildren += currentCar.kidList.size();
            averageAge += currentCar.age;
        }
        simulation.averageEnergy = String.format("%.02f", (float) averageEnergy/(float) carList.size());
        simulation.averageChildren = String.format("%.02f", (float) averageChildren/(float) carList.size());
        simulation.averageAge = String.format("%.02f", (float) averageAge/(float) carList.size());



        simulation.maxGene = maxCar.genes.toString();

    }

    public void moveObjects(){
        for (Iterator<Car> iterator = carList.iterator(); iterator.hasNext();) {
            Car currentCar = iterator.next();
            currentCar.move();
            currentCar.age++;
        }
    }

    public void growHays(){

        List<Vector2d> tilesInJungleList = new ArrayList<Vector2d>();
        List<Vector2d> tilesInStepsList = new ArrayList<Vector2d>();

        for (int i = lowerLeft.x; i < upperRight.x; i++){
            for (int j = lowerLeft.y; j < upperRight.y; j++){
                Vector2d testedTile = new Vector2d(i,j);
                if(!isOccupied(testedTile)){
                    if(inJungle(testedTile)) tilesInJungleList.add(testedTile);
                    else tilesInStepsList.add(testedTile);
                }
            }
        }

        if(tilesInJungleList.size()>0){
            Vector2d jungleTile = tilesInJungleList.get(new Random().nextInt(tilesInJungleList.size()));
            hays.put(jungleTile, new HayStack(jungleTile));
        }
        if(tilesInStepsList.size()>0){
            Vector2d stepTile = tilesInStepsList.get(new Random().nextInt(tilesInStepsList.size()));
            hays.put(stepTile, new HayStack(stepTile));
        }
    }

    public void eat(){
        Collection<HayStack> hay = hays.values();
        for (Iterator<HayStack> iterator = hay.iterator(); iterator.hasNext();) {
            HayStack currentStack = iterator.next();
            List<Car> currentCars = carAt(currentStack.getPosition());
            if(currentCars != null){
                Collections.sort(currentCars, Collections.reverseOrder());
                int count = 1;
                for(int i = 1; i < currentCars.size(); i++){
                    if(currentCars.get(0).energy == currentCars.get(i).energy) count++;
                    else break;
                }
                for(int i = 0; i < count; i++) currentCars.get(i).energy += simulation.plantEnergy/count;
//                hays.remove(currentStack.getPosition());
                iterator.remove();

            }
        }
    }
    public void breed(){
        Collection<List<Car>> carLists = cars.values();
        List<Car> newBabys = new ArrayList<Car>();
        for (Iterator<List<Car>> iterator = carLists.iterator(); iterator.hasNext();) {
            List<Car> list = iterator.next();
            if(list.size() >= 2){
                Collections.sort(list, Collections.reverseOrder());
                if(list.get(1).energy >= simulation.energyToBreed) {
                    Car newBaby = list.get(0).createBaby(list.get(1));
                    newBabys.add(newBaby);
                    newBaby.eraBorn = era;
                    list.get(0).kidList.add(newBaby);
                    list.get(1).kidList.add(newBaby);
                }
            }
        }
        for(int i = 0; i < newBabys.size(); i++){
            place(newBabys.get(i), true);
        }
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }
}
