package org.ge4j;

import org.ge4j.autoconfigure.EngineAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EngineAutoConfiguration.class)) {
            GraphicsSystem graphicsSystem = context.getBean(GraphicsSystem.class);
            graphicsSystem.setTitle("Demo Application");

            Engine engine = context.getBean(Engine.class);
            engine.run();
        }
    }
}
