package org.ge4j;

import java.util.function.Consumer;

public interface Delegate<A> {
    void bind(A action);

    boolean isBound();

    void unbind(A action);

    void unbind();

    void execute(Consumer<A> invoker);
}
