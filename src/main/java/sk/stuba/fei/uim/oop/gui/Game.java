package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.UniversalAdapter;

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
        this.frame = new JFrame("Reversi");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(720,752);
        this.frame.setResizable(false);
        this.frame.setFocusable(true);

        this.frame.setLayout(new BorderLayout());
        this.boardSize = 6;
        restart(this.boardSize);
        this.frame.addKeyListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 16));

        this.playerLabel = new JLabel("Player");
        panel.add(this.playerLabel);

        JButton button = new JButton("Restart");
        button.addActionListener(this);
        button.setFocusable(false);
        panel.add(button);

        this.boardSizeLabel = new JLabel("6 x 6");
        panel.add(this.boardSizeLabel);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 6, 12, this.boardSize);
        slider.setMajorTickSpacing(2);
        slider.setPaintLabels(true);
        slider.setFocusable(false);
        slider.addChangeListener(this);
        panel.add(slider);

        this.frame.add(panel, BorderLayout.PAGE_END);

        this.frame.setVisible(true);
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
        restart(this.boardSize);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE: this.frame.dispose(); break;
            case KeyEvent.VK_R: restart(this.boardSize); break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int size = ((JSlider) e.getSource()).getValue();
        if (size != this.boardSize && size % 2 == 0) {
            this.boardSize = size;
            restart(this.boardSize);
            this.boardSizeLabel.setText(this.boardSize + " x " + this.boardSize);
        }
    }
}
