package com.alex.test.view;

import com.alex.test.controller.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;
import java.util.Set;

public class Cell extends JButton implements ActionListener {

    private Set<Entity> entities = EnumSet.noneOf(Entity.class);
    private ImageLoader loader;

    Cell(ImageLoader loader) {
        super();
        this.loader = loader;
        setBackground(Color.WHITE);
        addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Image img;
        for (Entity entity : entities) {
            img = loader.getImage(entity);
            g.drawImage(img.getScaledInstance(this.getWidth(), getHeight(), Image.SCALE_FAST), 0, 0, this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (loader.selectedEntity().equals(Entity.EMPTY)) {
            entities.clear();
            return;
        }
        entities.add(loader.selectedEntity());

    }

    public Set<Entity> getEntities() {
        return entities;
    }
}
