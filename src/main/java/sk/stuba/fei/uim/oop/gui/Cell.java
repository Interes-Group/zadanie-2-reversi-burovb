package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    @Getter
    private int rows, cols;
    private Color color;

    public Cell(int rows, int cols, Color color) {
        this.rows = rows;
        this.cols = cols;
        this.color = color;
        this.setBackground(this.color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
