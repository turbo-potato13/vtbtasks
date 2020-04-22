package org.kortunov.vtbtasks.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

public class TreeUtilsTest {

    @Test
    public void testCountOfLeaves() {
        SortedSet<Integer> binaryTree = new TreeSet<>();
        binaryTree.add(8);
        binaryTree.add(6);
        binaryTree.add(9);
        binaryTree.add(7);
        binaryTree.add(5);
        Assertions.assertEquals(TreeUtils.countOfLeaves(binaryTree), 3);
    }

        @Test
        public void testCountOfLeavesHack(){
            SortedSet<Integer> binaryTree = new TreeSet<>();
            binaryTree.add(8);
            binaryTree.add(6);
            binaryTree.add(9);
            binaryTree.add(7);
            binaryTree.add(5);
            Assertions.assertEquals(TreeUtils.countOfLeavesHack(binaryTree), 3);
        }

}
