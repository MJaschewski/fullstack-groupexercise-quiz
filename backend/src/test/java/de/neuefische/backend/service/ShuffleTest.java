package de.neuefische.backend.service;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShuffleTest {
    @Test
    public void testShuffle() {
        List<String> unsortedAnswers = Arrays.asList("Answer 1", "Answer 2", "Answer 3", "Answer 4");
        List<String> originalOrder = new ArrayList<>(unsortedAnswers);

        // Shuffle the answers
        Collections.shuffle(unsortedAnswers);

        // Assert that the order has changed
        Assert.assertNotEquals(originalOrder, unsortedAnswers);

        // Assert that the shuffled list contains the same elements
        Assert.assertTrue(unsortedAnswers.containsAll(originalOrder) && originalOrder.containsAll(unsortedAnswers));
    }
}