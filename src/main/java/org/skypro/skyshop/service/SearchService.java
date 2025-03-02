package org.skypro.skyshop.service;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    // Внедрение StorageService через конструктор
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Map<UUID, Product> getProducts() {
        return storageService.getAllProduct();
    }

    public Map<UUID, Article> getArticles() {
        return storageService.getAllArticle();
    }

    // Метод для объединения продуктов и статей в одну коллекцию
    public List<Searchable> combiningCollections() {
        List<Searchable> allSearchable = new ArrayList<>();
        allSearchable.addAll(storageService.getAllProduct().values()); // Добавляем все продукты
        allSearchable.addAll(storageService.getAllArticle().values()); // Добавляем все статьи
        return allSearchable;
    }

    // Метод для поиска по запросу
    public List<Searchable> search(String request) {
        List<Searchable> result = combiningCollections().stream().filter(
                searchable -> searchable.getSearchTerm().toLowerCase().contains(request.toLowerCase())).collect(Collectors.toList());
        return result;
    }
}