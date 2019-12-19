package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfacePanel extends JPanel {
    Simulation simulation;
    JLabel informations;
    private JPanel buttons;
    private JButton getInformation;
    private JComboBox carList;
    private JButton eraInformations;

    public InterfacePanel(Simulation simulation){
        this.simulation = simulation;

        buttons = new JPanel();

        setLayout(new GridLayout(2,1));

        addStopButton();
        addNewButton();
        addLabels();
        addPanel();
        add(buttons);
    }
    private void addItemsToCarList(){
        for(Car car : simulation.map.carList){
            carList.addItem("Pozycja: " + car.getPosition()+  " energia: " + car.energy);
        }
        for(Car car : simulation.map.deadCarList){
            carList.addItem("Martwe które zmarło: " + car.eraDeath);
        }

    }
    private void addLabels(){
        informations = new JLabel();
        add(informations);
    }
    private void addPanel(){
        carList = new JComboBox();
        getInformation = new JButton("Wyświetl zaznaczone zwierzę");
        getInformation.setEnabled(false);
        carList.setEnabled(false);

        eraInformations = new JButton("Szczegółowe informacje");
        eraInformations.setEnabled(false);
        eraInformations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EraInformationFrame newFrame = new EraInformationFrame(simulation);
            }
        });

        buttons.add(eraInformations);
        buttons.add(getInformation);
        buttons.add(carList);

        getInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(actionEvent.getActionCommand());
                int index = carList.getSelectedIndex();
                System.out.println(index);
                Car selectedCar;
                if(index < simulation.map.carList.size()) selectedCar = simulation.map.carList.get(index);
                else selectedCar = simulation.map.deadCarList.get(index-simulation.map.carList.size());
                CarInformationFrame newFrame = new CarInformationFrame(selectedCar);
            }
        });

//        System.out.println(comboBox.getSelectedItem());
//        System.out.println(comboBox.getSelectedIndex());
    }

    private void addStopButton(){
        JButton stopButton = new JButton("Stop/Start");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {

                    if(!simulation.isRunning) {
                        getInformation.setEnabled(false);
                        eraInformations.setEnabled(false);
                        carList.setEnabled(false);
                        carList.removeAllItems();

                        simulation.startRunning();
                    }
                    else {
                        simulation.stopRunning();
                        getInformation.setEnabled(true);
                        eraInformations.setEnabled(true);
                        carList.setEnabled(true);
                        addItemsToCarList();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        buttons.add(stopButton);
    }
    private void addNewButton(){
        JButton newSimulation = new JButton("Nowa symulacja");
        newSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Simulation sim = new Simulation();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        buttons.add(newSimulation);
    }
    public void changeLabelInformations(){
        SimulationInformations info = simulation.simulationInformations.get(simulation.map.era);
        informations.setText("Ilość zwierząt: " + info.allAnimals +
                " Ilość roślin: " + info.allHays+
                " Dominujący gen: " + info.maxGene+
                " Średnia energia: "+ info.averageEnergy+
                " Średnia ilość dzieci: "+ info.averageChildren+
                " Średnia długość życia: "+ info.averageAge+
                " Era " + simulation.map.era
        );


        Font f = new Font("TimesRoman", Font.PLAIN, 10);
        informations.setFont(f);
    }

}
