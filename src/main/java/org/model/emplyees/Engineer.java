package org.model.emplyees;

import org.di.annotations.CInject;
import org.di.annotations.Default;
import org.model.book.Book;

@Default
public class Engineer implements Employee {

    @CInject("Fantasy")
    private Book book;


    private String name;

    public Engineer() {

    }

    public Engineer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
            "name='" + name + '\'' +
            ", book=" + book +
            '}';
    }
}
