package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> products = new LinkedHashMap<>();

    static {
        products.put(1, new Product(
                1, "iPhone 15", 20000000,
                "Dien thoai Apple", "Apple"
        ));

        products.put(2, new Product(
                2, "Galaxy S24", 18000000,
                "Dien thoai Samsung", "Samsung"
        ));

        products.put(3, new Product(
                3, "Xiaomi 14", 15000000,
                "Dien thoai Xiaomi", "Xiaomi"
        ));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getName().toLowerCase()
                    .contains(name.toLowerCase())) {
                result.add(product);
            }
        }

        return result;
    }
}