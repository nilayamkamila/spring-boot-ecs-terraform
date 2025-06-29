package org.example.rest.controller;


import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.rest.models.Item;
import org.example.rest.models.Purchase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class PurchaseController {
    private static final Logger logger = LogManager.getLogger(PurchaseController.class);

    @GetMapping("/hello")
    public String hello() {
        logger.info("Received request for /hello endpoint :{}",
                System.currentTimeMillis() / 1000L);
        return "Hello from Spring Boot on ECS!";
    }

    @GetMapping("/purchase")
    public Purchase getFakePurchase() {
        final Faker faker = new Faker();
        final Random random = new Random();
        Purchase purchase = new Purchase();
        purchase.setPurchaseId("TXN-" + faker.number().digits(6));
        purchase.setCustomerName(faker.name().fullName());
        purchase.setDate(faker.date().birthday().toInstant().toString());

        List<Item> items = new ArrayList<>();
        int itemCount = random.nextInt(3) + 2; // 2 to 4 items

        double total = 0.0;

        for (int i = 0; i < itemCount; i++) {
            Item item = new Item();
            item.setName(faker.commerce().productName());
            item.setQuantity(random.nextInt(5) + 1); // 1 to 5
            item.setPrice(Double.parseDouble(faker.commerce().price()));

            items.add(item);
            total += item.getQuantity() * item.getPrice();
        }

        purchase.setItems(items);
        purchase.setTotalAmount(Math.round(total * 100.0) / 100.0); // round to 2 decimals

        return purchase;
    }
}

