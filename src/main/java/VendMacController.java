import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VendMacController {
    private VendMacModel model;
    private VendMacView view;
    private JFileChooser fileChooser;

    public VendMacController(VendMacModel model, VendMacView view){
        System.out.println("Controller Created");
        this.model = model;
        this.view = view;
        fileChooser = new JFileChooser();

        view.updateItemsDisplay(model.getVendMacItemMap());
        setActionListeners();
    }

    private void setActionListeners(){
        view.getLoadButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int option = fileChooser.showDialog(null, "Choose");
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    model.loadNewJSON(file.getPath());
                    view.updateItemsDisplay(model.getVendMacItemMap());
                }
            }
        });
    }
}
