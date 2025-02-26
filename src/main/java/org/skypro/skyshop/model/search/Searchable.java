package org.skypro.skyshop.model.search;

public interface Searchable {

    String getSearchTerm();

    String getContentType();

    default String getID() {
        return "UUID(java.util.UUID)";
    }


    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getContentType();
    }
}
