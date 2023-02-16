package nus.iss.tfip.day29workshop.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.day29workshop.models.Order;
import static nus.iss.tfip.day29workshop.Constants.*;

@Repository
public class OrderRepo {
    
    // send order to mongodb
    @Autowired
    private MongoTemplate template;

    // db.orders.insertOne({
    //     deliveryDate: "2023-02-08",
    //     email: "asdf@asdf.com",
    //     lineItems: [{item: "milo", quantity: 2}],
    //     name: "asdf"}
    // )
    
    public void sendOrder(Order order) {
        Document doc = order.toDocument();
        template.insert(doc, COLLECTION_ORDERS);
    }
}
