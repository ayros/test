package com.alex.test.view;

import com.alex.test.controller.Entity;
import com.alex.test.controller.Info;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainFrame extends JFrame implements ImageLoader, ListSelectionListener, OutPut {

    private final Map<Entity, Image> entityIconMap = new HashMap<Entity, Image>();
    private FuelText fuel;
    private JPanel eastPanel;
    private View mainPanel;
    private JList resultList;
    private Entity active;

    public MainFrame(String title) {
        super(title);

        loadImages();
        active = Entity.FULL;
        fuel = new FuelText("fuel = ");
        mainPanel = new View(this, fuel);
        mainPanel.addOutput(this);
        eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout());
        JButton button = new JButton("Порешать!!!");
        eastPanel.add(button, BorderLayout.SOUTH);
        eastPanel.add(fuel, BorderLayout.NORTH);
        Object[] elem = new Object[8];
        elem[7] = "delete";
        elem[Entity.ROCK.ordinal()] = new ImageIcon(getClass().getResource("/image/" + Entity.ROCK.toString() + ".png"));
        elem[Entity.CAT.ordinal()] = new ImageIcon(getClass().getResource("/image/" + Entity.CAT.toString() + ".png"));
        elem[Entity.DOG.ordinal()] = new ImageIcon(getClass().getResource("/image/" + Entity.DOG.toString() + ".png"));
        elem[Entity.FULL.ordinal()] = new ImageIcon(getClass().getResource("/image/" + Entity.FULL.toString() + ".png"));
        elem[Entity.ROAD.ordinal()] = new ImageIcon(getClass().getResource("/image/" + Entity.ROAD.toString() + ".png"));
        elem[Entity.ICE.ordinal()] = new ImageIcon(getClass().getResource("/image/" + Entity.ICE.toString() + ".png"));

        JList list = new JList(elem);

        list.addListSelectionListener(this);
        setLayout(new BorderLayout());
        button.addActionListener(mainPanel);
        add(mainPanel, BorderLayout.CENTER);
        add(list, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void loadImages() {
        for (Entity entity : Entity.values()) {
            try {
                System.out.print("norm");
                entityIconMap.put(entity, ImageIO.read(getClass().getResourceAsStream("/image/" + entity.toString() + ".png")));
            } catch (Exception e) {
                try {
                    entityIconMap.put(entity, ImageIO.read(getClass().getResourceAsStream("/image/" + Entity.FULL.toString() + ".png")));
                } catch (Exception err) {

                }
            }
        }
    }


    public Image getImage(Entity entity) {
        return entityIconMap.get(entity);
    }


    public Entity selectedEntity() {
        return active;
    }

    public void valueChanged(ListSelectionEvent e) {
        int index;
        if (e.getSource().equals(resultList)) {
            index = ((JList) e.getSource()).getSelectedIndex();
            mainPanel.show(index);
        }
        index = ((JList) e.getSource()).getSelectedIndex();
        switch (index) {
            case 1:
                active = Entity.FULL;
                break;
            case 2:
                active = Entity.ROCK;
                break;
            case 3:
                active = Entity.CAT;
                break;
            case 4:
                active = Entity.DOG;
                break;
            case 5:
                active = Entity.ROAD;
                break;
            case 6:
                active = Entity.ICE;
                break;
            default:
                active = Entity.EMPTY;
        }
    }

    public void output(List<Info> info, Set<Integer> pattern) {
        Object[] infos = info.toArray();
        try {
            eastPanel.remove(resultList);
        } catch (NullPointerException e) {

        }
        resultList = new JList(infos);
        resultList.addListSelectionListener(this);
        eastPanel.add(resultList, BorderLayout.CENTER);
        eastPanel.repaint();
    }
}
