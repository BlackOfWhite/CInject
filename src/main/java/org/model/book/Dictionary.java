package org.model.book;

import org.di.annotations.CInject;
import org.model.authors.Author;

public class Dictionary implements Book {

    @CInject("Mickiewicz")
    private Author author;

    @Override
    public String getTitle() {
        return "Dictionary 1";
    }

    @Override
    public String toString() {
        return "Dictionary{" +
            "author=" + author +
            ", title=" + getTitle() +
            '}';
    }
}
