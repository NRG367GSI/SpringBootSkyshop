package org.skypro.skyshop.content;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set <Searchable> searchables;

    public SearchEngine() {
        this.searchables = new HashSet<>();
    }

    public Set<Searchable> search(String searchTerm) {
        Set <Searchable> results = new TreeSet<>(new ArtycleComparator());
        for (Searchable term : searchables) {
            if (term != null && term.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(term);
            }
        }
        return results;
    }

    public void addSearchable(Searchable searchable) {
        searchables.add(searchable);
    }

    public void printSearchable() {
        for (Searchable sc : searchables) {
            if (sc != null) {
                System.out.println(sc);
            }
        }
    }

    public Searchable substringSearch(String search) throws BestResultNotFound {
        if (search == null || search.isEmpty()) {
            throw new IllegalArgumentException("Поисковая строка не может быть пустой или null");
        }

        String subString = search.toLowerCase(Locale.ROOT);

        TreeSet<Searchable> resultSet = searchables.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingInt((Searchable product) -> {
                    String term = product.getSearchTerm().toLowerCase();
                    int count = 0;
                    int index = 0;
                    while ((index = term.indexOf(subString, index)) != -1) {
                        count++;
                        index += subString.length();
                    }
                    return count;
                })
                        .reversed()
                        .thenComparing(Searchable::getSearchTerm)
                )));
        if (resultSet.isEmpty()) {
            throw new BestResultNotFound(search);
        }

        return resultSet.first();
    }
}
