package de.qwatrum.contact.item;

import de.qwatrum.contact.Contact;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    public static final Item EMITTER = registerItem("emitter", properties -> new Item(properties.stacksTo(1).durability(128)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {

        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Contact.MOD_ID, name), function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Contact.MOD_ID, name)))));

    }

    public static void registerModItems() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(fabricCreativeModeTabOutput -> {
            fabricCreativeModeTabOutput.accept(EMITTER);
        });
    }
}
