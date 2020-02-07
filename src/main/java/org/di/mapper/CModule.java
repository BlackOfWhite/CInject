package org.di.mapper;

public interface CModule {
    void configure();

    <T> Class<? extends T> getMapping(Class<T> type);

    <T> Class<? extends T> getMapping(Class<T> type, String forClass);
}
