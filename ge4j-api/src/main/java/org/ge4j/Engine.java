package org.ge4j;

public interface Engine {
    Event<Runnable> engineStarted();

    Event<Runnable> engineStopped();

    Event<MainFunction> engineCycle();

    void run();

    void stop();
}
