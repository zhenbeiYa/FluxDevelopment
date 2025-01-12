package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void MonoTest() {
        Mono<int[]> just = Mono.just(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        just.subscribe(ele -> {
            System.out.println(Arrays.toString(ele));
        });

    }
}
