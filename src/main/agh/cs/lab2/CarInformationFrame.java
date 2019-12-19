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

public class CarInformationFrame extends JFrame {
    public Car car;
    public CarInformationFrame(Car car){
        this.car = car;
        setLayout(new GridLayout(1,1));

        setSize(400,400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,1));

        JLabel status = new JLabel();
        JLabel kidsAmount = new JLabel();
        JLabel bornEra = new JLabel();
        JLabel genes = new JLabel();
        JButton saveToFile = new JButton("Zapisz do pliku");

        saveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveToFile(car);
            }
        });

        if(car.eraDeath == -1) status.setText("Żywy na pozycji " + car.getPosition() + " energia " + car.energy);
        else status.setText("Zmarł w erze " + car.eraDeath);



        kidsAmount.setText("\nIlość dzieci " + car.kidList.size() );
        bornEra.setText("\nUrodzony w erze " + car.eraBorn);
        genes.setText("\nGeny: " + car.genes);



        panel.add(kidsAncestorsAfterNEras());

        panel.add(status);
        panel.add(kidsAmount);
        panel.add(bornEra);
        panel.add(genes);
        panel.add(saveToFile);
        add(panel);

        setVisible(true);
//        addLabels();
    }
    private void saveToFile(Car car){
        JSONObject json = new JSONObject();

        if(car.eraDeath == -1) {
            json.put("alive", true);
            json.put("energy", car.energy);
            json.put("position", car.getPosition().toString());
        }
        else {
            json.put("alive", false);
            json.put("deathEra", car.eraDeath);
        }



        json.put("kidsAmount", car.kidList.size());
        json.put("bornEra", car.eraBorn);
        json.put("genes", car.genes.toString());

        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        System.out.println(formatter.format(date));

        try (FileWriter file = new FileWriter("./animalInfo"+formatter.format(date)+".json")) {
            file.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel kidsAncestorsAfterNEras(){

        JPanel kidsAfterNEras = new JPanel();
        kidsAfterNEras.setLayout(new GridLayout(2,2));

        JTextField era = new JTextField("Wpisz numer ery (<"+car.map.era+")");

        JButton button = new JButton("Rachuj!");

        JLabel kids = new JLabel();
        JLabel ancestors = new JLabel();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int eraInt = Integer.parseInt(era.getText());
                kids.setText("Liczba wszystkich dzieci po " + eraInt + " erach: " + car.kidAmountInEra(eraInt));
            }
        });
        kidsAfterNEras.add(era);
        kidsAfterNEras.add(button);
        kidsAfterNEras.add(kids);
        kidsAfterNEras.add(ancestors);



        return kidsAfterNEras;
    }
}
