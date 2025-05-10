package org.skypro.skyshop.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        storageService = new StorageService();
        searchService = new SearchService(storageService);
    }

    @Test
    public void testListContainsAllProductsAndArticles() {
        List<Searchable> result = searchService.combiningCollections();
        Map<UUID, Product> products = storageService.getAllProduct();
        Map<UUID, Article> articles = storageService.getAllArticle();

        products.values().forEach(product -> {
            assertTrue(result.contains(product));
            assertNotNull(product);
            assertTrue(result.get(result.indexOf(product)) instanceof Product);
        });

        articles.values().forEach(article -> {
            assertTrue(result.contains(article));
            assertNotNull(article);
            assertTrue(result.get(result.indexOf(article)) instanceof Article);
        });
    }

    @Test
    public void testListSizeMatchesProductAndArticleSizes() {
        List<Searchable> result = searchService.combiningCollections();
        int productSize = searchService.getProducts().size();
        int articleSize = searchService.getArticles().size();
        assertEquals(productSize + articleSize, result.size());
    }

    @Test
    public void testListContainsNoDuplicates() {
        List<Searchable> result = searchService.combiningCollections();
        Set<Searchable> set = new HashSet<>(result);
        assertEquals(result.size(), set.size());
    }

    @Test
    public void testProductAndArticleListsAreNotEmpty() {
        int productSize = searchService.getProducts().size();
        int articleSize = searchService.getArticles().size();
        assertTrue(productSize > 0, "Список продуктов должен содержать элементы");
        assertTrue(articleSize > 0, "Список статей должен содержать элементы");
    }

}
