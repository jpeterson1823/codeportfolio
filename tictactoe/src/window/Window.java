package window;

import window.panels.*;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    // Creating objects of InfoPanel and GamePanel
    public InfoPanel infoPanel = new InfoPanel();
    public GamePanel gamePanel = new GamePanel();

    public Window()
    {
        // Setting up Window for GUI
        this.setTitle("TicTacToe!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 562);
        this.setLayout(null);
        this.setResizable(false);
        this.setBackground(new Color(50,50,50));

        // Adding object of InfoPanel and GamePanel to Window and setting their correct size and location
        this.add(infoPanel);
        infoPanel.setLocation(0,0);
        infoPanel.setVisible(true);
        this.add(gamePanel);
        gamePanel.setLocation(0,40);
        gamePanel.setVisible(true);
        this.setVisible(true);
    }
}
