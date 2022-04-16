package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.utility.UniversalAdapter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Game extends UniversalAdapter {
    private final JFrame frame;
    private final JLabel playerLabel;
    private final JLabel boardSizeLabel;
    private Board board;
    private int boardSize;

    public Game() {
        frame = new JFrame("Reversi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720,752);
        frame.setResizable(false);
        frame.setFocusable(true);

        frame.setLayout(new BorderLayout());
        boardSize = 6;
        restart(boardSize);
        frame.addKeyListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 16));

        playerLabel = new JLabel("Player");
        panel.add(playerLabel);

        JButton button = new JButton("Restart");
        button.addActionListener(this);
        button.setFocusable(false);
        panel.add(button);

        boardSizeLabel = new JLabel("6 x 6");
        panel.add(boardSizeLabel);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 6, 12, boardSize);
        slider.setMajorTickSpacing(2);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setFocusable(false);
        slider.addChangeListener(this);
        panel.add(slider);

        frame.add(panel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    public void restart(int size) {
        try {
            this.frame.remove(this.board);
        } catch (NullPointerException ignored) {}
        this.board = new Board(size);
        this.frame.add(this.board);
        this.frame.revalidate();
        this.frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.restart(this.boardSize);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE: this.frame.dispose(); break;
            case KeyEvent.VK_R: this.restart(this.boardSize); break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int size = ((JSlider) e.getSource()).getValue();
        if (size != this.boardSize && size % 2 == 0) {
            this.boardSize = size;
            this.restart(this.boardSize);
            this.boardSizeLabel.setText(this.boardSize + " x " + this.boardSize);
        }
    }
}
