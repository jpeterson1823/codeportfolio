package window.panels;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener
{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    // Keeps track of buttons and states
    public JButton[][] board = new JButton[3][3];

    // Keeps track of marks on board
    private char[][] marks = new char[3][3];

    // Keeps track of player turn
    public char player = 'X';

    public GamePanel()
    {
        this.setSize(WIDTH,HEIGHT);
        this.setLayout(new GridLayout(3,3));
        //this.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED);
        this.setBackground(new Color(64, 64, 64));
        boardInit();
    }

    // Creates buttons and adds them to board array, then adds the array to GamePanel.
    private void boardInit()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                board[i][j] = new JButton("");
                board[i][j].addActionListener(this);
                board[i][j].setForeground(Color.WHITE);
                board[i][j].setBackground(new Color(50,50,50));
                board[i][j].setOpaque(true);
                board[i][j].setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
                board[i][j].setFont(new Font("Skia", Font.BOLD, 80));
                this.add(board[i][j]);

                marks[i][j] = '\0';
            }
        }
    }

    // Checks for all win conditions but does not check for cat
    public boolean won()
    {
        if(marks[0][0] != '\0' && marks[0][1] != '\0' && marks[0][2] != '\0' && marks[0][0] == marks[0][1] && marks[0][1] == marks[0][2])
        {
            return true;
        }
        else if(marks[1][0] != '\0' && marks[1][1] != '\0' && marks[1][2] != '\0' && marks[1][0] == marks[1][1] && marks[1][1] == marks[1][2])
        {
            return true;
        }
        else if(marks[2][0] != '\0' && marks[2][1] != '\0' && marks[2][2] != '\0' && marks[2][0] == marks[2][1] && marks[2][1] == marks[2][2])
        {
            return true;
        }
        else if(marks[0][0] != '\0' && marks[1][0] != '\0' && marks[2][0] != '\0' && marks[0][0] == marks[1][0] && marks[1][0] == marks[2][0])
        {
            return true;
        }
        else if(marks[0][1] != '\0' && marks[1][1] != '\0' && marks[2][1] != '\0' && marks[0][1] == marks[1][1] && marks[1][1] == marks[2][1])
        {
            return true;
        }
        else if(marks[0][2] != '\0' && marks[1][2] != '\0' && marks[2][2] != '\0' && marks[0][2] == marks[1][2] && marks[1][2] == marks[2][2])
        {
            return true;
        }
        else if(marks[0][0] != '\0' && marks[1][1] != '\0' && marks[2][2] != '\0' && marks[0][0] == marks[1][1] && marks[1][1] == marks[2][2])
        {
            return true;
        }
        else if(marks[2][0] != '\0' && marks[1][1] != '\0' && marks[0][2] != '\0' && marks[2][0] == marks[1][1] && marks[1][1] == marks[0][2])
        {
            return true;
        }
        else return false;
    }

    // Checks for cat but does not check for win
    public boolean cat()
    {
        for(int i = 0; i < marks.length; i++)
        {
            for(int j = 0; j < marks.length; j++)
            {
                if(marks[i][j] == '\0') return false;
            }
        }
        if(!won()){
            if(player == 'X') player = 'O';
            else player = 'X';
            return true;
        }
        else return false;
    }

    // Sets board to original state with all buttons blank and all values of mark set to null characters
    public boolean clearBoard()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                board[i][j].setText("");
                marks[i][j] = '\0';
            }
        }
        return true;
    }

    // Returns the current players turn
    public char getPlayer()
    {
        return player;
    }

    // Action Listener
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if(e.getSource() == board[i][j] && marks[i][j] == '\0')
                {
                    marks[i][j] = player;
                    board[i][j].setText(Character.toString(player));
                    if(player == 'X') player = 'O';
                    else player = 'X';
                }
            }
        }
    }
}
