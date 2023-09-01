package top.xiaomckedou233.noanysus;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import org.slf4j.Logger;
import net.minecraft.server.MinecraftServer;
import org.slf4j.LoggerFactory;

public class NoAnySUS implements DedicatedServerModInitializer {
    public static final String MODID = "noanysus";
    public static final Logger LOGGER = LoggerFactory.getLogger("noanysus");
    public static MinecraftServer minecraftServer;
    @Override
    public void onInitializeServer() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            minecraftServer = server;
        });
        LOGGER.info("NoAnySUS,Start!");
    }
}