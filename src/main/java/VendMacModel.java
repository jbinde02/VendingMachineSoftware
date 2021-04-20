import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

public class VendMacModel {
    private int columns, rows;
    ArrayList<VendMacItem> vendMacItemsArray = new ArrayList<VendMacItem>();

    //Constructor that reads the default JSON input provided located in the resources folder
    public VendMacModel(){
        loadNewJSON("src/main/resources/input.json");
        Iterator iterator = vendMacItemsArray.iterator();

        //Testing the arraylist
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("Model Created");
    }

    //Constructor that takes path to JSON
    public VendMacModel(String path){
        loadNewJSON(path);
        Iterator iterator = vendMacItemsArray.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("Model Created");
    }

    public void loadNewJSON(String path){
        try{
            //Read in the entire JSON file
            Reader reader = new FileReader(path);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject)obj;

            //Set config variables
            JSONObject configValues = (JSONObject)jsonObject.get("config");
            Long columnLong = (Long) configValues.get("columns");
            columns = columnLong.intValue();
            Long rowLong = (Long) configValues.get("rows");
            rows = rowLong.intValue();

            //Populate itemsArray
            JSONArray jsonItems = (JSONArray)jsonObject.get("items");
            for(int i = 0; i < jsonItems.size(); i++){
                JSONObject jsonItem = (JSONObject)jsonItems.get(i);
                VendMacItem item = new VendMacItem();

                item.setName((String)jsonItem.get("name"));

                Long amountLong = (Long)jsonItem.get("amount");
                item.setAmount(amountLong.intValue());

                item.setPrice((String)jsonItem.get("price"));

                vendMacItemsArray.add(item);
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
}

class VendMacItem{
    String name;
    int amount;
    String price;

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

    public void increment(){
        amount++;
    }

    public String toString(){
        return "Name: " + name + " | Amount: " + amount + " | Price: " + price;
    }
}
