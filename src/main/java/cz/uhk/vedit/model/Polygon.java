package cz.uhk.vedit.model;

import java.awt.*;

public class Polygon extends AbstractGraphicObject {
    int height;
    int vertexesCount;      // počet vrcholů mnohouhelníku

    public Polygon() {}

    public Polygon(Point point, Color color, int height, int countVertexes) {
        super(point, color);
        this.height = height;
        this.vertexesCount = countVertexes;
    }

    public Polygon(int x, int y, Color color, int height, int countVertexes) {
        super(x, y, color);
        this.height = height;
        this.vertexesCount = countVertexes;
    }

    private Point[] getVertexes(Point center,int height, int vertexesCount) {
        Point[] points = new Point[vertexesCount];      // deklarace bodů vrcholů
        for (int i = 0; i < vertexesCount; i++) {
            points[i] = new Point();
        }

        for (int i = 0; i < vertexesCount; i++) {
            double rad = (i * 2 * Math.PI) / vertexesCount;         // rozseká jednotkovou kružnici na x počet dílů
            points[i].x = (int) (center.x + (height * Math.cos(rad)));      // ke středu přičte delku a pozici na jednotkové kružnici
            points[i].y = (int) (center.y + (height * Math.sin(rad)));

        }
        return points;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        Point[] points = getVertexes(point, height, vertexesCount);
        for (int i = 0; i < vertexesCount; i++) {
            if (i == vertexesCount - 1) {
                g2d.drawLine(points[i].x, points[i].y, points[0].x, points[0].y);
            } else {
                g2d.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
            }
        }
    }

    @Override
    public boolean contains(Point p) {
        if (p == null) return false;
        Point[] vertexes = getVertexes(this.point, this.height, this.vertexesCount);
        java.awt.Polygon polygon = new java.awt.Polygon();

        for (Point v : vertexes) {
            polygon.addPoint(v.x, v.y);
        }

        return polygon.contains(p);
    }
}
