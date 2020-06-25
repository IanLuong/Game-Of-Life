package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {

        private int generation = 0;
        private int alive = 0;

        private JPanel infoPanel = new JPanel();
        private JLabel generationLabel = new JLabel("Generation #" + generation);
        private JLabel aliveLabel = new JLabel("Alive: " + alive);

        JToggleButton playToggleButton = new JToggleButton("Play");
        JButton resetButton = new JButton("Reset");

        boolean playing = false;
        boolean reset = false;

        private BoardPanel boardPanel;

        public GameOfLife() {
            super("Game Of Life");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(515, 585);
            setLocationRelativeTo(null);

            initInfoPanel();

            setLayout(null); // sets absolute positioning of components
            setVisible(true);

        }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    private void initInfoPanel() {
        infoPanel.setLayout(new GridLayout(2,1,0,0));
        infoPanel.setBackground(Color.PINK);
        infoPanel.setBounds(0,0,500, 50);

        generationLabel.setName("GenerationLabel");
        generationLabel.setBounds(0,0, 100,30);
        infoPanel.add(generationLabel);

        aliveLabel.setName("AliveLabel");
        aliveLabel.setBounds(0,15, 100,30);
        infoPanel.add(aliveLabel);

        playToggleButton.setName("PlayToggleButton");
        playToggleButton.addActionListener(e -> {
            playing = !playing;
            playToggleButton.setText(playing ? "Pause" : "Play");
        });
        infoPanel.add(playToggleButton);

        resetButton.setName("ResetButton");
        resetButton.addActionListener(e -> reset = true);
        infoPanel.add(resetButton);

        add(infoPanel);
        }

        public void initBoard(Universe universe) {
            boardPanel = new BoardPanel(universe);
            add(boardPanel);
        }

        public void setLabels (int generation, int alive) {
            this.generation = generation;
            this.alive = alive;
            generationLabel.setText("Generation #" + this.generation);
            aliveLabel.setText("Alive: " + this.alive);
        }

        public void setBoard(Universe universe) {
            remove(boardPanel);
            boardPanel = new BoardPanel(universe);
            add(boardPanel);
            repaint();
        }

    }
