package org.ge4j.awt;

import org.ge4j.RenderTarget;

import java.awt.image.BufferedImage;

public class AwtRenderTarget implements RenderTarget {
    private final BufferedImage image;
    private final int width;
    private final int height;

    AwtRenderTarget(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;
    }

    BufferedImage getImage() {
        return image;
    }

    @Override
    public AwtRenderContext getRenderContext() {
        return new AwtRenderContext(image.createGraphics(), width, height);
    }
}
