package org.ge4j;

public interface GraphicsSystem extends EngineComponent {
    String getTitle();

    void setTitle(String title);

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    void setWindowed(int width, int height);

    boolean isFullscreen();

    void setFullscreen(boolean isFullscreen);

    RenderTarget getScreen();
}
