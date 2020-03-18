package com.tsu;

import ru.skillbench.tasks.javaapi.collections.TreeNode;
import ru.skillbench.tasks.javaapi.collections.TreeNodeImpl;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNodeImpl();
        root.setData("rootNode");
        TreeNode node1 = new TreeNodeImpl();
        node1.setData("node1");
        TreeNode node2 = new TreeNodeImpl();
        node2.setData("node2");
        TreeNode node3 = new TreeNodeImpl();
        root.addChild(node1);
        node1.addChild(node2);
        node2.addChild(node3);
        System.out.println(root);
        System.out.println(node1);
        System.out.println(node2);
        System.out.println(node3);

        System.out.println(root.findChild(true));
    }

}
