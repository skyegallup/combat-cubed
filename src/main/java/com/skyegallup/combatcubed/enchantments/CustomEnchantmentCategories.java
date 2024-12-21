package com.skyegallup.combatcubed.enchantments;

import com.skyegallup.combatcubed.items.AllItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CustomEnchantmentCategories {
    /**
     * Denotes enchantments that can be applied to axes. (This will likely be obsolete if/when the JE Combat Tests are
     * implemented, since the tests include the axe-only Cleaving enchant.)
     */
    public static final EnchantmentCategory AXES = EnchantmentCategory.create(
        "axe_enchants",
        item -> item instanceof AxeItem
    );

    /**
     * Denotes enchantments that can be applied to chestplates or shields.
     */
    public static final EnchantmentCategory ARMOR_CHESTS_OR_SHIELDS = EnchantmentCategory.create(
        "chest_or_shield_enchants",
        item -> {
            if (item instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.CHEST) {
                return true;
            } else {
                return item instanceof ShieldItem;
            }
        }
    );

    /**
     * Denotes enchantments that can be applied to shields.
     */
    public static final EnchantmentCategory SHIELDS = EnchantmentCategory.create(
        "shield_enchants",
        item -> item instanceof ShieldItem
    );

    /**
     * Denotes enchantments that can be applied to slings.
     */
    public static final EnchantmentCategory SLINGS = EnchantmentCategory.create(
        "sling_enchants",
        item -> item == AllItems.SLING.get()
    );
}
