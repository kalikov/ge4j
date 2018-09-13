package org.ge4j;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class MultiDelegate<A> implements ExecutableEvent<A> {
    private final Collection<A> actions = new ConcurrentLinkedQueue<>();

    public void bind(A action) {
        actions.add(action);
    }

    @Override
    public boolean isBound(A action) {
        return actions.contains(action);
    }

    public boolean isBound() {
        return !actions.isEmpty();
    }

    public void unbind(A action) {
        actions.remove(action);
    }

    public void unbind() {
        actions.clear();
    }

    public void execute(Consumer<A> invoker) {
        for (A action : actions) {
            invoker.accept(action);
        }
    }
}
