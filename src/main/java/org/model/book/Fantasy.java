package org.model.book;

import org.di.annotations.CInject;
import org.model.authors.Author;

public class Fantasy implements Book {

    private Author author;

    public Fantasy() {

    }

    @CInject
    public Fantasy(Author author) {
        this.author = author;
    }

    @Override
    public String getTitle() {
        return "Fantasy 1";
    }

    @Override
    public String toString() {
        return "Fantasy{" +
            "author=" + author +
            ", title=" + getTitle() +
            '}';
    }
}
