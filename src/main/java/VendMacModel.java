import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/*
The model is the datacenter of the application. It takes in outside data and stores it. It is also responsible for
manipulating the data and for returning data when queried.
 */
public class VendMacModel {
    private int columns, rows;
    //Map of the items. Key represents the location in the vending machine
    private Map<String, VendMacItem> vendMacItemMap = new HashMap<>();

    //Constructor that reads the default JSON input provided located in the resources folder
    public VendMacModel(){
        InputStream stream = getClass().getResourceAsStream("input.json");
        loadNewJSON(stream);
    }

    //This loadNewJSON version takes a path to the file
    public void loadNewJSON(String path){
        try{
            //Read in the entire JSON file
            Reader reader = new FileReader(path);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;
            reader.close();

            //Set config variables
            JSONObject configValues = (JSONObject)jsonObject.get("config");
            Long columnLong = (Long) configValues.get("columns");
            columns = columnLong.intValue();
            Long rowLong = (Long) configValues.get("rows");
            rows = rowLong.intValue();

            //Populate vendMacItemsMap. Creates key value which represents the location in the vending machine
            JSONArray jsonItems = (JSONArray)jsonObject.get("items");
            vendMacItemMap.clear();

            char rowValue = 'A';
            int columnValue = 0;
            for(int i = 0; i < jsonItems.size(); i++){
                JSONObject jsonItem = (JSONObject)jsonItems.get(i);
                VendMacItem item = new VendMacItem();

                item.setName((String)jsonItem.get("name"));
                Long amountLong = (Long)jsonItem.get("amount");
                item.setAmount(amountLong.intValue());
                item.setPrice((String)jsonItem.get("price"));

                //Check if at end of column. If so, advance a row and reset column index to 0
                if (columnValue == columns) {
                    columnValue = 0;
                    rowValue++;
                    if(rowValue - 65 == rows){ //65 is the ASCII value for 'A'. 65 - 'A' would represent the first index of the rows.
                        System.out.println("Maximum size reached!");
                        break;
                    }
                }
                String keyValue =  rowValue + "" + columnValue;
                columnValue++;
                vendMacItemMap.put(keyValue, item);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //This loadNewJSON version takes a input stream of the file
    public void loadNewJSON(InputStream stream){
        try{
            //Read in the entire JSON file
            Reader reader = new InputStreamReader(stream);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;
            reader.close();

            //Set config variables
            JSONObject configValues = (JSONObject)jsonObject.get("config");
            Long columnLong = (Long) configValues.get("columns");
            columns = columnLong.intValue();
            Long rowLong = (Long) configValues.get("rows");
            rows = rowLong.intValue();

            //Populate vendMacItemsMap. Creates key value which represents the location in the vending machine
            JSONArray jsonItems = (JSONArray)jsonObject.get("items");
            vendMacItemMap.clear();

            char rowValue = 'A';
            int columnValue = 0;
            for(int i = 0; i < jsonItems.size(); i++){
                JSONObject jsonItem = (JSONObject)jsonItems.get(i);
                VendMacItem item = new VendMacItem();

                item.setName((String)jsonItem.get("name"));
                Long amountLong = (Long)jsonItem.get("amount");
                item.setAmount(amountLong.intValue());
                item.setPrice((String)jsonItem.get("price"));

                //Check if at end of column. If so, advance a row and reset column index to 0
                if (columnValue == columns) {
                    columnValue = 0;
                    rowValue++;
                    if(rowValue - 65 == rows){ //65 is the ASCII value for 'A'. 65 - 'A' would represent the first index of the rows.
                        System.out.println("Maximum size reached!");
                        break;
                    }
                }
                String keyValue =  rowValue + "" + columnValue;
                columnValue++;
                vendMacItemMap.put(keyValue, item);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public VendMacItem getItem(String location){
        return vendMacItemMap.get(location);
    }

    public boolean isItemAvailable(String location){
        return vendMacItemMap.get(location) != null;
    }

    /*
    Takes a location and payment amount and attempts to buy and item. This method writes to the console acting as a 'log'
    and it returns a string that will be used to inform the user of what is happening. It will also remove one amount from
    the item if successful.
     */
    public String attemptPurchase(String location, String inputPayment){
        VendMacItem selectedItem = getItem(location);
        BigDecimal selectedItemPrice = new BigDecimal(selectedItem.getPrice().substring(1)); //Substring gets rid of dollar sign
        BigDecimal payment = new BigDecimal(inputPayment);
        BigDecimal result = selectedItemPrice.subtract(payment);
        if(result.signum() == 1){
            System.out.println("Purchase of item in " + location + " failed. Insufficient payment of " + payment + ". At " + java.time.LocalDateTime.now());
            return "This item cost " + result.toString() + " more: Invalid purchase amount. Purchase failed!";
        }else if(selectedItem.getAmount() <= 0){
            System.out.println("Purchase of item in " + location + " failed. Insufficient amount of item. At " + java.time.LocalDateTime.now());
            return "Invalid quantity of selected item. Purchase failed!";
        }else{
            selectedItem.decrement();
            System.out.println("Purchase of item in " + location + " successful of " + payment + ". At " + java.time.LocalDateTime.now());
            return "Purchase successful. "+ result.abs().toString() + " in change. Enjoy your " + selectedItem.getName() + "!";
        }
    }

    public Map<String, VendMacItem> getVendMacItemMap(){
        return vendMacItemMap;
    }

    //Turns the item map into a 2D array which is used by the view to set the JTable.
    public VendMacItem[][] itemMapTo2DArray(Map<String, VendMacItem> itemMap){
        VendMacItem[][] vendMacItem2DArray = new VendMacItem[getRows()][getColumns()];
        for(int i = 0; i < vendMacItem2DArray.length; i++){
            for(int j = 0; j < vendMacItem2DArray[0].length; j++){
                vendMacItem2DArray[i][j] = itemMap.get((char)(i + 65) + "" + j);
            }
        }
        return  vendMacItem2DArray;
    }
}

/*
This class is a representation of a item in the vending machine. It contains the items name, price, and amount left.
 */
class VendMacItem{
    private String name;
    private int amount;
    private String price;

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getPrice() {
        return price;
    }

    public void decrement(){
        amount--;
    }

    public String toString(){
        return name + " | " + price + " | " + amount;
    }
}
