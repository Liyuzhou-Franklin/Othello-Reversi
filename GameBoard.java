package org.cis1200.othello;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.JPanel;


public class GameBoard extends JPanel {

    private Othello model;
    private JLabel status;
    private JLabel ratio;
    private JLabel blackWhiteStatus;
    public static final int BOARD_WIDTH = 640;
    public static final int BOARD_HEIGHT = 640;

    public GameBoard() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);
        model = new Othello();
        status = new JLabel("Black's turn");
        ratio = new JLabel("Black : White");
        blackWhiteStatus = new JLabel(model.getNumberOfBlack() + " : " +
                model.getNumberOfWhite());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                model.playTurn(p.y / 80, p.x / 80);
                updateStatus();
                updateDiskStatus();
                repaint();
            }
        });
    }

    public void reset() {
        model.reset();
        status.setText("Black's turn");
        blackWhiteStatus.setText(model.getNumberOfBlack() + " : " +
                model.getNumberOfWhite());
        repaint();
        requestFocusInWindow();
    }

    private void updateStatus() {
        if (model.getPlayer()) {
            status.setText("Black's turn");
        } else {
            status.setText("White's turn");
        }

        int winner = model.checkWinner();
        if (winner == 1) {
            status.setText("Black wins!");
        } else if (winner == 2) {
            status.setText("White wins!");
        } else if (winner == 3) {
            status.setText("It's a tie!");
        }

        model.writeGameToFile("gameState");
    }

    public void resumeLastGame() {
        model.loadGameFromFile("gameState");
        updateStatus();
        updateDiskStatus();
        repaint();
    }

    public JLabel getStatus() {
        return status;
    }

    private void updateDiskStatus() {
        blackWhiteStatus.setText(model.getNumberOfBlack() + " : " +
                model.getNumberOfWhite());
    }

    public JLabel getRatio() {
        return ratio;
    }

    public JLabel getBlackWhiteStatus() {
        return blackWhiteStatus;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 80; i <= 560; i += 80) {
            g.drawLine(i, 0, i, 640);
        }
        for (int j = 80; j <= 560; j += 80) {
            g.drawLine(0, j, 640, j);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int k = model.getCell(i, j);
                if (k == 1) {
                    g.setColor(Color.BLACK);
                    g.fillOval(5 + 80 * j, 5 + 80 * i, 70, 70);
                } else if (k == 2) {
                    g.setColor(Color.WHITE);
                    g.fillOval(5 + 80 * j, 5 + 80 * i, 70, 70);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
