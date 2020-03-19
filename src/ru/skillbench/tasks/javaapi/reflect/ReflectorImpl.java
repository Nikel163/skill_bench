package ru.skillbench.tasks.javaapi.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ReflectorImpl implements Reflector {

    private Class<?> clazz;

    @Override
    public void setClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Stream<String> getMethodNames(Class<?>... paramTypes) {
        if (this.clazz == null) {
            throw new NullPointerException();
        }

        List<String> methodNames = new LinkedList<>();

        for (Method x: this.clazz.getMethods()) {
            if (Arrays.equals(x.getParameterTypes(), paramTypes)) {
                methodNames.add(x.getName());
            }
        }

        return methodNames.stream();
    }

    @Override
    public Stream<Field> getAllDeclaredFields() {
        if (this.clazz == null) {
            throw new NullPointerException();
        }
        return Arrays.stream(this.clazz.getDeclaredFields()).filter(it -> !Modifier.isStatic(it.getModifiers()));
    }

    @Override
    public Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = (this.clazz == null) ?
                target.getClass().getDeclaredField(fieldName) :
                this.clazz.getDeclaredField(fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        return (this.clazz == null) ?
                field.get(target) :
                field.get(this.clazz.getConstructors());
    }

    @Override
    public Object getMethodResult(Object constructorParam, String methodName, Object... methodParams) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object obj = (constructorParam == null) ?
                    this.clazz.getConstructors() :
                    this.clazz.getConstructor(constructorParam.getClass());
        Object res;
        try {
            Method method = this.clazz.getMethod(methodName);
            method.setAccessible(true);
            res = method.invoke(obj, methodParams);
        } catch (InvocationTargetException e) {
            throw new RuntimeException();
        }

        return res;
    }
}
