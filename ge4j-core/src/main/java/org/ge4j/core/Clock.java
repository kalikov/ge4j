package org.ge4j.core;

import org.ge4j.MillisProvider;
import org.ge4j.Timer;

public class Clock implements Timer {
    private final MillisProvider millisProvider;

    private long time;
    private long elapsedMillis;
    private long tickMillis;

    public Clock(MillisProvider millisProvider) {
        this.millisProvider = millisProvider;
        time = millisProvider.getMillis();
    }

    public Clock(MillisProvider millisProvider, long time) {
        long currentMillis = millisProvider.getMillis();
        if (time > currentMillis) {
            throw new IllegalArgumentException(String.format("Argument time %d is ahead of clock time %d", time, currentMillis));
        }
        this.millisProvider = millisProvider;
        this.time = time;
    }

    @Override
    public long getTickMillis() {
        return tickMillis;
    }

    @Override
    public long getElapsedMillis() {
        return elapsedMillis;
    }

    public long tick(long interval) {
        long delta = millisProvider.getMillis() - time;
        if (delta == 0) {
            return 0;
        }

        // If we reached the time for the next frame or we just run in unlimited FPS mode, then do the stuff
        if (delta >= interval) {
            // fDeltaTime = time step in seconds returned by Timer_GetDelta
            long normalizedDelta = delta;
            // Cap too large time steps usually caused by lost focus to avoid jerks
            if (normalizedDelta > 200) {
                normalizedDelta = interval > 0 ? interval : 10;
            }

            // Update time counter returned Timer_GetTime
            tickMillis = normalizedDelta;
            elapsedMillis += normalizedDelta;
        }
        return delta;
    }
}
