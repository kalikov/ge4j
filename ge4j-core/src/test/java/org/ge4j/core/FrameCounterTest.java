package org.ge4j.core;

import org.ge4j.MillisProvider;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class FrameCounterTest {
    @Test
    public void shouldReturnZeroByDefault() {
        FrameCounter counter = new FrameCounter(() -> 1000);
        Assert.assertEquals(counter.getFrameRate(), 0);
    }

    @Test(dataProvider = "millisWithRates")
    public void shouldCountMultipleFrames(long[] millis, int[] rates) {
        FrameCounter counter = new FrameCounter(new ArrayMillisProvider(millis));
        Assert.assertEquals(counter.getFrameRate(), rates[0]);
        for (int i = 1; i < millis.length; i++) {
            counter.increment();
            Assert.assertEquals(counter.getFrameRate(), rates[i]);
        }
    }

    @DataProvider
    public static Object[][] millisWithRates() {
        return new Object[][] {
                {new long[] {0, 1001}, new int[] {0, 0}},
                {new long[] {0, 1000, 1001}, new int[] {0, 0, 1}},
                {new long[] {0, 1, 1, 1, 1001}, new int[] {0, 0, 0, 0, 3}},
                {new long[] {0, 1, 1, 1, 1001, 1500, 10000, 10001, 10002, 100000}, new int[] {0, 0, 0, 0, 3, 3, 1, 1, 1, 2}}
        };
    }

    private static class ArrayMillisProvider implements MillisProvider {
        private final long[] millis;
        private int index;

        private ArrayMillisProvider(long[] millis) {
            this.millis = millis;
        }

        @Override
        public long getMillis() {
            return millis[index++];
        }
    }
}
