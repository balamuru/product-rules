package com.vgb.prules.demo;

import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import com.vgb.prules.demo.seller.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class DemoApplication {
    @Autowired
    ProductRepository productRepository;

    public void init() {
        //clear
        productRepository.getProducts().clear();

        //add some dummy data
        productRepository.addProduct(new Product(null, new HashMap<>() {{
            put("name", new StringAttribute("name", "Nike TShirt"));
            put("type", new StringAttribute("type", "Clothing"));
            put("color", new StringAttribute("color", "Blue"));
            put("weight", new NumberAttribute("weight", 5));
            put("cost", new NumberAttribute("cost", 17.75f));
            put("qty", new NumberAttribute("qty", 1000));
        }}));

        productRepository.addProduct(new Product(null, new HashMap<>() {{
            put("name", new StringAttribute("name", "Reebok Shoe"));
            put("type", new StringAttribute("type", "Footwear"));
            put("color", new StringAttribute("color", "Blue"));
            put("weight", new NumberAttribute("weight", 8));
            put("cost", new NumberAttribute("cost", 10));
            put("qty", new NumberAttribute("qty", 2000));
        }}));

        productRepository.addProduct(new Product(null, new HashMap<>() {{
            put("name", new StringAttribute("name", "Golden Goose Feather"));
            put("type", new StringAttribute("type", "Misc"));
            put("color", new StringAttribute("color", "Yellow"));
            put("weight", new NumberAttribute("weight", 0.1f));
            put("cost", new NumberAttribute("cost", 100));
            put("qty", new NumberAttribute("qty", 1));
        }}));

    }


    public void run() {
//        productRepository.getProducts().forEach(product -> System.err.println(product));


        Collection<Product> products = productRepository.getProducts();

        for (Product product : products) {
            String productId = product.getId();


        }

    }



}
