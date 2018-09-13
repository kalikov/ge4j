package org.ge4j;

public interface Engine {
    Event<Runnable> engineStarted();

    Event<Runnable> engineStopped();

    void run();

    void stop();
}
