package org.ge4j.awt;

import org.ge4j.RenderContext;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class AwtScreenContext implements RenderContext {
    private final AwtScreen screen;
    private final AwtRenderTarget target;
    private final AwtRenderContext context;

    public AwtScreenContext(AwtScreen screen, AwtRenderTarget target) {
        this.screen = screen;
        this.target = target;
        context = target.getRenderContext();
    }

    @Override
    public void clear(int color) {
        context.clear(color);
    }

    @Override
    public void dispose() {
        BufferStrategy bufferStrategy = screen.getBufferStrategy();
        do {
            do {
                Graphics2D gfx = (Graphics2D) screen.getGraphics();
//                gfx.translate(insets.left, insets.top);
//                gfx.scale(scaleX, scaleY);
                try {
                    gfx.drawImage(target.getImage(), 0, 0, Color.WHITE, null);
//                    if (cursor != null) {
//                        Point screenOffset = null;
//                        synchronized (frame.getTreeLock()) {
//                            if (frame.isShowing()) {
//                                screenOffset = frame.getLocationOnScreen();
//                            }
//                        }
//                        if (screenOffset != null) {
//                            Surface cursorSurface = cursor.getSurface();
//                            if (cursorSurface instanceof AwtSurface) {
//                                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
//                                int x = (int) ((mousePoint.getX() - screenOffset.getX() - insets.left) / scaleX);
//                                int y = (int) ((mousePoint.getY() - screenOffset.getY() - insets.top) / scaleY);
//                                gfx.drawImage(((AwtSurface) cursorSurface).getImage(), x, y, null);
//                            }
//                        }
//                    }
                } finally {
                    gfx.dispose();
                }
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());

        context.dispose();
    }
}
