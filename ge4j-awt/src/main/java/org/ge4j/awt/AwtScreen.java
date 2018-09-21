package org.ge4j.awt;

import org.ge4j.RenderContext;
import org.ge4j.RenderTarget;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class AwtScreen implements RenderTarget {
    private final BufferStrategy bufferStrategy;
    private final int width;
    private final int height;

    public AwtScreen(AwtWindow window) {
        bufferStrategy = window.getFrame().getBufferStrategy();
        width = 480;
        height = 320;
    }

    BufferStrategy getBufferStrategy() {
        return bufferStrategy;
    }

    @Override
    public RenderContext getRenderContext() {
        AwtRenderTarget target = new AwtRenderTarget(width, height);
        return new AwtScreenContext(this, target);
    }

    public void dispose() {
    }

    Graphics2D getGraphics() {
        return (Graphics2D) bufferStrategy.getDrawGraphics();
    }
}
