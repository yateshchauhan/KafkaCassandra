package com.services;

import com.bean.Product;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class ProductCaringService {

    private final HashMap<String, Product> productMap  = new HashMap<>();

    public ProductCaringService(){
        System.out.println("ProductCaringService instantiated");
    }

    public Product add(Product product){
        return productMap.put(product.getName(), product);
    }

    public List<Product> getProductList(){
        return productMap.entrySet().stream().map((set)->set.getValue()).collect(Collectors.toList());
    }

    public Product getProduct(String name){
        return productMap.get(name);
    }
}
