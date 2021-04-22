import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
The view creates the UI. It's only responsibility should be creating bits of UI and updating information as told by the
controller.
 */
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
        mainPanel.setBackground(Color.darkGray);
        frame.add(mainPanel);

        //Item table
        itemTable = new JTable();
        scrollPane = new JScrollPane(itemTable);
        itemTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        scrollPane.setPreferredSize(new Dimension(1200,500));
        scrollPane.getViewport().getView().setBackground(new Color(230, 255, 255));
        scrollPane.getViewport().getView().setForeground(Color.BLACK);
        scrollPane.getViewport().setBackground(new Color(0, 51, 51));
        mainPanel.add(scrollPane);

        //Load Button
        loadButton = new JButton("Load JSON");
        loadButton.setBackground(new Color(255, 255, 230));
        mainPanel.add(loadButton);

        //Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(255, 255, 230));
        mainPanel.add(submitButton);

        //Input field
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(75, 25));
        mainPanel.add(inputField);

        //Status field
        statusField = new JTextField("Status");
        statusField.setEditable(false);
        statusField.setPreferredSize(new Dimension(550, 25));
        statusField.setBackground(new Color(180, 255, 180));
        mainPanel.add(statusField);
    }

    //Sets the table to a new updated model. Then refreshes the components in the frame.
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
