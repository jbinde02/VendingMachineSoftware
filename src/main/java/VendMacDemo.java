public class VendMacDemo {
    public static void main(String[] args){
        VendMacModel model = new VendMacModel();
        VendMacView view = new VendMacView();
        VendMacController controller = new VendMacController(model, view);
    }
}
