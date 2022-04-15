package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.MouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static sk.stuba.fei.uim.oop.gui.Colors.*;

public class Board extends JPanel implements MouseAdapter {
    private int size;
    private char[] board;

    public Board(int size) {
        this.size = size;
        this.setLayout(new GridLayout(size, size));
        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j += 2) {
                if ((i + 1) % 2 == 0) {
                    this.add(new Cell(i, j, LIGHT_GREEN));
                    this.add(new Cell(i, j + 1, DARK_GREEN));
                } else {
                    this.add(new Cell(i, j, DARK_GREEN));
                    this.add(new Cell(i, j + 1, LIGHT_GREEN));
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component com = this.getComponentAt(e.getPoint());
        int rows = ((Cell) com).getRows();
        int cols = ((Cell) com).getCols();
        System.out.println(rows + " " + cols);
    }
}