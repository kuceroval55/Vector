package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.AbstractGraphicObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    public DrawPanel(List<AbstractGraphicObject> objects) {
        this.objects = objects;
        initGui();
    }

    private void initGui(){
        setBackground(Color.white);
        setPreferredSize(new Dimension(800,600));
    }

    private List<AbstractGraphicObject> objects = new ArrayList<>();

    public DrawPanel() {
        initGui();
    }

    public void addObject(AbstractGraphicObject obj) {
        objects.add(obj);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (var obj : objects) {
            obj.draw((Graphics2D) g);
        }
    }
}
