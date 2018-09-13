package org.ge4j;

public interface Event<A> {
    void bind(A action);

    boolean isBound(A action);

    void unbind(A action);
}
