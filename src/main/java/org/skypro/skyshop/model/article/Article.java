package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String title;
    private final String content;
    private final UUID id;

    public Article(UUID id, String title, String text) {
        this.title = title;
        this.content = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s", title, content);
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
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

    public UUID getID() {
        return this.id;
    }
}
