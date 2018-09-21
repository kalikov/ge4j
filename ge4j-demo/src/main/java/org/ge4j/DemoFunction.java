package org.ge4j;

public class DemoFunction implements MainFunction {
    private final Engine engine;
    private final GraphicsSystem graphics;

    @Managed
    public DemoFunction(Engine engine, GraphicsSystem graphics) {
        this.engine = engine;
        this.graphics = graphics;
        engine.engineCycle().bind(this);
    }

    @Override
    public void onDestroy() {
        engine.engineCycle().unbind(this);
    }

    @Override
    public void run(long tickMillis) {
        RenderContext context = graphics.getScreen().getRenderContext();
        try {
            context.clear(0);
        } finally {
            context.dispose();
        }
    }
}
