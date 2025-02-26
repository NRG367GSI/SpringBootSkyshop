package org.skypro.skyshop.content;

public interface Searchable {

    String getSearchTerm();

    String getContentType();

    //String getTitle();


    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getContentType();
    }
}
