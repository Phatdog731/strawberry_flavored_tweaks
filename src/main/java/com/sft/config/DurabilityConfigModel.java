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
    public List<String> unbreakableItems = defaultUnbreakable();

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

        // Create Fly
        map.put("create:super_glue", 99);    // ignored below - glue is unbreakable
        map.put("create:sand_paper", 8);
        map.put("create:red_sand_paper", 8);


        return map;
    }

    private static List<String> defaultUnbreakable() {
        List<String> list = new ArrayList<>();
        list.add("create:super_glue");
        list.add("enderitemod:enderite_pickaxe");
        list.add("enderitemod:enderite_axe");
        list.add("enderitemod:enderite_hoe");
        list.add("enderitemod:enderite_shovel");
        list.add("enderitemod:enderite_sword");
        list.add("enderitemod:enderite_spear");
        list.add("enderitemod:enderite_shears");
        list.add("enderitemod:enderite_bow");
        list.add("enderitemod:enderite_crossbow");
        list.add("enderitemod:enderite_shield");
        list.add("enderitemod:enderite_elytra");
        list.add("enderitemod:enderite_elytra_seperated");
        list.add("enderitemod:enderite_helmet");
        list.add("enderitemod:enderite_chestplate");
        list.add("enderitemod:enderite_leggings");
        list.add("enderitemod:enderite_boots");
        return list;
    }
}