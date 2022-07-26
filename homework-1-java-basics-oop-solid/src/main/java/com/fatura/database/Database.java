package com.fatura.database;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Database<T> {
    private final List<T> items;

    protected Database(List<T> items) {
        this.items = items;
    }

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        return items.get(index);
    }

    public List<T> getAll() {
        return Collections.unmodifiableList(this.items);
    }

    public List<T> filter(Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toUnmodifiableList());
    }

    public void remove(T item) {
        items.remove(item);
    }
}
