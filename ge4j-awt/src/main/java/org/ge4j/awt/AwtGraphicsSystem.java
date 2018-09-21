package org.ge4j.awt;

import org.ge4j.Engine;
import org.ge4j.GraphicsSystem;
import org.ge4j.Managed;
import org.ge4j.RenderTarget;

public class AwtGraphicsSystem implements GraphicsSystem {
    private final Engine engine;
    private AwtWindow window;
    private AwtScreen screen;

    private String title = "Game Engine";
    private int width = 640;
    private int height = 480;
    private boolean isFullscreen = false;

    @Managed
    public AwtGraphicsSystem(Engine engine) {
        this.engine = engine;
        engine.engineStarted().bind(this::onEngineStarted);
        engine.engineStopped().bind(this::onEngineStopped);
    }

    public void onDestroy() {
        engine.engineStarted().unbind(this::onEngineStarted);
        engine.engineStopped().unbind(this::onEngineStopped);
    }

    private void onEngineStarted() {
        window = new AwtWindow(title, width, height, isFullscreen);
        window.setCloseHandler(engine::stop);

        screen = new AwtScreen(window);
    }

    private void onEngineStopped() {
        if (screen != null) {
            screen.dispose();
            screen = null;
        }
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
    public void setWindowed(int width, int height) {
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

    @Override
    public RenderTarget getScreen() {
        return screen;
    }

    private void adjustWindow() {
        if (window != null) {
            window.setWindowed(width, height);
        }
    }
}
