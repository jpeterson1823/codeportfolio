import javax.swing.*;
import java.awt.*;

public class AlertWindow extends JFrame {
    private final int CENTER_X = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-150;
    private final int CENTER_Y = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-400;
    public AlertWindow(){
        this.setBounds(CENTER_X,CENTER_Y,300,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }
}
