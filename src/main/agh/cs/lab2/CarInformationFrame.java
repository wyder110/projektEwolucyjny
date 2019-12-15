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
        panel.setLayout(new GridLayout(6,1));

        JLabel status = new JLabel();
        JLabel kidsAmount = new JLabel();
        JLabel ancestorsAmount = new JLabel();
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
        ancestorsAmount.setText("\nIlość wszystkich potomków " + car.ancestorsAmount());
        bornEra.setText("\nUrodzony w erze " + car.eraBorn);
        genes.setText("\nGeny: " + car.genes);


        panel.add(status);
        panel.add(kidsAmount);
        panel.add(ancestorsAmount);
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
        json.put("ancestorsAmount", car.ancestorsAmount());
        json.put("bornEra", car.eraBorn);
        json.put("genes", car.genes.toString());

        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        System.out.println(formatter.format(date));

        try (FileWriter file = new FileWriter("./"+formatter.format(date)+".json")) {
            file.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
