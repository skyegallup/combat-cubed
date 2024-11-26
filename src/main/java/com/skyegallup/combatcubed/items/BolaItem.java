package com.skyegallup.combatcubed.items;
import com.skyegallup.combatcubed.entities.projectiles.ThrownBola;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BolaItem extends Item {
    public static int COOLDOWN_SECONDS = 4;

    public BolaItem(Properties properties) {
		super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        level.playSound(
            null,
            player.getX(),
            player.getY(),
            player.getZ(),
            SoundEvents.SNOWBALL_THROW,  // TODO: should use a different sound
            SoundSource.NEUTRAL,
            0.5F,
            0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        player.getCooldowns().addCooldown(this, COOLDOWN_SECONDS * 20);
        if (!level.isClientSide) {
            ThrownBola thrownBola = new ThrownBola(level, player);
            // thrownBola.setItem(itemStack);
            thrownBola.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 0.3F);
            level.addFreshEntity(thrownBola);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
