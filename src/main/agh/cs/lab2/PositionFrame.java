package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PositionFrame extends JFrame {
    int width;
    int height;
    int tileWidth;
    int tileHeight;
    Simulation simulation;
    Vector2d start;

    JLabel informations;
    private JPanel buttons;
    private JButton getInformation;
    private JComboBox carList;

    public PositionFrame(String str, Simulation simulation, int width, int height){
//        super(str);
        super.setLayout(new GridLayout(20, 10));
        this.start = new Vector2d(50,120);
        this.simulation = simulation;
        this.width = width;
        this.height = height;
        this.tileHeight = (height-100)/simulation.height;
        this.tileWidth = (width-100)/simulation.width;

        buttons = new JPanel();
        addStopButton();
        addNewButton();
        addLabels();
        addPanel();
        add(buttons);
        setSize(width+80, height+80);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane();
    }
    public void start(){
        setVisible(true);
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
                        carList.setEnabled(false);
                        carList.removeAllItems();

                        simulation.startRunning();
                    }
                    else {
                        getInformation.setEnabled(true);
                        carList.setEnabled(true);
                        addItemsToCarList();
                        simulation.stopRunning();
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

    public void drawNew() {
        PositionPanel drawPanel = new PositionPanel(simulation, this);

        informations.setText("Ilość zwierząt: " + simulation.map.cars.size() +
        " Ilość roślin: " + simulation.map.hays.size()+
        " Dominujący gen: " + simulation.maxGene+
        " Średnia energia: "+ simulation.averageEnergy+
        " Średnia ilość dzieci: "+ simulation.averageChildren+
        " Średnia długość życia: "+ simulation.averageAge+
        " Era " + simulation.map.era
        );


        Font f = new Font("TimesRoman", Font.PLAIN, 10);
        informations.setFont(f);
//        allHays.setFont(f);
//        JPanel panel = new JPanel();

        drawPanel.paintBoard(getGraphics());
        drawPanel.paintHays(getGraphics());
        drawPanel.paintAnimals(getGraphics());
//        add(panel);

//        this.add(drawPanel);
    }

    private void addItemsToCarList(){
        for(Car car : simulation.map.carList){
            carList.addItem("Pozycja: " + car.getPosition()+  " energia: " + car.energy);
        }
        for(Car car : simulation.map.deadCarList){
            carList.addItem("Martwe które zmarło: " + car.eraDeath);
        }

    }
}
