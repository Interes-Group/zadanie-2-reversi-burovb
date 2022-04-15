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
    public int boardSize;

    public Game() {
        this.frame = new JFrame("Reversi");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(720,720);
        this.frame.setResizable(false);
        this.frame.setFocusable(true);

        this.frame.setLayout(new BorderLayout());
        this.frame.addKeyListener(this);
        this.boardSize = 6;
        this.frame.add(new Board(this.boardSize));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 32, 16));

        this.playerLabel = new JLabel("Player");
        panel.add(this.playerLabel);

        JButton button = new JButton("Restart");
        button.addActionListener(this);
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
        this.frame.revalidate();
        this.frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE: this.frame.dispose(); break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.boardSize = ((JSlider) e.getSource()).getValue();
        this.boardSizeLabel.setText(this.boardSize + " x " + this.boardSize);
    }
}
