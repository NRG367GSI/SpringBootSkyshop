package org.skypro.skyshop.model.search;

public class BestResultNotFound extends Exception{
    public BestResultNotFound(String searchQuery) {
        super("Не удалось найти подходящий объект для поискового запроса: " + searchQuery);
    }
}
