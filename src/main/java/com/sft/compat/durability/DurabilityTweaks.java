package com.sft.compat.durability;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;

import com.sft.StrawberryFlavoredTweaks;
import com.sft.config.SftConfigs;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.minecraft.core.component.DataComponents.MAX_DAMAGE;
import static net.minecraft.core.component.DataComponents.UNBREAKABLE;

public class DurabilityTweaks {

    public static void initialize() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            Set<String> unbreakable = new HashSet<>(SftConfigs.DURABILITY.unbreakableItems());

            for (Map.Entry<String, Integer> entry : SftConfigs.DURABILITY.durabilities().entrySet()) {
                String itemId = entry.getKey();
                boolean isUnbreakable = unbreakable.remove(itemId);
                apply(context, itemId, entry.getValue(), isUnbreakable);
            }

            // Items marked unbreakable but with no durabilities entry - the number
            // is irrelevant for these, so pass a placeholder.
            for (String itemId : unbreakable) {
                apply(context, itemId, 0, true);
            }
        });
    }

    private static void apply(DefaultItemComponentEvents.ModifyContext context, String itemId, int maxDamage, boolean unbreakable) {
        Identifier identifier = Identifier.tryParse(itemId);
        if (identifier == null) {
            StrawberryFlavoredTweaks.LOGGER.warn("[DurabilityTweaks] Invalid item id in config: '{}'", itemId);
            return;
        }

        String namespace = identifier.getNamespace();
        boolean modPresent = namespace.equals("minecraft") || FabricLoader.getInstance().isModLoaded(namespace);

        if (!BuiltInRegistries.ITEM.containsKey(identifier)) {
            if (modPresent) {
                // The owning mod is here, but the item isn't - worth a warning
                // (typo, or the item id changed upstream).
                StrawberryFlavoredTweaks.LOGGER.warn("[DurabilityTweaks] Skipping unknown item '{}' (item id not found, but '{}' is loaded)", itemId, namespace);
            }
            // Otherwise the owning mod just isn't installed - nothing to warn about.
            return;
        }

        Item item = BuiltInRegistries.ITEM.getValue(identifier);

        if (unbreakable) {
            context.modify(item, components -> components.set(UNBREAKABLE, Unit.INSTANCE));
        } else {
            context.modify(item, components -> components.set(MAX_DAMAGE, maxDamage));
        }
    }
}