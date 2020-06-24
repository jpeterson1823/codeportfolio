import window.*;

public class Main
{
    public static void main(String args[])
    {
        // Creating object of Window
        Window game = new Window();

        // Constant loop to check for win/cat condition(s)
        while(true)
        {
            // Checking for win, then restarting game if returned true
            if(game.gamePanel.won())
            {
                game.infoPanel.changeWinner("Player "+game.gamePanel.player+" Wins!");
                try
                {
                    // Pausing game for effect
                    Thread.sleep(1000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                game.gamePanel.clearBoard();
                game.infoPanel.checkScore(game.gamePanel.getPlayer());
                game.infoPanel.changeWinner("");
            }
            // Checking for cat, then restarting game if returned true
            else if(game.gamePanel.cat())
            {
                try
                {
                    // Pausing game for effect
                    Thread.sleep(500);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                game.gamePanel.clearBoard();
            }
        }
    }
}
