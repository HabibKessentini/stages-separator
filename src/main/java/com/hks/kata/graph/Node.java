package com.hks.kata.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final String id;
    private final List<String> parents;
    private int level;

    private Node(String id) {
        this.id = id;
        this.parents = new ArrayList<>();
    }

    public static Node withId(String name) {
        return new Node(name);
    }

    public void addParent(String id) {
        parents.add(id);
    }

    public boolean hasParent(String id) {
        return parents.contains(id);
    }

    public boolean isRoute() {
        return parents.isEmpty();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }
}
