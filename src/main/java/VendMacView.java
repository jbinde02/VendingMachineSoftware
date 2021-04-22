import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VendMacView {
    JFrame frame;
    JPanel mainPanel;
    JButton submitButton, loadButton;
    JTextField inputField, statusField;
    JTable itemTable;
    JScrollPane scrollPane;

    public VendMacView(){
        //Setting up the frame
        frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250,750);
        frame.setVisible(true);

        //Main panel
        mainPanel = new JPanel();
        frame.add(mainPanel);

        //Item table
        itemTable = new JTable();
        scrollPane = new JScrollPane(itemTable);
        itemTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        scrollPane.setPreferredSize(new Dimension(1200,500));
        mainPanel.add(scrollPane);

        //Load Button
        loadButton = new JButton("Load JSON");
        mainPanel.add(loadButton);

        //Submit Button
        submitButton = new JButton("Submit");
        mainPanel.add(submitButton);

        //Input field
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(75, 25));
        mainPanel.add(inputField);

        //Status field
        statusField = new JTextField("Status");
        statusField.setEditable(false);
        statusField.setPreferredSize(new Dimension(550, 25));
        mainPanel.add(statusField);
    }

    public void updateItemsDisplay(VendMacItem[][] vendMacItem2D, String[] columnNames){

        itemTable.setModel(new DefaultTableModel(vendMacItem2D, columnNames));
        SwingUtilities.updateComponentTreeUI(frame);
    }


    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
