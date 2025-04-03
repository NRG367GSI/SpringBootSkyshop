package org.skypro.skyshop.service.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearch_EmptyStorage() {
        when(storageService.getAllProduct()).thenReturn(Collections.emptyMap());
        when(storageService.getAllArticle()).thenReturn(Collections.emptyMap());

        List<Searchable> result = searchService.search("test");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearch_NoMatchingObjects() {
        Map<UUID, Product> products = new HashMap<>();
        Map<UUID, Article> articles = new HashMap<>();

        products.put(UUID.randomUUID(), mock(Product.class));
        articles.put(UUID.randomUUID(), mock(Article.class));

        when(storageService.getAllProduct()).thenReturn(products);
        when(storageService.getAllArticle()).thenReturn(articles);

        List<Searchable> result = searchService.search("test");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearch_MatchingObject() {
        Map<UUID, Product> products = new HashMap<>();
        Map<UUID, Article> articles = new HashMap<>();

        Product product = mock(Product.class);
        when(product.getSearchTerm()).thenReturn("testProduct");
        products.put(UUID.randomUUID(), product);

        when(storageService.getAllProduct()).thenReturn(products);
        when(storageService.getAllArticle()).thenReturn(articles);

        List<Searchable> result = searchService.search("test");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    public void testSearch_CaseInsensitive() {
        Map<UUID, Product> products = new HashMap<>();
        Map<UUID, Article> articles = new HashMap<>();

        Product product = mock(Product.class);
        when(product.getSearchTerm()).thenReturn("testProduct");
        products.put(UUID.randomUUID(), product);

        when(storageService.getAllProduct()).thenReturn(products);
        when(storageService.getAllArticle()).thenReturn(articles);

        List<Searchable> result = searchService.search("TEST");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    public void testSearch_PartialMatch() {
        Map<UUID, Product> products = new HashMap<>();
        Map<UUID, Article> articles = new HashMap<>();

        Product product = mock(Product.class);
        when(product.getSearchTerm()).thenReturn("testProduct");
        products.put(UUID.randomUUID(), product);

        when(storageService.getAllProduct()).thenReturn(products);
        when(storageService.getAllArticle()).thenReturn(articles);

        List<Searchable> result = searchService.search("test");
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }
}