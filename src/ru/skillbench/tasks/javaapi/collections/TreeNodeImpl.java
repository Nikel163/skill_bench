package ru.skillbench.tasks.javaapi.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNodeImpl implements TreeNode {

    private TreeNode parent;
    private List<TreeNode> children;
    private Object data;
    private boolean expanded = false;

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getRoot() {
        return this.parent != null ? this.parent.getRoot() : this;
    }

    @Override
    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    @Override
    public int getChildCount() {
        return this.children == null ? 0 : this.children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return this.children == null ? null : this.children.iterator();
    }

    @Override
    public void addChild(TreeNode child) {
        if (this.children == null) {
            this.children = new LinkedList<>();
        }
        child.setParent(this);
        this.children.add(child);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        child.setParent(null);
        return this.children.remove(child);
    }

    @Override
    public boolean isExpanded() {
        return this.expanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTreePath() {
        String res = getData() == null ? "empty" : getData().toString();
        return this.parent == null ? res : this.parent.getTreePath() + "->" + res;
    }

    @Override
    public TreeNode findParent(Object data) {
        if (data == null && getData() == null) return this;
        if (getData() != null && getData().equals(data)) return this;
        return getParent() != null ? getParent().findParent(data) : null;
    }

    @Override
    public TreeNode findChild(Object data) {
        if (data == null && getData() == null) return this;
        if (getData() != null && getData().equals(data)) return this;

        Iterator<TreeNode> child = getChildrenIterator();
        if (child == null) return null;
        TreeNode res = null;
        while (child.hasNext() && res == null) {
            res = child.next().findChild(data);
        }

        return res;
    }
}
