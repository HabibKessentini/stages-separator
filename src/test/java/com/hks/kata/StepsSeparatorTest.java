package com.hks.kata;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;

public class StepsSeparatorTest {


    @Test
    public void should_separate_a_given_simple_workflow_steps_into_multiple_stages() {
        List<String> steps1 = list("clean", "build");
        List<String> steps2 = list("clean", "metadata");
        List<List<String>> stepsInput = list(steps1, steps2);

        List<List<String>> result = new StepsSeparator().toStages(stepsInput);

        assertThat(result.get(0)).containsExactlyInAnyOrder("clean");
        assertThat(result.get(1)).containsExactlyInAnyOrder("build", "metadata");
    }


    @Test
    public void should_separate_a_given_complex_workflow_steps_into_multiple_stages() {
        List<String> steps1 = list("clean", "build");
        List<String> steps2 = list("metadata", "binary");
        List<String> steps3 = list("clean", "metadata");
        List<String> steps4 = list("build", "resources");
        List<String> steps5 = list("link", "binary");
        List<String> steps6 = list("build", "link");
        List<List<String>> stepsInput = list(steps1, steps2, steps3, steps4, steps5, steps6);

        List<List<String>> result = new StepsSeparator().toStages(stepsInput);

        assertThat(result.get(0)).containsExactlyInAnyOrder("clean");
        assertThat(result.get(1)).containsExactlyInAnyOrder("build", "metadata");
        assertThat(result.get(2)).containsExactlyInAnyOrder("resources", "link");
        assertThat(result.get(3)).containsExactlyInAnyOrder("binary");

    }

}