package nus.iss.tfip.day29workshop.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {

    private String orderId;

    public Order(){
        this.orderId = generateId(8);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    
    private String name;
    private String email;
    private String deliveryDate;
    private List<LineItem> lineItemsList;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public List<LineItem> getLineItemsList() {
        return lineItemsList;
    }
    public void setLineItemsList(List<LineItem> lineItemsList) {
        this.lineItemsList = lineItemsList;
    }

    public Document toDocument(){
        Document doc = new Document();
        doc.put("name", getName());
        doc.put("email", getEmail());
        doc.put("id", getOrderId());
        doc.put("deliveryDate", getDeliveryDate());
        doc.put("lineItems", getLineItemsList());
        return doc;
    }

    public Order create(String json) throws IOException {
        Order order = new Order();
        List<LineItem> result = new LinkedList<>();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            JsonArray items = o.getJsonArray("lineItems");
            order.setName(o.getString("name"));
            order.setEmail(o.getString("email"));
            order.setDeliveryDate(o.getString("deliveryDate"));

            for (int i= 0;i < items.size(); i++) {
                JsonObject x = items.getJsonObject(i);
                LineItem l = LineItem.createJson(x);
                result.add(l);
            }
            order.lineItemsList = result;
            return order;

        }


    }    

}


