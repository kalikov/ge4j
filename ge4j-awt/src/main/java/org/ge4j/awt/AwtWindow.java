package org.ge4j.awt;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class AwtWindow {
    private final GraphicsDevice fullscreenDevice;
    private final JFrame frame;

    private Runnable closeHandler;

    AwtWindow(String title, int width, int height, boolean isFullscreen) {


        frame = new JFrame(title) {
            @Override
            public void paint(Graphics gfx) {
            }
        };
//        frame.setBackground(SCREEN_BG_COLOR);
//        frame.setSize(Screen.ORIGINAL_DISPLAY_WIDTH, Screen.ORIGINAL_DISPLAY_HEIGHT);
        frame.setIgnoreRepaint(true);
        frame.setVisible(true);

        Insets insets = frame.getInsets();
        int frameWidth = width + insets.right + insets.left;
        int frameHeight = height + insets.bottom + insets.top;
        frame.setSize(frameWidth, frameHeight);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screen.width - frameWidth) / 2, (screen.height - frameHeight) / 2);
//
//        frame.createBufferStrategy(2);
//        bufferStrategy = frame.getBufferStrategy();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (closeHandler != null) {
                    closeHandler.run();
                }
            }
        });
    }

    void setCloseHandler(Runnable closeHandler) {
        this.closeHandler = closeHandler;
    }

    void dispose() {
        frame.dispose();
    }

    void setTitle(String title) {
        frame.setTitle(title);
    }

    public void setDimensions(int width, int height) {
        frame.setSize(width, height);
    }

    public void toggleFullscreen() {

    }
}
