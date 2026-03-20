package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ProductBasket {
    private Map<String, List<Product>> products = new HashMap<>();

    public void add(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>())
                .add(product);
    }

    public List<Product> getByName(String name) {
        return products.getOrDefault(name, new ArrayList<>());
    }

    public List<Product> removeByName(String name) {
        List<Product> removed = products.remove(name);
        return removed != null ? removed : new ArrayList<>();
    }

    public boolean contains(String name) {
        return products.containsKey(name);
    }

    public void clear() {
        products.clear();
    }

    // ✅ ИСПРАВЛЕН: mapToInt + sum
    public int getTotalCost() {
        return products.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }


    private boolean isSpecialProduct(Product p) {
        return p.getName().startsWith("Special") || p.getPrice() > 500;
    }


    public void print() {
        products.values().stream()
                .flatMap(Collection::stream)
                .forEach(product -> System.out.println(product));

        System.out.println("Special products count: " + getSpecialCount());
    }


    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(Collection::stream)
                .filter(this::isSpecialProduct)
                .count();
    }
}

