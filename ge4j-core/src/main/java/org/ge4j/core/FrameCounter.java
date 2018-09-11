package org.ge4j.core;

import org.ge4j.MillisProvider;

public class FrameCounter {
    private final MillisProvider millisProvider;
    private long time;
    private int frame;
    private int frameRate;

    public FrameCounter(MillisProvider millisProvider) {
        this.millisProvider = millisProvider;
        this.time = millisProvider.getMillis();
    }

    public FrameCounter(MillisProvider millisProvider, long time) {
        long currentMillis = millisProvider.getMillis();
        if (time > currentMillis) {
            throw new IllegalArgumentException(String.format("Argument time %d is ahead of clock time %d", time, currentMillis));
        }
        this.millisProvider = millisProvider;
        this.time = time;
    }

    public void increment() {
        long now = millisProvider.getMillis();
        if (now - time <= 1000) {
            frame++;
        } else {
            frameRate = frame;
            frame = 0;
            time = now;
        }
    }

    public int getFrameRate() {
        return frameRate;
    }
}
