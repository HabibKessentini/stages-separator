package com.hks.kata;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class StepsSeparator {


    public List<List<String>> toStages(List<List<String>> stepsInputs) {
        StepsGraph stepsGraph = StepsInputsParser.parse(stepsInputs);
        return IntStream.rangeClosed(0, stepsGraph.getLevelNumber())
                .mapToObj(stepsGraph::getStepNamesByLevel)
                .collect(toList());

    }


}
