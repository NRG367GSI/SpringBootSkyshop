package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> product;
    private final Map<UUID, Article> article;

    public StorageService() {
        this.product = new HashMap<UUID, Product>();
        this.article = new HashMap<UUID, Article>();

        initTestData();
    }

    public Map<UUID, Product> getAllProduct() {
        return this.product;
    }

    public Map<UUID, Article> getAllArticle() {
        return this.article;
    }

    private void initTestData() {
        Product apple = new DiscountedProduct(UUID.randomUUID(), "Apple", 100, 10);
        Product banana = new SimpleProduct(UUID.randomUUID(), "Banana", 50);
        Product milk = new FixPriceProduct(UUID.randomUUID(), "Milk");
        Product cheese = new DiscountedProduct(UUID.randomUUID(), "Cheese", 200, 20);
        Product yogurt = new FixPriceProduct(UUID.randomUUID(), "Yogurt");
        Product tomato = new DiscountedProduct(UUID.randomUUID(), "Tomato", 40, 10);
        Product potato = new SimpleProduct(UUID.randomUUID(), "Potato", 30);

        product.put(apple.getID(), apple);
        product.put(banana.getID(), banana);
        product.put(milk.getID(), milk);
        product.put(cheese.getID(), cheese);
        product.put(yogurt.getID(), yogurt);
        product.put(tomato.getID(), tomato);
        product.put(potato.getID(), potato);

        // Создаем статьи с описаниями товаров
        Article article1 = new Article(
                UUID.randomUUID(),
                "Яблоки",
                "Свежие яблоки с местных садов. Идеально подходят для перекуса или приготовления десертов."
        );

        Article article2 = new Article(
                UUID.randomUUID(),
                "Бананы",
                "Спелые бананы, богатые калием. Отлично подходят для здорового перекуса."
        );

        Article article3 = new Article(
                UUID.randomUUID(),
                "Молоко",
                "Свежее молоко с местной фермы. Идеально для приготовления каш, кофе и других блюд."
        );

        Article article4 = new Article(
                UUID.randomUUID(),
                "Сыр",
                "Натуральный сыр, изготовленный из коровьего молока. Отлично подходит для бутербродов и закусок."
        );

        Article article5 = new Article(
                UUID.randomUUID(),
                "Йогурт",
                "Натуральный йогурт без добавок. Подходит для завтрака или легкого перекуса."
        );

        // Добавляем статьи в SearchEngine
        this.article.put(article1.getID(), article1);
        this.article.put(article2.getID(), article2);
        this.article.put(article3.getID(), article3);
        this.article.put(article4.getID(), article4);
        this.article.put(article5.getID(), article5);

    }
}



