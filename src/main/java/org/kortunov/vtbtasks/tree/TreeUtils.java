package org.kortunov.vtbtasks.tree;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TreeUtils {

    private static final Field TREE_MAP = getTreeMapField();
    private static final Field ROOT = getRoot();

    @SneakyThrows
    private static Field getRoot() {
        final Field root = TreeMap.class.getDeclaredField("root");
        root.setAccessible(true);
        return root;
    }

    @SneakyThrows
    private static Field getTreeMapField() {
        final Field field = TreeSet.class.getDeclaredField("m");
        field.setAccessible(true);
        return field;
    }

    @SneakyThrows
    public static long countOfLeavesHack(SortedSet<?> tree) {
        if (!TreeSet.class.equals(tree.getClass())) {
            throw new UnsupportedTreeException(tree.getClass().getName());
        }
        final NavigableMap<?, ?> map = (NavigableMap<?, ?>) TREE_MAP.get(tree);
        return map.entrySet()
                .stream()
                .map(TreeEntry::of)
                .filter(treeEntry -> treeEntry.getLeft() == null && treeEntry.getRight() == null)
                .count();
    }

    @SneakyThrows
    public static long countOfLeaves(SortedSet<?> tree) {
        if (!TreeSet.class.equals(tree.getClass())) {
            throw new UnsupportedTreeException(tree.getClass().getName());
        }
        final var map = TREE_MAP.get(tree);
        final var root = (Map.Entry<?, ?>) ROOT.get(map);
        final var treeEntry = TreeEntry.of(root);
        return countOfLeaves(treeEntry);
    }

    private static long countOfLeaves(TreeEntry<?, ?> node) {
        if (node == null)
            return 0;
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        else
            return countOfLeaves(node.getLeft()) + countOfLeaves(node.getRight());
    }

}

