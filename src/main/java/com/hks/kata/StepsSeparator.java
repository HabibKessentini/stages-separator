package com.hks.kata;

import com.hks.kata.graph.Graph;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class StepsSeparator {


    public List<List<String>> toStages(List<List<String>> stepsInputs) {
        Graph stepsGraph = new StepsInputsParser().parse(stepsInputs);
        return IntStream.rangeClosed(0, stepsGraph.getLevelNumber())
                .mapToObj(stepsGraph::getStepIdsByLevel)
                .collect(toList());

    }


}
