package graphics;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    private JLabel dayLabel = new JLabel("Days: undefined");
    private JLabel popLabel = new JLabel("Population: undefined");
    private JLabel initPopLabel = new JLabel("Initial Population:");
    private JTextField initPopField = new JTextField("1");
    private JTextArea eData = new JTextArea("test");
    private Font FONT;
    private Font EFONT;
    private JLabel errorMesg = new JLabel("Input must be a positive number!");

    public JButton start = new JButton("Start");
    public JButton end = new JButton("End Simulation");

    public Window()
    {
        super();

        String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0)
        {
            FONT = new Font("Lato", Font.PLAIN,15);
            EFONT = new Font("Helvetica", Font.PLAIN,4);
        }
        else
        {
            FONT = new Font("Helvetica",Font.PLAIN,15);
            EFONT = new Font("Helvetica",Font.PLAIN,4);
        }

        this.setSize(910,810);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Simulation");

        eData.setFont(EFONT);
        eData.setBackground(new Color(65,65,65));
        eData.setForeground(Color.WHITE);
        eData.setEditable(false);
        eData.setBorder(BorderFactory.createEtchedBorder());

        dayLabel.setForeground(Color.WHITE);
        dayLabel.setFont(FONT);

        popLabel.setForeground(Color.WHITE);
        popLabel.setFont(FONT);

        initPopLabel.setForeground(Color.WHITE);
        initPopLabel.setFont(FONT);

        initPopField.setForeground(Color.WHITE);
        initPopField.setBackground(new Color(100,100,100));
        initPopField.setBorder(null);
        initPopField.setHorizontalAlignment(JTextField.CENTER);
        initPopField.setFont(FONT);

        start.setForeground(Color.WHITE);
        start.setBackground(new Color(100,100,100));
        start.setFont(FONT);
        if (os.compareTo("win") < 0) start.setBorder(BorderFactory.createEtchedBorder());

        end.setForeground(Color.WHITE);
        end.setBackground(new Color(100,100,100));
        end.setFont(FONT);
        if (os.compareTo("win") < 0) end.setBorder(BorderFactory.createEtchedBorder());

        errorMesg.setForeground(new Color(65,65,65));
        errorMesg.setFont(FONT);


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5,5,5,5);
        c.weightx = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 120;
        c.gridheight = 50;
        c.anchor = GridBagConstraints.NORTHWEST;
        panel.add(dayLabel,c);

        c.gridx = 0;
        c.gridy = 50;
        c.gridwidth = 120;
        c.gridheight = 50;
        c.anchor = GridBagConstraints.WEST;
        panel.add(popLabel,c);

        c.gridx = 0;
        c.gridy = 100;
        c.gridwidth = 150;
        c.gridheight = 50;
        c.anchor = GridBagConstraints.NORTHWEST;
        panel.add(initPopLabel,c);

        c.gridx = 0;
        c.gridy = 150;
        c.gridwidth = 50;
        c.gridheight = 50;
        c.anchor = GridBagConstraints.NORTHWEST;
        panel.add(initPopField,c);

        c.gridx = 50;
        c.gridy = 150;
        c.gridwidth = 50;
        c.gridheight = 50;
        c.anchor = GridBagConstraints.NORTHWEST;
        panel.add(start,c);

        c.gridx = 0;
        c.gridy = 200;
        c.gridwidth = 100;
        c.gridheight = 50;
        c.anchor = GridBagConstraints.NORTHWEST;
        panel.add(end,c);

        c.gridx = 120;
        c.gridy = 0;
        c.gridwidth = 800;
        c.gridheight = 800;
        c.anchor = GridBagConstraints.EAST;
        panel.add(eData,c);


        panel.setBackground(new Color(65,65,65));

        this.add(panel);
        this.setVisible(true);
    }

    public void updateInfo(int day, int population, String str)
    {
        dayLabel.setText("Day: "+day);
        popLabel.setText("Population: "+population);
        eData.setText(str);
    }

    public int getInput()
    {
        try
        {
            String input = initPopField.getText();
            return Integer.parseInt(input);
        }
        catch(Exception e)
        {
            return -1;
        }
    }
}
