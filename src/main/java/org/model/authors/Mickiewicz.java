package org.model.authors;

import org.di.annotations.Default;

@Default
public class Mickiewicz implements Author {
    @Override
    public String getName() {
        return "A. Mickiewicz";
    }

    @Override
    public String toString() {
        return "Mickiewicz{" +
            getName() +
            "}";
    }
}
