package com.alex.test.view;

import com.alex.test.controller.Entity;

import java.awt.*;

public interface ImageLoader {

    public Image getImage(Entity entity);

    public Entity selectedEntity();
}
