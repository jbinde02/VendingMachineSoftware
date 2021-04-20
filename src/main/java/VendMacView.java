import javax.swing.*;
import java.awt.*;

public class VendMacView {
    JFrame frame;
    JPanel itemsPanel, uiPanel;
    JTextArea itemsArea;
    public VendMacView(){
        //Setting up the frame
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(0,2);
        frame.setLayout(gridLayout);

        //Sets up the two panels
        itemsPanel = new JPanel();
        itemsPanel.setSize(250,250);
        frame.add(itemsPanel);
        uiPanel = new JPanel();
        uiPanel.setSize(250,250);
        frame.add(uiPanel);

        //Sets up itemsPane
        itemsArea = new JTextArea(100, 100);
        itemsArea.setSize(100,100);
        itemsPanel.add(itemsArea);

        frame.setVisible(true);
        System.out.println("View Created");
    }
}
