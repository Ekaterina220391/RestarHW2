package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;

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

    public int getTotalCost() {
        int total = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }

    public void print() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product);
            }
        }
        System.out.println("Итого: " + getTotalCost());

        long specialCount = products.values().stream()
                .flatMap(List::stream)
                .mapToLong(p -> p.isSpecial() ? 1 : 0)
                .sum();
        System.out.println("Специальных товаров: " + specialCount);
    }
}

