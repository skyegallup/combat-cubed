package com.skyegallup.combatcubed.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class SlingItem extends ProjectileWeaponItem implements Vanishable {
    public static final int MAX_DRAW_DURATION = 15;
    public static final int MAX_RANGE = 8;

    public SlingItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean flag = !player.getProjectile(itemstack).isEmpty();

        // TODO: evaluate if this should apply here. it probably should?
        InteractionResultHolder<ItemStack> ret = EventHooks.onArrowNock(itemstack, level, player, hand, flag);
        if (ret != null) return ret;

        if (!player.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public void releaseUsing(
        @NotNull ItemStack itemStack,
        @NotNull Level level,
        @NotNull LivingEntity user,
        int useDuration
    ) {
        // this is largely copied from BowItem::releaseUsing

        if (user instanceof Player player) {
            boolean shouldNotConsumeAmmo = player.getAbilities().instabuild;
            ItemStack projectileItemStack = player.getProjectile(itemStack);

            int power = this.getUseDuration(itemStack) - useDuration;
            power = EventHooks.onArrowLoose(itemStack, level, player, power, !projectileItemStack.isEmpty() || shouldNotConsumeAmmo);
            if (power < 0) return;

            // if we have ammo (or are in creative), use the sling!
            if (!projectileItemStack.isEmpty() || shouldNotConsumeAmmo) {
                if (projectileItemStack.isEmpty()) {
                    projectileItemStack = new ItemStack(AllItems.PEBBLE.get());
                }

                float powerF = 1f;
                if (powerF > 0.1f) {  // require the shot to be at least a little charged
                    if (!level.isClientSide) {
                        // per-projectile handling
                        if (projectileItemStack.is(AllItems.PEBBLE)) {
                            this.launchPebble();
                        } else if (projectileItemStack.is(Items.SPLASH_POTION) || projectileItemStack.is(Items.LINGERING_POTION)) {
                            this.launchPotion(projectileItemStack, level, player, powerF);
                        } else if (projectileItemStack.is(Items.FIRE_CHARGE)) {
                            this.launchFireCharge();
                        }

                        itemStack.hurtAndBreak(1, player, _player -> _player.broadcastBreakEvent(player.getUsedItemHand()));
                    }

                    level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.ARROW_SHOOT,  // TODO: make a new sound
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + powerF * 0.5F
                    );

                    // if the player isn't in creative mode, consume ammo
                    if (!player.getAbilities().instabuild) {
                        projectileItemStack.shrink(1);
                        if (projectileItemStack.isEmpty()) {
                            player.getInventory().removeItem(projectileItemStack);
                        }

                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    protected void launchPebble() {
        throw new NotImplementedException();
    }

    protected void launchPotion(
        ItemStack potionItemStack,
        Level level,
        Player player,
        float power
    ) {
        ThrownPotion thrownPotion = new ThrownPotion(level, player);
        thrownPotion.setItem(potionItemStack);
        thrownPotion.shootFromRotation(player, player.getXRot(), player.getYRot(), -10f, power + .3f, 1f);
        level.addFreshEntity(thrownPotion);
    }

    protected void launchFireCharge() {
        throw new NotImplementedException();
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        // TODO: add a "slingable" tag
        return item -> item.is(Items.SPLASH_POTION)
            || item.is(Items.LINGERING_POTION)
            || item.is(AllItems.PEBBLE)
            || item.is(Items.FIRE_CHARGE);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemStack) {
        return 72000;
    }

    @Override
    public int getDefaultProjectileRange() {
        return MAX_RANGE;
    }
}
