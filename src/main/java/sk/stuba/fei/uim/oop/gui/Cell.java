package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

import static sk.stuba.fei.uim.oop.gui.Colors.TRANSPARENT;

public class Cell extends JPanel {
    @Getter
    private int rows, cols;
    @Getter
    private Color backColor, chipColor;

    public Cell(int rows, int cols, Color backColor) {
        this.rows = rows;
        this.cols = cols;
        this.backColor = backColor;
        this.setBackground(this.backColor);

        this.chipColor = TRANSPARENT;
    }

    public Cell(int rows, int cols, Color backColor, Color chipColor) {
        this(rows, cols, backColor);

        this.chipColor = chipColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(this.chipColor);
        int width = (int) (this.getWidth() * 0.8);
        int height = (int) (this.getHeight() * 0.8);
        g.fillOval((this.getWidth() - width) / 2, (this.getHeight() - height) / 2, width, height);
    }
}
