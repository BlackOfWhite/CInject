package org.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.di.annotations.CInject;
import org.di.mapper.CModule;

public class CDIFramework {

    private final CModule module;

    public CDIFramework(final CModule module) {
        this.module = module;
    }

    /**
     * Will try to inject missing dependencies into class classToInject
     *
     * @param classToInject
     * @return
     * @throws Exception
     */
    public <T> T inject(final Class<T> classToInject) throws Exception {
        if (classToInject == null) {
            return null;
        }
        return (T) injectFieldsIntoClass(classToInject);
    }

    private Object injectFieldsIntoClass(final Class<?> classToInject)
        throws Exception {
        for (final Constructor<?> constructor : classToInject.getConstructors()) {
            if (constructor.isAnnotationPresent(CInject.class)) {
                return injectFieldsViaConstructor(classToInject, constructor);
            }
        }
        return injectFields(classToInject);
    }

    private Object injectFieldsViaConstructor(Class<?> classToInject, Constructor<?> constructor)
        throws Exception {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final Object[] objArr = new Object[parameterTypes.length];
        int i = 0;
        for (final Class<?> paramClass : parameterTypes) {
            final Class<?> dependency = module.getMapping(paramClass);
            if (paramClass.isAssignableFrom(dependency)) {
                objArr[i++] = initializeWithSubDependencies(dependency); // instantiate multiple in constructor
            }
        }
        return classToInject.getConstructor(parameterTypes).newInstance(objArr);
    }

    private Object injectFields(Class<?> classToInject)
        throws Exception {
        Object o = classToInject.newInstance();
        for (Field field : classToInject.getDeclaredFields()) {
            if (field.isAnnotationPresent(CInject.class)) {
                String injectedClassName = field.getAnnotation(CInject.class).value();
                final Class<?> dependency;
                if (injectedClassName.isEmpty()) { // no value so get first possible implementation
                    dependency = module.getMapping(field.getType());
                } else {
                    dependency = module.getMapping(field.getType(), injectedClassName);
                }
                field.setAccessible(true);
                field.set(o, initializeWithSubDependencies(dependency)); // instantiate multiple fields
            }
        }
        return o;
    }

    private Object initializeWithSubDependencies(Class<?> clazz) throws Exception {
        try {
            return inject(clazz);
        } catch (Exception e) {
            System.out.println("Failed to instantiate class of type: " + clazz + " with its sub-dependencies.");
            throw e;
        }
    }
}
