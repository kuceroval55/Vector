package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.Circle;
import cz.uhk.vedit.model.Rectangle;
import cz.uhk.vedit.model.Square;

import javax.swing.*;
import java.awt.*;

public class VeditFrame extends JFrame {
    private DrawPanel drawPanel = new DrawPanel();

    public VeditFrame() {
        super("FIM Vector Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(drawPanel, BorderLayout.CENTER);
        initSampleData();

        pack();
    }

    private void initSampleData() {
        drawPanel.addObject(new Square(100,100, Color.RED,50));
        drawPanel.addObject(new Rectangle(500,60, Color.BLUE,60, 70));
        drawPanel.addObject(new Circle(300, 400, Color.BLACK, 60));
    }
}
