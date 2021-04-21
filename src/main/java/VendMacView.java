import javax.swing.*;
import java.util.Map;

public class VendMacView {
    JFrame frame;
    JPanel mainPanel;
    JButton submitButton, loadButton;
    JTextArea itemsArea;
    JTextField inputField, statusField;

    public VendMacView(){
        //Setting up the frame
        frame = new JFrame("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setVisible(true);

        mainPanel = new JPanel();
        frame.add(mainPanel);

        //Items display area
        itemsArea = new JTextArea("",10,10);
        itemsArea.setEditable(false);
        mainPanel.add(itemsArea);

        //Submit Button
        submitButton = new JButton("Submit");
        mainPanel.add(submitButton);

        //Load Button
        loadButton = new JButton("Load JSON");
        mainPanel.add(loadButton);

        //Input field
        inputField = new JTextField("Input");
        mainPanel.add(inputField);

        //Status field
        statusField = new JTextField("Status");
        statusField.setEditable(false);
        mainPanel.add(statusField);

        System.out.println("View Created");
    }

    public void updateItemsDisplay(Map<String, VendMacItem> vendMacItemMap){
        itemsArea.setText("");
        for(Map.Entry<String, VendMacItem> item : vendMacItemMap.entrySet()){
            itemsArea.append(item.getValue() + " | " + item.getKey() + "\n");
        }
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
