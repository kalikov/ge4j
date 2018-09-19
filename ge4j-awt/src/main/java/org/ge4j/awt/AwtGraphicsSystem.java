package org.ge4j.awt;

import org.ge4j.Engine;
import org.ge4j.GraphicsSystem;
import org.ge4j.Managed;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class AwtGraphicsSystem implements GraphicsSystem {
    private static final Logger logger = LoggerFactory.getLogger(AwtGraphicsSystem.class);

    private final Engine engine;
    private AwtWindow window;
    private String title = "Game Engine";
    private int width = 640;
    private int height = 480;
    private boolean isFullscreen = true;

    @Managed
    public AwtGraphicsSystem(Engine engine) {
        this.engine = engine;
        engine.engineStarted().bind(this::onEngineStarted);
        engine.engineStopped().bind(this::onEngineStopped);

        GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        GraphicsDevice fullscreenDevice = null;
        for (GraphicsDevice device : devices) {
            if (!device.isFullScreenSupported()) {
                logger.debug("Found device {}");
                continue;
            }
            if (fullscreenDevice == null) {
                fullscreenDevice = device;
            } else {
                DisplayMode deviceMode = device.getDisplayMode();
                int width = deviceMode.getWidth();
                int height = deviceMode.getHeight();
                int bitDepth = deviceMode.getBitDepth();
                DisplayMode fullscreenMode = fullscreenDevice.getDisplayMode();
                int fullscreenWidth = fullscreenMode.getWidth();
                int fullscreenHeight = fullscreenMode.getHeight();
                int fullscreenBitDepth = fullscreenMode.getBitDepth();
                if (width < fullscreenWidth || height < fullscreenHeight) {
                    continue;
                }
                if (width > fullscreenWidth || height > fullscreenHeight || bitDepth > fullscreenBitDepth) {
                    fullscreenDevice = device;
                }
            }
        }
    }

    public void onDestroy() {
        engine.engineStarted().unbind(this::onEngineStarted);
        engine.engineStopped().unbind(this::onEngineStopped);
    }

    private void onEngineStarted() {
        window = new AwtWindow(title, width, height, isFullscreen);
        window.setCloseHandler(engine::stop);
    }

    private void onEngineStopped() {
        if (window != null) {
            window.dispose();
            window = null;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        if (window != null) {
            window.setTitle(title);
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        adjustWindow();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        adjustWindow();
    }

    @Override
    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        adjustWindow();
    }

    @Override
    public boolean isFullscreen() {
        return isFullscreen;
    }

    @Override
    public void setFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        adjustWindow();
    }

    private void adjustWindow() {
        if (window != null) {
            window.setDimensions(width, height);
        }
    }
}
