package org.ge4j.awt;

import org.ge4j.Screen;

import javax.swing.JFrame;
import java.awt.Graphics;

public class AwtScreen implements Screen {
    private JFrame frame;

    public AwtScreen() {
        frame = new JFrame("AwtScreen") {
            @Override
            public void paint(Graphics gfx) {
            }
        };
//        frame.setBackground(SCREEN_BG_COLOR);
//        frame.setSize(Screen.ORIGINAL_DISPLAY_WIDTH, Screen.ORIGINAL_DISPLAY_HEIGHT);
        frame.setIgnoreRepaint(true);
//        frame.setVisible(true);
//
//        Insets insets = frame.getInsets();
//
//        frame.setSize(Screen.DEFAULT_DISPLAY_WIDTH + insets.right + insets.left,
//                Screen.DEFAULT_DISPLAY_HEIGHT + insets.bottom + insets.top);
//
//        frame.createBufferStrategy(2);
//        bufferStrategy = frame.getBufferStrategy();
//
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent event) {
//                game.quit();
//            }
//        });
    }
}
