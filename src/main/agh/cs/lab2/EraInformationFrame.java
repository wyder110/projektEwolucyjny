package agh.cs.lab2;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EraInformationFrame extends JFrame {

    public EraInformationFrame(Simulation simulation){
        setSize(400,400);


        JTextField era = new JTextField("Wpisz numer ery (<"+simulation.map.era+")");
        JButton calculateInformations = new JButton("Rachuj!");
        JButton saveToFile = new JButton("Zapisz");
        saveToFile.setEnabled(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1));

        JLabel allAnimals = new JLabel();
        JLabel allHays = new JLabel();
        JLabel dominantGene = new JLabel();
        JLabel averageEnergy = new JLabel();
        JLabel averageAge = new JLabel();
        JLabel averageChildren = new JLabel();
        panel.add(era);
        panel.add(allAnimals);
        panel.add(allHays);
        panel.add(dominantGene);
        panel.add(averageEnergy);
        panel.add(averageAge);
        panel.add(averageChildren);
        panel.add(calculateInformations);
        panel.add(saveToFile);

        calculateInformations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int eraInt = Integer.parseInt(era.getText());
                if(eraInt > simulation.map.era) return;
                saveToFile.setEnabled(true);
                SimulationInformations info = simulation.simulationInformations.get(eraInt);
                System.out.println(eraInt);
                allAnimals.setText("Ilość zwierząt: "+info.allAnimals);
                allHays.setText("Ilość roślin: "+info.allHays);
                averageAge.setText("Średni wiek: "+info.averageAge);
                averageChildren.setText(" Średnia ilość dzieci: "+info.averageChildren);
                averageEnergy.setText(" Średnia energia: "+ info.averageEnergy);
                dominantGene.setText(" Dominujący gen: " +info.maxGene);
            }
        });

        saveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int eraInt = Integer.parseInt(era.getText());
                if(eraInt > simulation.map.era) return;
                saveToJson(simulation.simulationInformations.get(eraInt));
            }
        });

        add(panel);
        setVisible(true);
    }
    private void saveToJson(SimulationInformations info){
        JSONObject json = new JSONObject();

        json.put("alimalsAlive", info.allAnimals);
        json.put("haysAmount", info.allHays);
        json.put("era", info.era);
        json.put("dominantGene", info.maxGene);
        json.put("averageAge", info.averageAge);
        json.put("averageChildren", info.averageChildren);
        json.put("averageEnergy", info.averageEnergy);

        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        System.out.println(formatter.format(date));

        try (FileWriter file = new FileWriter("./"+info.era+"eraInfo"+formatter.format(date)+".json")) {
            file.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
