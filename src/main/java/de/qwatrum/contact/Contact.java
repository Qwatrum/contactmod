package de.qwatrum.contact;

import de.qwatrum.contact.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleGroupRegistry;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.block.Blocks;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Contact implements ModInitializer {

    public static final String MOD_ID = "contact";

    @Override
    public void onInitialize() {

        ModItems.registerModItems();

        UseItemCallback.EVENT.register((player, level, interactionHand) -> {
            if (player.getActiveItem().getItem() == ModItems.EMITTER) {
                if (level.getBlockState(BlockPos.containing(player.position())).getBlock() == Blocks.AIR) {

                    ParticleOptions particle = ParticleTypes.END_ROD;

                    level.addAlwaysVisibleParticle(particle, true, player.getX(), player.getY() + 0.5, player.getZ(), 0.0, 0.0, 0.0);
                    level.addAlwaysVisibleParticle(particle, true, player.getX(), player.getY() + 0.5, player.getZ(), 0.0, 0.0, 0.0);
                    level.addAlwaysVisibleParticle(particle, true, player.getX(), player.getY() + 0.5, player.getZ(), 0.0, 0.0, 0.0);
                    player.getActiveItem().setDamageValue(player.getActiveItem().getDamageValue() - 1);
                    return InteractionResult.SUCCESS;

                }
            } else if (player.getActiveItem().getItem() == Items.WRITTEN_BOOK) {

                ItemStack bookStack = player.getActiveItem();
                String name = bookStack.getCustomName().getString();

                if (player.getDisplayName().getString().equals(name)) {
                    return InteractionResult.PASS;
                } else {
                    return InteractionResult.SUCCESS;
                }
            }

            return InteractionResult.PASS;
        });


    }
}
