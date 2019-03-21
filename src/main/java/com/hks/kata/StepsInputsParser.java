package com.hks.kata;

import com.hks.kata.graph.Graph;

import java.util.List;

public class StepsInputsParser {

    public Graph parse(List<List<String>> stepsInputs) {
        Graph.Builder builder = Graph.builder();
        stepsInputs.forEach(stepsInput -> {
            String firstStepName = stepsInput.get(0);
            String secondStepName = stepsInput.get(1);
            builder.nodeIfAbsent(firstStepName)
                    .nodeIfAbsent(secondStepName)
                    .parentNode(secondStepName, firstStepName);
        });
        return builder.build();
    }

}
