package org;

import org.di.CDI;
import org.di.CDIFramework;
import org.di.mapper.CMapperImpl;
import org.model.School;
import org.model.Student;
import org.model.User;
import org.model.authors.Author;
import org.model.authors.Mickiewicz;
import org.model.authors.Slowacki;
import org.model.book.Book;
import org.model.book.Dictionary;
import org.model.book.Fantasy;

public class Main {
    public static void main(String[] args) throws Exception {
        CMapperImpl diConfig = new CMapperImpl() {
            @Override
            public void configure() {
                addMapping(User.class, Student.class);
                addMapping(Book.class, Dictionary.class);
                addMapping(Book.class, Fantasy.class);
                addMapping(Author.class, Slowacki.class);
                addMapping(Author.class, Mickiewicz.class);
            }
        };
        final CDIFramework ownDi = CDI.getFramework(diConfig);
        final School school = ownDi.inject(School.class);
        System.out.println(school);
    }
}
