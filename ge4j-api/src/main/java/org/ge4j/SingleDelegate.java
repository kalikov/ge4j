package org.ge4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class SingleDelegate<A> implements ExecutableEvent<A> {
    private final AtomicReference<A> actionRef = new AtomicReference<>();

    public void bind(A action) {
        actionRef.set(action);
    }

    @Override
    public boolean isBound(A action) {
        return actionRef.get() == action;
    }

    public boolean isBound() {
        return actionRef.get() != null;
    }

    public void unbind(A action) {
        actionRef.compareAndSet(action, null);
    }

    public void unbind() {
        actionRef.set(null);
    }

    public void execute(Consumer<A> invoker) {
        A action = actionRef.get();
        if (action != null) {
            invoker.accept(action);
        }
    }
}
