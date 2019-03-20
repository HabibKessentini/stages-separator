package com.hks.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class StepsGraph {

    private Map<String, StepNode> stepByName;

    public static Builder builder() {
        return new Builder();
    }

    private StepsGraph(Map<String, StepNode> stepByName) {
        this.stepByName = stepByName;
        setStepNodesLevels(computeRouteNode());
    }

    private void setStepNodesLevels(StepNode stepNode) {
        for (StepNode currentStepNode : stepByName.values()) {
            if (!currentStepNode.hasParent(stepNode.getId())) {
                continue;
            }
            int level = stepNode.getLevel() + 1;
            int currentLevel = currentStepNode.getLevel();
            currentStepNode.setLevel(level > currentLevel ? level : currentLevel);
            setStepNodesLevels(currentStepNode);
        }
    }

    private StepNode computeRouteNode() {
        return stepByName.values().stream()
                .filter(StepNode::isRoute)
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public int getLevelNumber() {
        return stepByName.values().stream()
                .map(StepNode::getLevel)
                .reduce((level1, level2) -> level1 > level2 ? level1 : level2)
                .orElse(0);
    }

    public List<String> getStepNamesByLevel(int level) {
        return stepByName.values().stream()
                .filter(stepNode -> stepNode.getLevel() == level)
                .map(StepNode::getId)
                .collect(toList());
    }

    public static class Builder {

        private final Map<String, StepNode> stepByName = new HashMap<>();

        public Builder nodeIfAbsent(String nodeId) {
            this.stepByName.putIfAbsent(nodeId, StepNode.withId(nodeId));
            return this;
        }

        public Builder parentNode(String nodeId, String parentNodeId) {
            this.stepByName.get(nodeId).addParent(parentNodeId);
            return this;
        }

        public StepsGraph build() {
            return new StepsGraph(stepByName);
        }
    }


}
