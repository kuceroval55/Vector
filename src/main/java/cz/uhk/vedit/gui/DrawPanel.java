package cz.uhk.vedit.gui;

import cz.uhk.vedit.model.AbstractGraphicObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    public void clearCanvas() {
        objects.clear();
        repaint();
    }

    public void saveToPNG() {
        JFileChooser chooser = new JFileChooser();      // vytvoření dialogového okna na výběr souboru
        chooser.setDialogTitle("Save canvas to PNG");
        int userSelection = chooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {     // pokud uživat potvrdil výběr
            File file = chooser.getSelectedFile();
            if (!file.getAbsolutePath().endsWith(".png")) {         // aby nám nevytvořil binárku
                file = new File(file.getAbsolutePath() + ".png");
            }
            try {
                BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = image.createGraphics();        // vztvořím "kreslící hlavu která kreslí do našeho obrázku"
                this.paint(g2d);            // předám kreslící hlavě instrukce aby kreslila to co je v DrawPanelu
                g2d.dispose();
                ImageIO.write(image, "png", file);          // vše uložím do nového souboru
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Can't save canvas to PNG");
            }
        }
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
