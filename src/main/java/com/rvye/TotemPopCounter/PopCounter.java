package com.rvye.TotemPopCounter;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class PopCounter implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("tpc");

    @Override
    public void onInitialize() {
        LOGGER.info("Loaded");
    }

}
