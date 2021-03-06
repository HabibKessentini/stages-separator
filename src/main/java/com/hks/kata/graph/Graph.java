package com.hks.kata.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;
import static java.util.stream.Collectors.toList;

public class Graph {

    private Map<String, Node> nodeById;

    private Graph(Map<String, Node> nodeById) {
        this.nodeById = nodeById;
        updateNodeLevels(computeRouteNode());
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getLevelNumber() {
        return nodeById.values().stream()
                .mapToInt(Node::getLevel)
                .max()
                .orElse(0) + 1;
    }

    public List<String> getStepIdsByLevel(int level) {
        return nodeById.values().stream()
                .filter(node -> node.getLevel() == level)
                .map(Node::getId)
                .collect(toList());
    }

    private void updateNodeLevels(Node node) {
        nodeById.values().stream()
                .filter(currentNode -> currentNode.hasParent(node.getId()))
                .forEach(currentNode -> {
                    currentNode.setLevel(max(node.getLevel() + 1, currentNode.getLevel()));
                    updateNodeLevels(currentNode);
                });
    }

    private Node computeRouteNode() {
        return nodeById.values().stream()
                .filter(Node::isRoute)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No route node was found"));
    }

    public static class Builder {

        private final Map<String, Node> stepById = new HashMap<>();

        public Builder nodeIfAbsent(String nodeId) {
            this.stepById.putIfAbsent(nodeId, Node.withId(nodeId));
            return this;
        }

        public Builder parentNode(String nodeId, String parentNodeId) {
            this.stepById.get(nodeId).addParent(parentNodeId);
            return this;
        }

        public Graph build() {
            return new Graph(stepById);
        }
    }

}
