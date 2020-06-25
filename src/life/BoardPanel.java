package life;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardPanel extends JPanel {
    Universe universe;
    int space;

    BoardPanel(Universe universe) {
        this.universe = universe;
        space = 500/universe.getSize();
        setLayout(new GridLayout(universe.getSize(), universe.getSize(),0, 0));
        setBounds(0,50,500, 500);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
        setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < universe.getSize(); i++) {
            for (int j = 0; j < universe.getSize(); j++) {
                if (universe.getCellAtPos(i, j).getValue() == 'O') {
                    g.fillRect(i * space, j * space, space, space);
                } else {
                    g.drawRect(i * space, j * space, space, space);
                }
            }
        }

    }
}