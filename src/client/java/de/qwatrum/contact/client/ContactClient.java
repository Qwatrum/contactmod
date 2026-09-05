package de.qwatrum.contact.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.qwatrum.contact.Contact;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;

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
