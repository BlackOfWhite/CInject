package org;

import org.di.CDI;
import org.di.CDIFramework;
import org.di.mapper.CMapperImpl;
import org.model.Company;
import org.model.emplyees.Engineer;
import org.model.emplyees.Employee;
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
                addMapping(Employee.class, Engineer.class);
                addMapping(Book.class, Dictionary.class);
                addMapping(Book.class, Fantasy.class);
                addMapping(Author.class, Slowacki.class);
                addMapping(Author.class, Mickiewicz.class);
            }
        };
        final CDIFramework ownDi = CDI.getFramework(diConfig);
        final Company company = ownDi.inject(Company.class);
        System.out.println(company);
    }
}
