package org.ge4j;

public final class EngineFactory {
    private static Engine instance;

    private EngineFactory() {
    }

    public static Engine getEngine() {
        if (instance == null) {
            synchronized (EngineFactory.class) {
                if (instance == null) {

                }
            }
        }
        return instance;
    }
}
