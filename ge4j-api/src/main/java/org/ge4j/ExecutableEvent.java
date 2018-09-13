package org.ge4j;

import java.util.function.Consumer;

public interface ExecutableEvent<A> extends Event<A> {
    boolean isBound();

    void unbind();

    void execute(Consumer<A> invoker);
}
