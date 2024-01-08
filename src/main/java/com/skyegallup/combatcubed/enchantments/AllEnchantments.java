package com.skyegallup.combatcubed.enchantments;

import com.skyegallup.combatcubed.CombatCubedMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllEnchantments {
    public static DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT, CombatCubedMod.ID);

    public static DeferredHolder<Enchantment, FrostAspectEnchantment> FROST_ASPECT = ENCHANTMENTS.register(
            "frost_aspect",
            () -> new FrostAspectEnchantment(Enchantment.Rarity.RARE, new EquipmentSlot[] {EquipmentSlot.MAINHAND })
    );

    public static DeferredHolder<Enchantment, GuidanceEnchantment> GUIDANCE = ENCHANTMENTS.register(
        "guidance",
        () -> new GuidanceEnchantment(Enchantment.Rarity.RARE, new EquipmentSlot[] {EquipmentSlot.MAINHAND })
    );
}
