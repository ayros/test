package com.alex.test.view;

import com.alex.test.controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class View extends JPanel implements ActionListener, OutPut, OutputSubject {

    private Cell[][] arr = new Cell[Direction.N][Direction.M];
    private Set<OutPut> outPuts = new LinkedHashSet<OutPut>();
    private List<Info> results;
    private Set<Integer> pattern;
    private Fuel fuel;

    View(ImageLoader loader, Fuel fuel) {
        super();
        this.fuel = fuel;
        setLayout(new GridLayout(Direction.N, Direction.M));

        for (int i = 0; i < Direction.N; i++) {
            for (int j = 0; j < Direction.M; j++) {
                arr[i][j] = new Cell(loader);
                add(arr[i][j]);
            }
        }
    }


    public Cell[][] getArr() {
        return arr;
    }

    public void actionPerformed(ActionEvent e) {
        Area area = new Area(getArr(), fuel.getFuel());
        area.addOutput(this);
        area.start();
    }


    public void output(List<Info> info, Set<Integer> pattern) {
        results = info;
        this.pattern = pattern;
        result();
    }

    public void show(int num) {
        Set<Integer> set;
        Info inf = results.get(num);
        for (int i = 0; i < Direction.N; i++) {
            for (int j = 0; j < Direction.M; j++) {
                arr[i][j].getEntities().clear();
            }
        }

        for (Entity entity : Entity.values()) {
            if (inf.contains(entity)) {
                set = inf.getCoordinates(entity);
                for (Integer i : set) {
                    arr[i / Direction.M][i % Direction.M].getEntities().add(entity);
                }
            }
        }
        for (Integer i : pattern) {
            arr[i / Direction.M][i % Direction.M].getEntities().add(Entity.FULL);
        }
        repaint();

    }

    public void addOutput(OutPut outPut) {
        outPuts.add(outPut);
    }

    public void removeOutput(OutPut outPut) {
        outPuts.remove(outPut);
    }

    public void result() {
        for (OutPut outPut : outPuts) {
            outPut.output(results, pattern);
        }
    }
}
