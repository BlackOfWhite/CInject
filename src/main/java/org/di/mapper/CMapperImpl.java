package org.di.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.di.annotations.Default;

public abstract class CMapperImpl implements CModule {

    /**
     * Class and possible registered implementations
     */
    private final Map<Class<?>, Set<Class<?>>> classMap = new HashMap<>();

    protected <T> void addMapping(final Class<T> baseClass, final Class<? extends T> subClass) {
        Set<Class<?>> set;
        if (classMap.containsKey(baseClass)) {
            set = classMap.get(baseClass);
        } else {
            set = new HashSet<>();
        }
        set.add(subClass.asSubclass(baseClass));
        classMap.put(baseClass, set);
    }

    public <T> Class<? extends T> getMapping(final Class<T> type) {
        final List<Class<?>> implementations =
            classMap.get(type).stream().collect(Collectors.toList());
        if (implementations.isEmpty()) {
            throw new IllegalArgumentException("No mapping for type: " + type);
        }
        Optional<Class<?>> defaultImpl = implementations.stream().filter(clazz -> clazz.isAnnotationPresent(Default.class)).findFirst();
        Class<?> implementation = defaultImpl.orElse(implementations.get(0)); // default or first
        return implementation.asSubclass(type);
    }

    public <T> Class<? extends T> getMapping(final Class<T> type, final String forName) {
        final Class<?> implementation =
            classMap.get(type).stream().filter(clazz -> clazz.getName().endsWith(forName)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No mapping for type: " + type + " for value: " + forName));
        return implementation.asSubclass(type);
    }
}
