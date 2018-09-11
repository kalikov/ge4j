package org.ge4j.core;

import org.ge4j.Engine;
import org.ge4j.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class EngineImpl implements Engine {
    private static final Logger logger = LoggerFactory.getLogger(EngineImpl.class);

    private boolean quit;
    private boolean isActive;
    private boolean dontSuspend;

    private long fixedDelta;

    private MainProc mainProc;

    public void run() {
        logger.info("Engine started");

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy; HH:mm:ss");
        logger.info("Date: {}", dateFormat.format(new Date()));

        logger.info("Java: {}; OS: {}", System.getProperty("java.version"), System.getProperty("os.name"));

        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        logger.info("Memory: {}K total, {}K free", totalMemory / 1024L, freeMemory / 1024L);

        try {
            mainLoop();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("Engine stopped");
    }

    private void mainLoop() throws InterruptedException {
        isActive=true;
        while (!quit) {

//            Event event;
//            while ((event = input.pollEvent()) != null) {
//                event.process(this);
//            }

            // Check if mouse is over HGE window for Input_IsMouseOver
//            _UpdateMouse();

            if (isActive || dontSuspend) {
                mainProc.run(fixedDelta);
            } else {
                // If main loop is suspended - just sleep a bit
                // (though not too much to allow instant window redraw if requested by OS)
                Thread.sleep(1);
            }
        }
    }
}
