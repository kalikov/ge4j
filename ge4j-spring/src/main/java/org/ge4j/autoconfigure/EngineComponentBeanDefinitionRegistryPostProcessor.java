package org.ge4j.autoconfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ge4j.EngineComponent;
import org.ge4j.EngineConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class EngineComponentBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private static final Log logger = LogFactory.getLog(EngineComponentBeanDefinitionRegistryPostProcessor.class);

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        for (String name : beanDefinitionRegistry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName != null) {
                postProcessBean(beanDefinitionRegistry, name, beanClassName);
            }
        }
    }

    private void postProcessBean(BeanDefinitionRegistry beanDefinitionRegistry, String name, String beanClassName) {
        try {
            Class<?> beanClass = Class.forName(beanClassName);
            postProcessBean(beanDefinitionRegistry, name, beanClass);
        } catch (ClassNotFoundException e) {
            logger.warn("Failed to post process bean \"" + name + "\"", e);
        }
    }

    private void postProcessBean(BeanDefinitionRegistry beanDefinitionRegistry, String name, Class<?> beanClass) {
        if (!EngineConfiguration.class.isAssignableFrom(beanClass)) {
            return;
        }
        ReflectionUtils.doWithLocalMethods(beanClass, method -> {
            if (method.isAnnotationPresent(EngineComponent.class)) {
                GenericBeanDefinition componentDefinition = new GenericBeanDefinition();
                componentDefinition.setAutowireCandidate(true);
                componentDefinition.setParentName(name);
                componentDefinition.setFactoryBeanName(name);
                componentDefinition.setFactoryMethodName(method.getName());
                beanDefinitionRegistry.registerBeanDefinition(method.getName(), componentDefinition);
            }
        });
    }
}
