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

    public void reset() {
        time = millisProvider.getMillis();
        frame = 0;
        frameRate = 0;
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
        return frameRate == 0 ? frame : frameRate;
    }
}
