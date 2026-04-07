package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.*;
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

    private int randomPos(int max) {
        return random.nextInt(max);
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
                drawPanel.addObject(new Square(
                        randomPos(drawPanel.getWidth()),
                        randomPos(drawPanel.getHeight()),
                        randomColor(), size
                ));
            }
        };

        var actRectangle = new AbstractAction("Rectangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Rectangle(
                        randomPos(drawPanel.getWidth()),
                        randomPos(drawPanel.getHeight()),
                        randomColor(),
                        randomSize(40, 150), randomSize(40, 150)
                ));
            }
        };

        var actTriangle = new AbstractAction("Triangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Triangle(
                        randomPos(drawPanel.getWidth()),
                        randomPos(drawPanel.getHeight()),
                        randomColor(),
                        randomSize(30, 120)
                ));
            }
        };

        var actCircle = new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.addObject(new Circle(
                        randomPos(drawPanel.getWidth()),
                        randomPos(drawPanel.getHeight()),
                        randomColor(),
                        randomSize(20, 80)
                ));
            }
        };

        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint triangle");
        tb.add(actTriangle);

        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint Rectangle");
        tb.add(actRectangle);

        actTriangle.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint Circle");
        tb.add(actCircle);

        actSquare.putValue(AbstractAction.SHORT_DESCRIPTION, "Paint square");
        actSquare.putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_F2,0)); //spusteni klavesou F2, ale musi byt v menu
        tb.add(actSquare);

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
    }
}
