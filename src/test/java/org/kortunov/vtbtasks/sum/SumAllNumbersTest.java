package org.kortunov.vtbtasks.sum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kortunov.vtbtasks.word.FrequencyWord;

public class SumAllNumbersTest {

    @Test
    public void testCalculateSum(){
        Assertions.assertEquals(SumAllNumbers.calculateSum("10 ff 33 -1"), 42);
    }
}
