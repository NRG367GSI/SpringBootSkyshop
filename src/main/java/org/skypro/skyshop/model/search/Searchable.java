package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String getSearchTerm();

    String getContentType();

    UUID getID();


    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getContentType();
    }
}
