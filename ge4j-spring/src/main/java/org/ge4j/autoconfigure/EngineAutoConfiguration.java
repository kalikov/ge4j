package org.ge4j.autoconfigure;

import org.ge4j.EngineComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "org.ge4j",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EngineComponent.class)
)
public class EngineAutoConfiguration {
}
