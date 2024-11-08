package com.skyegallup.combatcubed.mixins;

import com.skyegallup.combatcubed.enchantments.AllEnchantments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Player.class)
public abstract class PlayerMixins extends LivingEntity {
    protected PlayerMixins(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyArg(method = "disableShield(Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;addCooldown(Lnet/minecraft/world/item/Item;I)V"), index = 1)
    public int getShieldDisableCooldown(int original)  {
        int recoveryLevel = EnchantmentHelper.getEnchantmentLevel(AllEnchantments.RECOVERY.get(), this);
        if (recoveryLevel > 0) {
            return original - 10 - ((recoveryLevel - 1) * 5);
        }
        else {
            return original;
        }
    }
}
