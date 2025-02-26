package org.skypro.skyshop.content;

import java.util.Objects;

public final class Article implements Searchable{
    private final String title;
    private final String content;

    public Article(String title, String text) {
        this.title = title;
        this.content = text;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", title, content);
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Article article = (Article) object;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
