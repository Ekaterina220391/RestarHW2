package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.article.Article;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) {
        demoHomework1();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demoHomework2();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demoHomework3();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demoHomework4();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demoHomework5();
        System.out.println("\n" + "=".repeat(50) + "\n");
        demoHomework6();
    }

    public static void demoHomework1() {
        System.out.println("=== ДОМАШКА 1 ===");
        ProductBasket basket = new ProductBasket();

        // Добавление
        basket.add(new SimpleProduct("Хлеб", 50));
        basket.add(new SimpleProduct("Молоко", 100));

        // Печать
        basket.print();

        // Стоимость
        System.out.println("Стоимость: " + basket.getTotalCost());

        // Поиск
        System.out.println("Есть Хлеб: " + basket.contains("Хлеб"));
        System.out.println("Есть Пиво: " + basket.contains("Пиво"));

        // Очистка
        basket.clear();
        basket.print();
        System.out.println("Стоимость: " + basket.getTotalCost());
        System.out.println("Есть Хлеб: " + basket.contains("Хлеб"));
    }

    public static void demoHomework2() {
        System.out.println("=== ДОМАШКА 2 ===");
        ProductBasket basket = new ProductBasket();

        basket.add(new SimpleProduct("Хлеб", 50));
        basket.add(new DiscountedProduct("Молоко", 100, 20));
        basket.add(new FixPriceProduct("Абонемент"));

        basket.print();
    }

    public static void demoHomework3() {
        System.out.println("=== ДОМАШКА 3 ===");
        SearchEngine engine = new SearchEngine();

        // Товары
        engine.add(new SimpleProduct("Хлеб", 50));
        engine.add(new SimpleProduct("Молоко", 100));

        // Статьи
        engine.add(new Article("О молоке", "Молоко полезно для здоровья"));
        engine.add(new Article("Хлебушек", "Свежий хлеб каждый день"));

        // Поиск
        List<Searchable> results = engine.search("молок");
        System.out.println("Найдено: " + results.size());
        for (Searchable item : results) {
            System.out.println(item.getStringRepresentation());
        }
    }

    public static void demoHomework4() {
        System.out.println("=== ДОМАШКА 4 ===");

        // Тест исключений
        testProductException();

        SearchEngine engine = new SearchEngine();
        engine.add(new Article("Молоко", "Молоко полезно"));
        engine.add(new SimpleProduct("Молоко", 100));

        // Best search
        try {
            Searchable best = engine.findBest("Молоко");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            engine.findBest("Неизвестно");
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void demoHomework5() {
        System.out.println("=== ДОМАШКА 5 ===");
        ProductBasket basket = new ProductBasket();

        basket.add(new SimpleProduct("Хлеб", 50));
        basket.add(new SimpleProduct("Хлеб", 60));
        basket.add(new SimpleProduct("Молоко", 100));

        basket.print();

        // Удаление
        List removed = basket.removeByName("Хлеб");
        System.out.println("Удалено Хлеб: " + removed.size());
        for (Object product : removed) {
            System.out.println(product);
        }
        basket.print();

        // Удаление несуществующего
        removed = basket.removeByName("Пиво");
        System.out.println("Удалено Пиво: " + removed.size() + " (Список пуст)");
        basket.print();

        // Поиск теперь возвращает все
        SearchEngine engine = new SearchEngine();
        engine.add(new SimpleProduct("яблоко", 100));
        engine.add(new SimpleProduct("банан", 150));
        List<Searchable> results = engine.search("ан");
        System.out.println("Все результаты поиска: " + results.size());
    }

    public static void demoHomework6() {
        System.out.println("=== ДОМАШКА 6 ===");
        SearchEngine engine = new SearchEngine();
        engine.add(new SimpleProduct("яблоко", 100));
        engine.add(new SimpleProduct("банан", 150));
        engine.add(new SimpleProduct("Апельсин", 200));

        TreeMap<String, Searchable> results = engine.searchMap("ан");
        System.out.println("Поиск TreeMap (алфавитный порядок):");
        for (Map.Entry<String, Searchable> entry : results.entrySet()) {
            System.out.println(entry.getValue().getStringRepresentation());
        }

        ProductBasket basket = new ProductBasket();
        basket.add(new SimpleProduct("Молоко", 100));
        basket.add(new SimpleProduct("Молоко", 120));
        basket.add(new SimpleProduct("Хлеб", 50));
        basket.print();
    }

    private static void testProductException() {
        try {
            new SimpleProduct("", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }

        try {
            new SimpleProduct("Хлеб", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }

        try {
            new DiscountedProduct("Молоко", 0, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }

        try {
            new DiscountedProduct("Молоко", 100, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}

