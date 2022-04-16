package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.MouseAdapterInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static sk.stuba.fei.uim.oop.gui.Colors.*;

public class Board extends JPanel implements MouseAdapterInterface {
    private int size, prevRow, prevCol;
    private Cell[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new Cell[this.size][this.size];

        this.setLayout(new GridLayout(this.size, this.size));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j += 2) {
                if ((i + 1) % 2 == 0) {
                    this.board[i][j] = new Cell(i, j, LIGHT_GREEN);
                    this.board[i][j + 1] = new Cell(i, j + 1, DARK_GREEN);
                } else {
                    this.board[i][j] = new Cell(i, j, DARK_GREEN);
                    this.board[i][j + 1] = new Cell(i, j + 1, LIGHT_GREEN);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.removeAll();

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.add(this.board[i][j]);
            }
        }

        this.revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Cell comp = (Cell) this.getComponentAt(e.getPoint());
        int rows = comp.getRow();
        int cols = comp.getCol();
        String out = String.format("%s: %d %d", this.getClass().getSimpleName(), rows, cols);
        System.out.println(out);

        this.board[rows][cols] = new Cell(rows, cols, comp.getBackColor(), Color.RED);

        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Cell comp;
        try {
            comp = (Cell) this.getComponentAt(e.getPoint());
        } catch (ClassCastException exc) {return;}
        int rows = comp.getRow();
        int cols = comp.getCol();

        if (this.board[rows][cols].getChipColor() == TRANSPARENT) {
            this.board[rows][cols] = new Cell(rows, cols, comp.getBackColor(), LIGHT_CYAN);

            if (rows != this.prevRow || cols != this.prevCol) {
                Color prevBack = this.board[this.prevRow][this.prevCol].getBackColor();
                Color prevChip = this.board[this.prevRow][this.prevCol].getChipColor();
                if (prevChip == LIGHT_CYAN) prevChip = TRANSPARENT;
                this.board[this.prevRow][this.prevCol] = new Cell(this.prevRow, this.prevCol, prevBack, prevChip);
                this.prevRow = rows;
                this.prevCol = cols;
            }

            this.repaint();
        }
    }
}