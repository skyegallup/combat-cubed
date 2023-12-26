package com.skyegallup.combatcubed.render;

import com.skyegallup.combatcubed.CombatCubedMod;
import com.skyegallup.combatcubed.entities.projectiles.ObsidianArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ObsidianArrowRenderer extends ArrowRenderer<ObsidianArrow> {
    public static final ResourceLocation OBSIDIAN_ARROW_LOCATION = new ResourceLocation(
        CombatCubedMod.ID,
        "textures/entity/projectiles/obsidian_arrow.png"
    );

    public ObsidianArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ObsidianArrow arrow) {
        return OBSIDIAN_ARROW_LOCATION;
    }
}
