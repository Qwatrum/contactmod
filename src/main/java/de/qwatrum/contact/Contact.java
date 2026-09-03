package de.qwatrum.contact;

import de.qwatrum.contact.item.ModItems;
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

    public static final String MOD_ID = "contact";

    @Override
    public void onInitialize() {

        ModItems.registerModItems();

        UseItemCallback.EVENT.register((player, level, interactionHand) -> {

            if (level.getBlockState(BlockPos.containing(player.position())).getBlock() == Blocks.AIR) {
                if (player.getActiveItem().getItem() == ModItems.EMITTER) {

                    ParticleOptions particle = ParticleTypes.END_ROD;

                    level.addAlwaysVisibleParticle(particle, true, player.getX(), player.getY() + 0.5, player.getZ(), 0.0, 0.0, 0.0);
                    level.addAlwaysVisibleParticle(particle, true, player.getX(), player.getY() + 0.5, player.getZ(), 0.0, 0.0, 0.0);
                    level.addAlwaysVisibleParticle(particle, true, player.getX(), player.getY() + 0.5, player.getZ(), 0.0, 0.0, 0.0);

                    return InteractionResult.SUCCESS;
                }

            }
            return InteractionResult.PASS;
        });
    }
}
