package org.skypro.skyshop.content;

public class BestResultNotFound extends Exception{
    public BestResultNotFound(String searchQuery) {
        super("Не удалось найти подходящий объект для поискового запроса: " + searchQuery);
    }
}
