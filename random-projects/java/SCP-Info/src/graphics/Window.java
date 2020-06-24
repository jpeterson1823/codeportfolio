package graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Dimension;

public class Window extends JFrame {
    private JPanel infoPanel;
    private JLabel itemNumberL;
    private JLabel itemNumber;
    private JLabel scpClassL;
    private JLabel scpClass;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextPane infoPane;

    public Window(InfoNabber nabber) {

        super();
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800,800));

        String scpPageInfo[] = nabber.getItemNumberAndClassThenFormatData(nabber.getSite("http://scp-wiki.net/scp-028"));

        itemNumber.setText(scpPageInfo[0]);
        scpClass.setText(scpPageInfo[1]);

        infoPane.setContentType("text/html");
        infoPane.setText(scpPageInfo[2]);

        this.setVisible(true);

    }

}
