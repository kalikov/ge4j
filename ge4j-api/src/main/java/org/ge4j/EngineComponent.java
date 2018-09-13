package org.ge4j;

import javax.annotation.PreDestroy;

public interface EngineComponent {
    @PreDestroy
    default void onDestroy() {
    }
}
