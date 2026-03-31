package cz.uhk.vedit.model;

import java.awt.*;

/**
 * Predek vsech grafickych utvaru v editoru
 */
public abstract class AbstractGraphicObject {
    protected Point point; //Referencni bod, od ktereho se bude tvar tvorit
    protected Color color;
    public AbstractGraphicObject(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    public AbstractGraphicObject(int x, int y, Color color) {
        setPoint(x, y);
        this.color = color;
    }

    public AbstractGraphicObject() {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setPoint(int x, int y) {
        this.point = new Point(x, y);
    }

    public abstract void draw(Graphics2D g);

    public boolean contains(int x, int y){
        return contains(new Point(x, y));
    }

    public abstract boolean contains(Point p);

    public void move(int dx, int dy){
        point.translate(dx, dy);
    }
}
