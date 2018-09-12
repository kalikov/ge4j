package org.ge4j.core;

import org.ge4j.EngineComponent;
import org.ge4j.MillisProvider;

public class SystemMillisProvider implements MillisProvider, EngineComponent {
    @Override
    public long getMillis() {
        return System.currentTimeMillis();
    }
}
