package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.*;
import cz.uhk.vedit.model.Polygon;
import cz.uhk.vedit.model.Rectangle;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class VeditFrame extends JFrame {
    private DrawPanel drawPanel = new DrawPanel();
    private Random random = new Random();

    private Color randomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private int randomPos(int min, int max) {
        return random.nextInt(min, max);
    }

    private int randomSize(int min, int max) {
        return min + random.nextInt(max - min);
    }

    public VeditFrame() {
        super("FIM Vector Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(createToolbar(), BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        initSampleData();

        pack();
    }

    private JToolBar createToolbar() {
        var tb = new JToolBar(JToolBar.HORIZONTAL);
//        var btnSquare = new JButton("Square");
//        tb.add(btnSquare);
//        btnSquare.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                drawPanel.addObject(new Square(10,10,Color.darkGray, 50));
//            }
//        });

        var actSquare = new AbstractAction("Square") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = randomSize(30, 120);
                Color color = randomColor();
                int py =  randomPos(0, drawPanel.getHeight() - size);
                int px = randomPos(0, drawPanel.getWidth() - size);
                drawPanel.addObject(new Square(px, py, color, size));
            }
        };

        var actRectangle = new AbstractAction("Rectangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sizeY = randomSize(40, 150);
                int sizeX = randomSize(40, 150);
                Color color = randomColor();
                int px = randomPos(0, drawPanel.getWidth() - sizeX);
                int py = randomPos(0, drawPanel.getHeight() - sizeY);
                drawPanel.addObject(new Rectangle(px, py, color, sizeX, sizeY));
            }
        };

        var actTriangle = new AbstractAction("Triangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size =  randomSize(30, 150);
                Color color = randomColor();

                int halfOfC = (int) Math.ceil(Math.sqrt(
                        Math.pow((2.0/3.0 * size), 2) -
                        Math.pow((1.0/3.0 * size), 2)));
                int centerToVertex = (int) Math.ceil(((double) size * (2.0/3.0)));
                int centerToSide = (int) Math.ceil(((double) size * (1.0/3.0)));

                int py = randomPos(centerToVertex, drawPanel.getHeight() - centerToSide);
                int px = randomPos(halfOfC, drawPanel.getWidth() - halfOfC);
                drawPanel.addObject(new Triangle(px,  py, color, size));
            }
        };

        var actCircle = new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size =  randomSize(20, 80);
                Color color = randomColor();
                int px = randomPos(size, drawPanel.getWidth() - size);
                int py = randomPos(size, drawPanel.getHeight() - size);
                drawPanel.addObject(new Circle(px,  py, color, size));
            }
        };

        var actPolygon = new AbstractAction("Polygon") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size =  randomSize(20, 100);
                Color color = randomColor();
                int py = randomPos(size, drawPanel.getHeight() - size);
                int px = randomPos(size, drawPanel.getWidth() - size);
                int vertexes = randomSize(4,10);
                drawPanel.addObject(new Polygon(px, py, color, size, vertexes));
            }
        };


        var actClear = new AbstractAction("Delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.clearCanvas();
            }
        };

        var actSavePNG = new AbstractAction("Save canvas to PNG") {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.saveToPNG();
            }
        };

        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint triangle");
        tb.add(actTriangle);

        actPolygon.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint polygon");
        tb.add(actPolygon);

        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint Rectangle");
        tb.add(actRectangle);

        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint Circle");
        tb.add(actCircle);

        actSquare.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint square");
        actSquare.putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_F2,0)); //spusteni klavesou F2, ale musi byt v menu
        tb.add(actSquare);

        actClear.putValue(AbstractAction.SHORT_DESCRIPTION, "Clear Canvas");
        tb.add(actClear);

        actSavePNG.putValue(AbstractAction.SHORT_DESCRIPTION, "Save canvas to PNG");
        tb.add(actSavePNG);

        return tb;
    }

    private void initSampleData() {
        /*drawPanel.addObject(new Square(100,100, Color.RED,50));
        drawPanel.addObject(new Rectangle(500,60, Color.yellow,60, 70));
        drawPanel.addObject(new Circle(300, 400, Color.BLACK, 60));
        drawPanel.addObject(new Triangle(200, 400, Color.GREEN, 200));*/

        Group gr = new Group();
        drawPanel.addObject(gr);
        gr.addObject(new Triangle(300, 200, Color.magenta, 100));
        gr.addObject(new Circle(300, 200, Color.blue, 50));
        gr.addObject(new Square(100, 0, Color.blue, 50));
        gr.addObject(new Polygon(100, 200, Color.yellow, 50, 6));
    }
}
