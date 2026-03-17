package cz.uhk.vedit.model;

import java.awt.*;

public class Circle extends AbstractGraphicObject {

    protected int r;

    public Circle() {}

    public Circle(Point point, Color color, int r) {
        super(point, color);
        this.r = r;
    }

    public Circle(int x, int y, Color color, int r) {
        super(x, y, color);
        this.r = r;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawOval(point.x - r, point.y - r, 2 * r, 2 * r); //vzorec z tabule
    }

    @Override
    public boolean contains(Point p) {
        double distanceSquared = Math.pow(p.x - point.x, 2) + Math.pow(p.y - point.y, 2);
        return distanceSquared <= Math.pow(r, 2);
    }
}