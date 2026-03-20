package org.skypro.skyshop.product;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String search) {
        super("Для поискового запроса '" + search + "' не нашлось подходящей статьи");
    }
}
