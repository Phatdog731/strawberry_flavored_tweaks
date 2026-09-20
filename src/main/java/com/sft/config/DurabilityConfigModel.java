package com.sft.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Item durability overrides: "namespace:item_id" -> max durability.
 * Saved to config/strawberry_flavored_tweaks/durability.json5
 * <p>
 * The annotation processor generates the DurabilityConfig wrapper class from this
 * model on build. Do not edit that generated class directly.
 * <p>
 * Only one config model per mod may carry @Modmenu (it registers the mod's config
 * screen entry point), so keep it on this one for now.
 */
@Modmenu(modId = "strawberry_flavored_tweaks")
@Config(name = "strawberry_flavored_tweaks/durability", wrapperName = "DurabilityConfig")
public class DurabilityConfigModel {

    public Map<String, Integer> durabilities = defaultDurabilities();

    // Items listed here ignore their durabilities entry (if any) and become
    // unbreakable instead, mirroring Custom Durability's per-item checkbox.
    public List<String> unbreakableItems = new ArrayList<>();

    private static Map<String, Integer> defaultDurabilities() {
        Map<String, Integer> map = new LinkedHashMap<>();

        // Alloyed - steel tools
        map.put("alloyed:steel_sword", 1000);
        map.put("alloyed:steel_hoe", 1000);
        map.put("alloyed:steel_shears", 750);
        map.put("alloyed:steel_fishing_rod", 512);
        map.put("alloyed:steel_spear", 1000);
        map.put("alloyed:steel_pickaxe", 1000);
        map.put("alloyed:steel_axe", 1000);
        map.put("alloyed:steel_shovel", 1000);

        // Alloyed - steel armor
        map.put("alloyed:steel_helmet", 165);
        map.put("alloyed:steel_chestplate", 240);
        map.put("alloyed:steel_leggings", 225);
        map.put("alloyed:steel_boots", 195);

        return map;
    }
}