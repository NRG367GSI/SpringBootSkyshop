package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getID().toString(), searchable.getSearchTerm(), searchable.getContentType());
    }
}
