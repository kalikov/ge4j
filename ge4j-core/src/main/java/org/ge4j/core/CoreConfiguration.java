package org.ge4j.core;

import org.ge4j.Engine;
import org.ge4j.EngineComponent;
import org.ge4j.EngineConfiguration;

public class CoreConfiguration implements EngineConfiguration {
    @EngineComponent
    public Engine engine() {
        return new EngineImpl();
    }


}
