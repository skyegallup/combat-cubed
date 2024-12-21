package com.skyegallup.combatcubed.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.skyegallup.combatcubed.enchantments.CustomEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static java.util.Map.Entry;

@Mixin(ThornsEnchantment.class)
public class ThornsEnchantmentMixins extends Enchantment {
    protected ThornsEnchantmentMixins(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target="Lnet/minecraft/world/item/enchantment/Enchantment;<init>(Lnet/minecraft/world/item/enchantment/Enchantment$Rarity;Lnet/minecraft/world/item/enchantment/EnchantmentCategory;[Lnet/minecraft/world/entity/EquipmentSlot;)V"))
    private static void updateConstructor(Args args) {
        // Allow enchanting tables to apply Thorns to shields
        args.set(1, CustomEnchantmentCategories.ARMOR_CHESTS_OR_SHIELDS);

        // Specify Thorns as applying to chest and offhand equipment slots
        args.set(2, new EquipmentSlot[] { EquipmentSlot.CHEST, EquipmentSlot.OFFHAND });
    }

    @ModifyReturnValue(method = "canEnchant", at = @At(value = "RETURN"))
    private boolean allowShieldsForBooks(boolean originalReturn, @Local(ordinal = 0, argsOnly = true) ItemStack itemStack) {
        return originalReturn || itemStack.getItem() instanceof ShieldItem;
    }

    @WrapOperation(method = "doPostHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getRandomItemWith(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/entity/LivingEntity;)Ljava/util/Map$Entry;"))
    private Entry<EquipmentSlot, ItemStack> conditionallySelectShieldForThorns(Enchantment enchantment, LivingEntity livingEntity, Operation<Entry<EquipmentSlot, ItemStack>> original) {
        Entry<EquipmentSlot, ItemStack> originalSelection = original.call(enchantment, livingEntity);
        if (originalSelection != null && originalSelection.getValue().getItem() instanceof ShieldItem) {
            // Check if item is actively being used
            if (livingEntity.isBlocking()) {
                return originalSelection;
            }
            else {
                // Select a different item
                return EnchantmentHelper.getRandomItemWith(enchantment, livingEntity, itemStack -> (itemStack.getItem() instanceof ShieldItem));
            }
        }
        else {
            return originalSelection;
        }
    }

    @ModifyExpressionValue(method = "doPostHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/ThornsEnchantment;shouldHit(ILnet/minecraft/util/RandomSource;)Z"))
    private boolean onlyHurtIfItemStackIsSelected(boolean original, @Local() Entry<EquipmentSlot, ItemStack> entry) {
        return original && entry != null;
    }
}
