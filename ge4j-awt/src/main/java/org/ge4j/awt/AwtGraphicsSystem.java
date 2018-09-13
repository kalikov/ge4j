package org.ge4j.awt;

import org.ge4j.Engine;
import org.ge4j.GraphicsSystem;
import org.ge4j.Managed;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtGraphicsSystem implements GraphicsSystem {
    private final Engine engine;
    private JFrame frame;

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
        frame = new JFrame("AwtScreen") {
            @Override
            public void paint(Graphics gfx) {
            }
        };
//        frame.setBackground(SCREEN_BG_COLOR);
//        frame.setSize(Screen.ORIGINAL_DISPLAY_WIDTH, Screen.ORIGINAL_DISPLAY_HEIGHT);
        frame.setIgnoreRepaint(true);
        frame.setVisible(true);

        Insets insets = frame.getInsets();
        frame.setSize(640 + insets.right + insets.left, 480 + insets.bottom + insets.top);
//
//        frame.createBufferStrategy(2);
//        bufferStrategy = frame.getBufferStrategy();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                engine.stop();
            }
        });
    }

    private void onEngineStopped() {
        if (frame != null) {
            frame.dispose();
            frame = null;
        }
    }
}
