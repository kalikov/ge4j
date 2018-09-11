package org.ge4j;

import org.ge4j.autoconfigure.EngineAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EngineAutoConfiguration.class);

        Engine engine = context.getBean(Engine.class);
        engine.run();
    }
}
