package org.model.authors;

public class Slowacki implements Author {

    @Override
    public String getName() {
        return "J. Słowacki";
    }

    @Override
    public String toString() {
        return "Slowacki{" +
            getName() +
            "}";
    }
}
