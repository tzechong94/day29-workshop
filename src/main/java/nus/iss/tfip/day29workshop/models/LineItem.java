package nus.iss.tfip.day29workshop.models;

import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

public class LineItem {
    
    private String itemName;
    private Integer quantity;

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // create a lineItem with the json object passed in from Order
    public static LineItem createJson(JsonObject o){
        LineItem l = new LineItem();
        JsonNumber quantity = o.getJsonNumber("quantity");
        String itemName = o.getString("item");
        l.quantity = quantity.intValue();
        l.itemName = itemName;
        System.out.println("quantity: " + quantity.toString());
        System.out.println("itemname: " + itemName);

        return l;
    }
    
}
