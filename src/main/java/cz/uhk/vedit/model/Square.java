package cz.uhk.vedit.model;

import java.awt.*;

public class Square extends AbstractGraphicObject {

    protected int a;

    public Square() {}

    public Square(Point point, Color color, int a) {
        super(point, color);
        this.a = a;
    }

    public Square(int x, int y, Color color, int a) {
        super(x, y, color);
        this.a = a;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawRect(point.x, point.y, a, a);
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }
}
