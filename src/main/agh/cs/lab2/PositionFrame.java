package agh.cs.lab2;

import javax.swing.*;
import java.awt.*;

public class PositionFrame extends JFrame {
    int width;
    int height;
    int tileWidth;
    int tileHeight;
    Simulation simulation;
    Vector2d start;
    private InterfacePanel interfacePanel;

    private PositionPanel drawPanel;

    public PositionFrame(String str, Simulation simulation, int width, int height){
//        super(str);
        setMinimumSize(new Dimension(1000,1000));
        super.setLayout(new BorderLayout());
        this.start = new Vector2d(50,50);
        this.simulation = simulation;
        this.width = width;
        this.height = height;
        this.tileHeight = (height-100)/simulation.constants.height;
        this.tileWidth = (width-100)/simulation.constants.width;
        drawPanel = new PositionPanel(simulation, this);
        interfacePanel = new InterfacePanel(simulation);

        add(interfacePanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        setSize(width, height);
    }

    public void start(){
        setVisible(true);
    }


    public void drawNew() {
        interfacePanel.changeLabelInformations();
        drawPanel.repaint();
    }
}
