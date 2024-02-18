package com.example.service.scheduled;


import com.example.service.model.Color;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductUpdateTask {
    private List<Color> colors;

    public ProductUpdateTask() {
        colors = new ArrayList<>();
    }

    @Scheduled(fixedRate = 60000)
    public void updateProductStock() {
        System.out.println("Check quantity successfully");
        for (Color color : colors) {
            int quantity = color.getQuantity();
            boolean inStock = quantity > 0;
            color.setInStock(inStock);
        }
    }
}
