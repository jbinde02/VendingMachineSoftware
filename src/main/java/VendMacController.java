import javax.swing.*;
import java.io.File;

/*
The controller acts as the interface between the view and the model. The controller will give the data to the view
to update and the controller will take actions from the view and update the model.
 */
public class VendMacController {
    private VendMacModel model;
    private VendMacView view;
    private JFileChooser fileChooser;
    private String submission;
    private String selectedItem;
    private int inputState;

    public VendMacController(VendMacModel model, VendMacView view){
        this.model = model;
        this.view = view;
        fileChooser = new JFileChooser();
        inputState = 0;
        updateItemDisplay();
        setActionListeners();

        cycle(); //Begin cycle.
    }

    //Takes the data from the model and gives it to the view to update. Also creates some column names for the table.
    private void updateItemDisplay(){
        String[] columnNames = new String[model.getColumns()];
        for(int i = 0; i<columnNames.length; i++){
            columnNames[i] = String.valueOf(i);
        }
        view.updateItemsDisplay(model.itemMapTo2DArray(model.getVendMacItemMap()), columnNames);
    }

    private void setActionListeners(){
        //Load button
        view.getLoadButton().addActionListener(actionEvent -> {
            int option = fileChooser.showDialog(null, "Choose");
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                model.loadNewJSON(file.getPath());
                updateItemDisplay();
            }
        });

        //Submit button
        view.getSubmitButton().addActionListener(actionEvent -> {
            submission = view.inputField.getText().toUpperCase();
            view.inputField.setText("");
            System.out.println("User submitted - " + submission + ". At " + java.time.LocalDateTime.now());
            cycle();
        });
    }

    /*
    Cycle() represents the cycle the machine goes through. It is called once at the creation of the controller class
    and then every time the submit button is triggered. It starts by asking for a selection and advancing the
    inputState. This takes it to the next case which checks the selection. If the selection is acceptable, it increases
    the inputState which takes it to the next case which asks for payment. This will either fail or succeed. The submit
    button's text is changed to clear and the inputState is reset to 0 where the cycle will begin again.
     */
    private void cycle(){
        switch (inputState){
            case 0:
                view.statusField.setText("Enter selection.");
                inputState++;
                view.submitButton.setText("Submit");
                break;
            case 1:
                selectedItem = submission;
                if(model.isItemAvailable(selectedItem)){
                    view.statusField.setText(selectedItem + " Selected. Enter payment of " + model.getItem(selectedItem).getPrice());
                    inputState++;
                }
                break;
            case 2:
                view.statusField.setText("Payment of " + submission + " received. " + model.attemptPurchase(selectedItem, submission));
                updateItemDisplay();
                view.getSubmitButton().setText("Clear");
                inputState = 0;
                break;
        }

    }
}
