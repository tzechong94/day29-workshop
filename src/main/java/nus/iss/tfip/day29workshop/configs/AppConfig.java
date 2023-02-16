package nus.iss.tfip.day29workshop.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import static nus.iss.tfip.day29workshop.Constants.*;

@Configuration
public class AppConfig {
    
    @Value("${mongo.url}")
    private String mongoUrl;

    @Bean
    public MongoTemplate createSHOWS(){
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, ORDERSDB);
    }

    

}
