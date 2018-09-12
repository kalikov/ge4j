package org.ge4j;

@FunctionalInterface
public interface MainFunction extends EngineComponent {
    void run(long tickMillis);
}
