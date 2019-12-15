package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public class PositionPanel extends JPanel {
    Simulation simulation;
    PositionFrame frame;

    public PositionPanel(Simulation simulation, PositionFrame frame){
        this.simulation = simulation;
        this.frame = frame;
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//    }

    protected void paintBoard(Graphics g){
//        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.fillRect(frame.start.x, frame.start.y, frame.tileWidth*simulation.width, frame.tileHeight*simulation.height);

        g.setColor(Color.green);

        int jungleWidth = frame.tileWidth*(simulation.map.upperRightJungle.x-simulation.map.lowerLeftJungle.x);
        int jungleHeigth = frame.tileHeight*(simulation.map.upperRightJungle.y-simulation.map.lowerLeftJungle.y);

        g.fillRect(frame.start.x+simulation.map.lowerLeftJungle.x*frame.tileWidth, frame.start.y+simulation.map.lowerLeftJungle.y*frame.tileHeight, jungleWidth, jungleHeigth);
//        for(int i = simulation.map.lowerLeft.x; i <= simulation.map.upperRight.x; i+=1){
//            for(int j = simulation.map.lowerLeft.y; j <= simulation.map.upperRight.y; j+=1){
//                g.drawRect(frame.start.x + i*frame.tileWidth, frame.start.y + j*frame.tileWidth, frame.tileWidth, frame.tileHeight);
//            }
//        }
    }

    protected void paintHays(Graphics g) {
//        super.paintComponent(g);
        g.setColor(Color.ORANGE);

        for(HayStack stack : simulation.map.hays.values()){


            g.fillRect(frame.start.x+(frame.tileWidth/4) + stack.getPosition().x*frame.tileWidth, frame.start.y +(frame.tileHeight/4)+ stack.getPosition().y*frame.tileHeight, frame.tileWidth/2, frame.tileHeight/2);
        }
//        g.fillRect(0, 0, getWidth(), getHeight());
//        g.setColor(Color.GREEN);
//        g.fillRect(200, 200, 350, 350);
    }

    protected void paintAnimals(Graphics g) {
//        super.paintComponent(g);


        Collection<java.util.List<Car>> carListList = simulation.map.cars.values();


        for (Iterator<java.util.List<Car>> iterator = carListList.iterator(); iterator.hasNext();) {
            java.util.List<Car> currentList = iterator.next();
            if(currentList.size()>0) {
                Car car = currentList.get(0);
                g.setColor(Color.RED);
                g.fillOval(frame.start.x + car.getPosition().x*frame.tileWidth, frame.start.y + car.getPosition().y*frame.tileHeight, frame.tileWidth, frame.tileHeight);

                g.setColor(Color.BLUE);
                g.drawString(Integer.toString(car.energy), frame.start.x+frame.tileWidth/2+car.getPosition().x*frame.tileWidth,frame.start.y +frame.tileHeight/2+ car.getPosition().y*frame.tileHeight);

            }
        }


//        g.fillRect(0, 0, getWidth(), getHeight());
//        g.setColor(Color.GREEN);
//        g.fillRect(200, 200, 350, 350);
    }

}
