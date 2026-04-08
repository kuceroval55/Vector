package cz.uhk.vedit.model;

import java.awt.*;

public class Triangle extends AbstractGraphicObject {
    int height;

    public Triangle() {}

    public Triangle(Point point, Color color, int height) {
        super(point, color);
        this.height = height;
    }

    public Triangle(int x, int y, Color color, int height) {
        super(x, y, color);
        this.height = height;
    }

    private Point[] getVertexes(Point center){
        Point[] points = new Point[3];
        points[0] = new Point();
        points[1] = new Point();
        points[2] = new Point();

        double halfOfC = Math.sqrt( (double)
                Math.pow((2.0/3.0 * height), 2) -
                Math.pow((1.0/3.0 * height), 2));

        points[0].y = center.y - (int) (2.0/3.0 * height);
        points[0].x = center.x;

        points[1].y = center.y + (int) (1.0/3.0 * height);
        points[1].x = center.x - (int) halfOfC;

        points[2].y = center.y + (int) (1.0/3.0 * height);
        points[2].x = center.x + (int) halfOfC;

        return points;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        Point[] points = getVertexes(point);
        g2d.drawLine((int) points[0].x, (int) points[0].y, (int) points[1].x, (int) points[1].y);
        g2d.drawLine((int) points[1].x, (int) points[1].y, (int) points[2].x, (int) points[2].y);
        g2d.drawLine((int) points[0].x, (int) points[0].y, (int) points[2].x, (int) points[2].y);

    }

    @Override
    public boolean contains(Point p) {
        if (p == null) return false;
        Point[] vertexes = getVertexes(this.point);
        java.awt.Polygon polygon = new java.awt.Polygon();

        for (Point v : vertexes) {
            polygon.addPoint(v.x, v.y);
        }

        return polygon.contains(p);
    }
}
