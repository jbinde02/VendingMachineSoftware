public class VendMacController {
    private VendMacModel model;
    private VendMacView view;

    public VendMacController(VendMacModel model, VendMacView view){
        System.out.println("Controller Created");
        this.model = model;
        this.view = view;
        System.out.println(model.attemptPurchase("A0", "1.35")); //Test
    }
}
