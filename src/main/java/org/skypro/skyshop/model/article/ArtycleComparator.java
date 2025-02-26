package org.skypro.skyshop.content;

import java.util.Comparator;

public class ArtycleComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable o1, Searchable o2) {

        int lengthComparison = Integer.compare(o1.getSearchTerm().length(), o2.getSearchTerm().length());
        // Если длины разные, возвращаем результат сравнения длин
        if (lengthComparison != 0) {
            return -lengthComparison; // Отсортируем от самой длинной до самой короткой
        }

        // Если длины одинаковые, сравниваем сами имена
        return o1.getSearchTerm().compareTo(o2.getSearchTerm());
    }
}
