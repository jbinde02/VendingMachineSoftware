public class VendMacDemo {
    //This is the main method of the program. Creates a new model and view and passes them to the controller.
    public static void main(String[] args){
        VendMacModel model = new VendMacModel();
        VendMacView view = new VendMacView();
        VendMacController controller = new VendMacController(model, view);
    }
}
