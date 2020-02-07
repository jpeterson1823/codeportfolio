package window.panels;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class InfoPanel extends JPanel
{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 40;

    // These should be self-explanatory
    private int xpoints = 0, opoints = 0;
    private JLabel xScore = new JLabel("X Score: " + xpoints);
    private JLabel oScore = new JLabel("O Score: " + opoints);
    private JLabel winner = new JLabel();

    public InfoPanel()
    {
        // Setting up JPanel
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(new Color(64, 64, 64));
        this.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        this.setLayout(new GridLayout(1,3));

        // Setting up JLabel and adding it to InfoPanel
        xScore.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
        xScore.setForeground(Color.WHITE);
        xScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(xScore);

        // Setting up JLabel and adding it to InfoPanel
        winner.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
        winner.setForeground(Color.WHITE);
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(winner);

        // Setting up JLabel and adding it to InfoPanel
        oScore.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
        oScore.setForeground(Color.WHITE);
        oScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(oScore);
    }

    public void changeWinner(String msg)
    {
        winner.setText(msg);
    }

    public void checkScore(char player)
    {
        // Opposite player chosen because the turn changes after winning move.
        if(player == 'O') xpoints++;
        else opoints++;

        // Updating Score in GUI
        xScore.setText("X Score: "+xpoints);
        oScore.setText("O Score: "+opoints);
    }
}
