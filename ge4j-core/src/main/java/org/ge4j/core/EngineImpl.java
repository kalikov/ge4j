package org.ge4j.core;

import org.ge4j.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EngineImpl implements Engine {
    private static final Logger logger = LoggerFactory.getLogger(EngineImpl.class);

    public void run() {
        logger.info("Started");
    }
}
