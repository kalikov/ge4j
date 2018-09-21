package org.ge4j.awt;

import javax.swing.JFrame;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

class AwtWindow {
    private final JFrame frame;
    private final BufferStrategy bufferStrategy;

    private Runnable closeHandler;

    private int windowedWidth;
    private int windowedHeight;

    AwtWindow(String title, int width, int height, boolean isFullscreen) {
        frame = new JFrame(title) {
            @Override
            public void paint(Graphics gfx) {
            }
        };
        GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
        DisplayMode displayMode = device.getDisplayMode();

        windowedWidth = Math.min(width, displayMode.getWidth());
        windowedHeight = Math.min(height, displayMode.getHeight());

        int x = (displayMode.getWidth() - windowedWidth) / 2;
        int y = (displayMode.getHeight() - windowedHeight) / 2;
        frame.setBounds(x, y, windowedWidth, windowedHeight);

        frame.setIgnoreRepaint(true);
        frame.setUndecorated(isFullscreen);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.createBufferStrategy(2);
        bufferStrategy = frame.getBufferStrategy();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (closeHandler != null) {
                    closeHandler.run();
                }
            }
        });

        /*JButton button1 = new JButton("FS");
        button1.addActionListener(e -> {
            toggleFullscreen();
        });

        JButton button2 = new JButton("800");
        button2.addActionListener(e -> {
            setWindowed(800, 600);
        });

        JButton button3 = new JButton("640");
        button3.addActionListener(e -> {
            setWindowed(640, 480);
        });

        AtomicInteger i = new AtomicInteger();
        JButton button4 = new JButton("X");
        button4.addActionListener(e -> {
            int v = i.incrementAndGet() % 4;
            if (v == 1) {
                frame.setLocation(device.getDefaultConfiguration().getBounds().x, 0);
            } else if (v == 2) {
                frame.setLocation(displayMode.getWidth() - frame.getWidth(), 0);
            } else if (v == 3) {
                frame.setLocation(displayMode.getWidth() - frame.getWidth(), displayMode.getHeight() - frame.getHeight());
            } else {
                frame.setLocation(0, displayMode.getHeight() - frame.getHeight());
            }
        });

        JPanel panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        frame.getContentPane().add(panel);*/

        Insets insets = frame.getInsets();
        windowedWidth = Math.min(windowedWidth, displayMode.getWidth() - insets.right - insets.left);
        windowedHeight = Math.min(windowedHeight, displayMode.getHeight() - insets.bottom - insets.top);

        int frameWidth = windowedWidth + insets.right + insets.left;
        int frameHeight = windowedHeight + insets.bottom + insets.top;

        frame.setBounds((displayMode.getWidth() - frameWidth) / 2, (displayMode.getHeight() - frameHeight) / 2, frameWidth, frameHeight);

        if (isFullscreen) {
            device.setFullScreenWindow(frame);
        }
    }

    JFrame getFrame() {
        return frame;
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

    public void setWindowed(int width, int height) {
        GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
        if (device.getFullScreenWindow() == frame) {
            device.setFullScreenWindow(null);
            frame.dispose();
            frame.setUndecorated(false);
        }
        Rectangle bounds = device.getDefaultConfiguration().getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(device.getDefaultConfiguration());
        bounds.x += screenInsets.left;
        bounds.y += screenInsets.top;
        bounds.width -= screenInsets.left + screenInsets.right;
        bounds.height -= screenInsets.top + screenInsets.bottom;

        Insets insets = frame.getInsets();
        windowedWidth = Math.min(width, bounds.width - insets.right - insets.left);
        windowedHeight = Math.min(height, bounds.height - insets.bottom - insets.top);

        int frameWidth = windowedWidth + insets.right + insets.left;
        int frameHeight = windowedHeight + insets.bottom + insets.top;

        Point location = frame.getLocationOnScreen();
        int x = (location.x - bounds.x) * (bounds.width - frameWidth) / (bounds.width - frame.getWidth()) + bounds.x;
        int y = (location.y - bounds.y) * (bounds.height - frameHeight) / (bounds.height - frame.getHeight()) + bounds.y;

        frame.setBounds(Math.max(bounds.x, Math.min(bounds.x + bounds.width - frameWidth, x)),
                Math.max(bounds.y, Math.min(bounds.y + bounds.height - frameHeight, y)), frameWidth, frameHeight);
        frame.setVisible(true);
    }

    public void toggleFullscreen() {
        GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
        if (device.getFullScreenWindow() == frame) {
            device.setFullScreenWindow(null);
            frame.dispose();
            frame.setUndecorated(false);
            frame.setVisible(true);
        } else {
            frame.dispose();
            frame.setUndecorated(true);
            frame.setVisible(true);
            device.setFullScreenWindow(frame);
        }
    }

    public void flip() {
//        Insets insets = frame.getInsets();
        do {
            do {
                Graphics2D gfx = (Graphics2D) bufferStrategy.getDrawGraphics();
//                gfx.translate(insets.left, insets.top);
//                gfx.scale(scaleX, scaleY);
                try {
//                    gfx.drawImage(surface.getImage(), 0, 0, SCREEN_BG_COLOR, null);
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
    }
}
