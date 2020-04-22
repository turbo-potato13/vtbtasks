package org.kortunov.vtbtasks.tree;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Map;

class TreeEntry<K, V> implements Map.Entry<K, V> {

    private static final Class<?> ENTRY_CLASS = getEntryClass();
    private static final Field LEFT = getField("left");
    private static final Field RIGHT = getField("right");

    @SneakyThrows
    private static Field getField(String name) {
        final Field field = ENTRY_CLASS.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    @SneakyThrows
    private static Class<?> getEntryClass() {
        return Class.forName("java.util.TreeMap$Entry");
    }

    private final Map.Entry<K, V> value;

    private TreeEntry(Map.Entry<K, V> value) {
        this.value = value;
    }

    public static <K, V> TreeEntry<K, V> of(Map.Entry<K, V> entry) {
        if (entry == null) {
            return null;
        }
        if (!ENTRY_CLASS.equals(entry.getClass())) {
            throw new InvalidEntryException(String.format("Unsupported class = %s", entry.getClass().getName()));
        }
        return new TreeEntry<>(entry);
    }

    @Override
    public K getKey() {
        return this.value.getKey();
    }

    @Override
    public V getValue() {
        return this.value.getValue();
    }

    @Override
    public V setValue(V v) {
        return this.value.setValue(v);
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public TreeEntry<K, V> getLeft() {
        return TreeEntry.of((Map.Entry<K, V>)LEFT.get(this.value));
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public TreeEntry<K, V> getRight() {
        return TreeEntry.of((Map.Entry<K, V>)RIGHT.get(this.value));
    }
}
