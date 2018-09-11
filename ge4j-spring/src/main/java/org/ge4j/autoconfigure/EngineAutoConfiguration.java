package org.ge4j.autoconfigure;

import org.ge4j.EngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "org.ge4j",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EngineConfiguration.class)
)
public class EngineAutoConfiguration {
    @Bean
    public Object s() {
        return "s";
    }

//    @Bean
//    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
//        return new MyBeanDefinitionRegistryPostProcessor();
//    }

}
