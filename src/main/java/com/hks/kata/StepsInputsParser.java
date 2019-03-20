package com.hks.kata;

import java.util.List;

public class StepsInputsParser {

    public static StepsGraph parse(List<List<String>> stepsInputs) {
        StepsGraph.Builder builder = StepsGraph.builder();
        for (List<String> stepsInput : stepsInputs) {
            String firstStepName = stepsInput.get(0);
            String secondStepName = stepsInput.get(1);
            builder.nodeIfAbsent(firstStepName)
                    .nodeIfAbsent(secondStepName)
                    .parentNode(secondStepName, firstStepName);
        }
        return builder.build();
    }

}
