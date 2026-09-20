package com.sft.config;
/**
 * Central place to load every config in the mod. As new config models are added
 * under com.sft.config (one per concern - durability, recipes, world gen, etc.),
 * load them here and call SftConfigs.init() once from the mod initializer.
 */
public class SftConfigs {

    public static DurabilityConfig DURABILITY;

    public static void init() {
        DURABILITY = DurabilityConfig.createAndLoad();
    }
}