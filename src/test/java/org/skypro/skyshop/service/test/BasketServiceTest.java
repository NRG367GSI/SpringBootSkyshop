package org.skypro.skyshop.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    private UUID productId;
    private Product product;

    @BeforeEach
    public void setUp() {
        productId = UUID.randomUUID();
        product = new SimpleProduct(productId, "Test Product", 100);
    }

    @Test
    public void addProductToBasket_productNotFound_throwsException() {
        when(storageService.getProductById(productId)).thenThrow(new NoSuchProductException("Product not found with id: " + productId));

        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(productId));
    }

    @Test
    public void addProductToBasket_productFound_callsAddProduct() {
        when(storageService.getProductById(productId)).thenReturn(product);

        basketService.addProductToBasket(productId);

        verify(productBasket, times(1)).addProduct(productId);
    }

    @Test
    public void getUserBasket_basketEmpty_returnsEmptyList() {
        when(productBasket.getAllProduct()).thenReturn(Collections.emptyMap());

        List<BasketItem> basketItems = basketService.getUserBasket();

        assertTrue(basketItems.isEmpty());
    }

    @Test
    public void getUserBasket_basketHasItems_returnsCorrectList() {
        Map<UUID, Integer> productMap = new HashMap<>();
        productMap.put(productId, 2);

        when(productBasket.getAllProduct()).thenReturn(productMap);
        when(storageService.getProductById(productId)).thenReturn(product);

        List<BasketItem> basketItems = basketService.getUserBasket();

        assertEquals(1, basketItems.size());
        assertEquals(product, basketItems.get(0).getProduct());
        assertEquals(2, basketItems.get(0).getCountProduct());
    }

    @Test
    public void getUserBasket_multipleItemsInBasket_returnsCorrectList() {
        UUID productId2 = UUID.randomUUID();
        Product product2 = new SimpleProduct(productId2, "Another Product", 200);

        Map<UUID, Integer> productMap = new HashMap<>();
        productMap.put(productId, 2);
        productMap.put(productId2, 3);

        when(productBasket.getAllProduct()).thenReturn(productMap);
        when(storageService.getProductById(productId)).thenReturn(product);
        when(storageService.getProductById(productId2)).thenReturn(product2);

        List<BasketItem> basketItems = basketService.getUserBasket();

        assertEquals(2, basketItems.size());

        assertEquals(product, basketItems.get(0).getProduct());
        assertEquals(2, basketItems.get(0).getCountProduct());

        assertEquals(product2, basketItems.get(1).getProduct());
        assertEquals(3, basketItems.get(1).getCountProduct());
    }
}
