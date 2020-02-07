package org.model;

import org.di.annotations.CInject;
import org.model.book.Book;

public class Student implements User {

    private String name;

    @CInject("Fantasy")
    private Book book;

    public Student() {

    }

    public Student(String name) {
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
