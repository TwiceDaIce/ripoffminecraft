package util;

import util.event.EventBus;
import util.event.annotations.EventBusSubscriber;
import util.event.annotations.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void registerItems() {

    }
    @SubscribeEvent
    public static void registerBlocks() {
        // Register blocks
    }
}
