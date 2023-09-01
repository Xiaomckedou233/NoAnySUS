package top.xiaomckedou233.noanysus;

import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoAnySUS implements DedicatedServerModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("noanysus");
    @Override
    public void onInitializeServer() {

        LOGGER.info("NoAnySUS,Start!");
    }
}