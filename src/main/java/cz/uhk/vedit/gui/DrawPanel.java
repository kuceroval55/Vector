package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.AbstractGraphicObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    public DrawPanel(List<AbstractGraphicObject> objects) {
        this.objects = objects;
        initGui();
    }
    private AbstractGraphicObject selected;
    private int dx,dy;
    private Point oldMouse;

    private void initGui(){
        setBackground(Color.white);
        setPreferredSize(new Dimension(800,600));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selected = findObjectUnderMouse(e.getPoint());
                if(selected != null){
                    oldMouse = e.getPoint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selected != null){
                    int dx = e.getX() - oldMouse.x;
                    int dy = e.getY() - oldMouse.y;
                    selected.move(dx, dy);
                    oldMouse = e.getPoint();
                    repaint(); //znovu preklesime
                }
            }
        });
    }

    private AbstractGraphicObject findObjectUnderMouse(Point point) {
        return objects.stream().filter(obj -> obj.contains(point)).findFirst().orElse(null);
    }

    private List<AbstractGraphicObject> objects = new ArrayList<>();

    public DrawPanel() {
        initGui();
    }

    public void addObject(AbstractGraphicObject obj) {
        objects.add(obj);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // render objektu
        ((Graphics2D)g).setStroke(new BasicStroke(2f)); //sirka cary

        for (var obj : objects) {
            obj.draw((Graphics2D) g);
        }
    }
}
