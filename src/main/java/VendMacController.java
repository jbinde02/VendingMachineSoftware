public class VendMacController {
    private VendMacModel model;
    private VendMacView view;

    /*
    Create default controller.
     */
    public VendMacController(){
        System.out.println("Controller Created");
        this.model = new VendMacModel();
        this.view = new VendMacView();
    }

    /*
    Create controller with model and view parameters.
     */
    public VendMacController(VendMacModel model, VendMacView view){
        System.out.println("Controller Created");
        this.model = model;
        this.view = view;
    }
}
