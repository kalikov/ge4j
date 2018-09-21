package org.ge4j.awt;

import org.ge4j.RenderContext;

import java.awt.Color;
import java.awt.Graphics2D;

public class AwtRenderContext implements RenderContext {
    private final Graphics2D graphics;
    private final int width;
    private final int height;

    public AwtRenderContext(Graphics2D graphics, int width, int height) {
        this.graphics = graphics;
        this.width = width;
        this.height = height;
    }

    @Override
    public void clear(int color) {
        graphics.setBackground(new Color(color));
        graphics.clearRect(0, 0, width, height);
    }

    @Override
    public void dispose() {
        graphics.dispose();
    }
}
