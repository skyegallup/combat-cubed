package com.skyegallup.combatcubed;

import com.mojang.logging.LogUtils;
import com.skyegallup.combatcubed.alchemy.AllPotionRecipes;
import com.skyegallup.combatcubed.alchemy.AllPotions;
import com.skyegallup.combatcubed.effects.AllEffects;
import com.skyegallup.combatcubed.enchantments.AllEnchantments;
import com.skyegallup.combatcubed.enchantments.EnchantmentEffectHandlers;
import com.skyegallup.combatcubed.entities.AllEntityTypes;
import com.skyegallup.combatcubed.items.AllItems;
import com.skyegallup.combatcubed.render.ObsidianArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ElytraItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CombatCubedMod.ID)
public class CombatCubedMod
{
    // Define mod id in a common place for everything to reference
    public static final String ID = "combat_cubed";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public CombatCubedMod(IEventBus modEventBus)
    {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        AllEffects.EFFECTS.register(modEventBus);
        AllEnchantments.ENCHANTMENTS.register(modEventBus);
        AllEntityTypes.ENTITY_TYPES.register(modEventBus);
        AllItems.ITEMS.register(modEventBus);
        AllPotions.POTIONS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.addListener(EnchantmentEffectHandlers::onLivingAttack);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(AllPotionRecipes::addPotionRecipes);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(AllItems.OBSIDIAN_ARROW.get());
            event.accept(AllItems.PEBBLE.get());
            event.accept(AllItems.SLING.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(AllEntityTypes.OBSIDIAN_ARROW.get(), ObsidianArrowRenderer::new);
            EntityRenderers.register(AllEntityTypes.PEBBLE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(AllEntityTypes.THROWN_FIRE_CHARGE.get(), ThrownItemRenderer::new);
        }
    }
}
