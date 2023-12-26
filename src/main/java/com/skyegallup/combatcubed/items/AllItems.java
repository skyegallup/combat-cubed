package com.skyegallup.combatcubed.items;

import com.skyegallup.combatcubed.CombatCubedMod;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllItems {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(CombatCubedMod.ID);

    public static DeferredItem<ObsidianArrowItem> OBSIDIAN_ARROW = ITEMS.registerItem(
        "obsidian_arrow",
        ObsidianArrowItem::new,
        new Item.Properties().stacksTo(64)
    );

    public static DeferredItem<Item> PEBBLE = ITEMS.registerSimpleItem(
        "pebble",
        new Item.Properties().stacksTo(64)
    );

    public static DeferredItem<SlingItem> SLING = ITEMS.registerItem(
        "sling",
        SlingItem::new,
        new Item.Properties().stacksTo(1).durability(200)
    );
}
