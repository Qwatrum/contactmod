package de.qwatrum.contact;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleGroupRegistry;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Contact implements ModInitializer {

    @Override
    public void onInitialize() {

        UseItemCallback.EVENT.register((player, level, interactionHand) -> {

            if (level.getBlockState(BlockPos.containing(player.position())).getBlock() == Blocks.AIR) {
                if (player.getActiveItem().getItem() == Items.FIREWORK_STAR) {

                    level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, player.getX(), player.getY(), player.getZ(), 0.0, 0.0, 0.0);
                    level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, player.getX(), player.getY(), player.getZ(), 0.0, 0.0, 0.0);
                    level.addAlwaysVisibleParticle(ParticleTypes.END_ROD, true, player.getX(), player.getY(), player.getZ(), 0.0, 0.0, 0.0);
                    return InteractionResult.PASS;
                }

            }
            return InteractionResult.PASS;
        });
    }
}
