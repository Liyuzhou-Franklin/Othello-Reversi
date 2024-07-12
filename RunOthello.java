package org.cis1200.othello;

import javax.swing.*;
import java.awt.*;

public class RunOthello implements Runnable {

    public void run() {
        final JFrame frame = new JFrame("Othello");
        frame.setLocation(640, 640);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);

        final org.cis1200.othello.GameBoard board = new GameBoard();
        Color boardColor = new Color(80, 200, 120);
        board.setBackground(boardColor);
        frame.add(board, BorderLayout.CENTER);

        JLabel status = board.getStatus();
        status_panel.add(status);

        JPanel diskPanel = new JPanel();
        frame.add(diskPanel, BorderLayout.WEST);

        JLabel ratio = board.getRatio();
        JLabel blackWhiteStatus = board.getBlackWhiteStatus();
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.add(ratio);
        westPanel.add(blackWhiteStatus);

        diskPanel.add(westPanel);

        JPanel empty = new JPanel();
        frame.add(empty, BorderLayout.EAST);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        final JButton resume = new JButton("Resume");
        resume.addActionListener(e -> board.resumeLastGame());
        control_panel.add(resume);

        final JButton instruction = new JButton("Rules");
        instruction.addActionListener(e -> openNewWindow());
        control_panel.add(instruction);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        board.reset();
    }

    private static void openNewWindow() {
        JFrame instructionWindow = new JFrame("Othello Instructions");
        instructionWindow.setSize(300, 150);
        instructionWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Brief explanation of rules: ");
        label.setHorizontalAlignment(JLabel.CENTER);
        JTextArea explanation = new JTextArea("Othello (Reversi) is a strategic board " +
                "game played on an 8*8 board. Two players compete, using 64 identical " +
                "game pieces (\"disks\") that are white on one side and black on the " +
                "other. Each player chooses one color to use throughout the game. Players " +
                "take turns placing one disk on an empty square, with their assigned color " +
                "facing up. After a play is made, any disks of the opponent's color that " +
                "lie in a straight line bounded by the one just played and another one in " +
                "the current player's color are turned over. When all playable empty " +
                "squares are filled, the player with more disks showing in their own color " +
                "wins the game.");

        explanation.setEditable(false);
        explanation.setLineWrap(true);
        explanation.setWrapStyleWord(true);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        instructionWindow.add(panel);

        JScrollPane scrollPane = new JScrollPane(explanation);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        instructionWindow.add(scrollPane);

        instructionWindow.setVisible(true);
    }

}
