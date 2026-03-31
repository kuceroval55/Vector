package cz.uhk.vedit.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group extends AbstractGraphicObject{

    protected List<AbstractGraphicObject> items = new ArrayList<>();

    public void addObject(AbstractGraphicObject o) {
        items.add(o); //delegace
    }

    @Override
    public void draw(Graphics2D g) {
        items.forEach(it -> it.draw(g));
    }

    @Override
    public boolean contains(Point p) {
        for (var it : items) {
            if (it.contains(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(int dx, int dy) {
        items.forEach(it -> it.move(dx, dy));
    }
}
