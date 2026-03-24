package cz.uhk.vedit.model;

import java.awt.*;

public class Rectangle extends AbstractGraphicObject {

    protected int a;
    protected int b;

    public Rectangle() {}

    public Rectangle(Point point, Color color, int a, int b) {
        super(point, color);
        this.a = a;
        this.b = b;
    }

    public Rectangle(int x, int y, Color color, int a, int b) {
        super(x, y, color);
        this.a = a;
        this.b = b;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawRect(point.x, point.y, a, b);
    }

    @Override
    public boolean contains(Point p) {
        return  point.x <= p.x && p.x <= point.x + a &&
                point.y <= p.y && p.y <= point.y + b;
    }
}
