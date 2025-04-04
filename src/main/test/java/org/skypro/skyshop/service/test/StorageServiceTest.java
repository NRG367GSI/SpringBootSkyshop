package org.skypro.skyshop.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.StorageService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StorageServiceTest {

    private StorageService storageService;

    @BeforeEach
    public void setUp() {
        storageService = new StorageService();
    }

    @Test
    public void testGetProductById_EmptyStorage() {
        StorageService emptyStorageService = new StorageService();
        emptyStorageService.getAllProduct().clear();

        UUID nonExistentId = UUID.randomUUID();
        assertThrows(NoSuchProductException.class, () -> emptyStorageService.getProductById(nonExistentId));
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        assertThrows(NoSuchProductException.class, () -> storageService.getProductById(nonExistentId));
    }

    @Test
    public void testGetProductById_ProductFound() {
        UUID existingId = storageService.getAllProduct().keySet().iterator().next();
        Product product = storageService.getProductById(existingId);
        assertNotNull(product);
        assertEquals(existingId, product.getIdProduct());
    }

    @Test
    public void testGetAllProductsStream() {
        storageService.getAllProduct().keySet().stream()
                .forEach(productId -> {
                    Product product = storageService.getProductById(productId);
                    assertNotNull(product);
                    assertEquals(productId, product.getIdProduct());
                });
    }
}