package de.qwatrum.contact.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.qwatrum.contact.Contact;
import de.qwatrum.contact.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class ContactClient implements ClientModInitializer {
    KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(Contact.MOD_ID, "contact")
    );

    KeyMapping hugKey = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.contact.hug",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_H,
                    this.CATEGORY
            )
    );
    @Override
    public void onInitializeClient() {


        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register(((playerChatMessage, serverPlayer, bound) -> serverPlayer.getInventory().contains(ModItems.CONTACTOR.getDefaultInstance())));
        ServerMessageEvents.ALLOW_COMMAND_MESSAGE.register(((playerChatMessage, commandSourceStack, bound) -> {
            if (commandSourceStack.isPlayer()) {
                return Objects.requireNonNull(commandSourceStack.getPlayer()).getInventory().contains(ModItems.CONTACTOR.getDefaultInstance());
            }
            return true;
        }));


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (this.hugKey.consumeClick()) {
                if (client.player != null) {
                    client.player.swing(InteractionHand.MAIN_HAND);
                    client.player.swing(InteractionHand.OFF_HAND);
                }
            }

        });

    }
}
