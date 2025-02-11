package com.lwbuchanan.viridesce;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final RegistryKey<Item> ARABICA_KEY =
            RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Viridesce.MOD_ID, "arabica"));
    public static final Item ARABICA = register(
            new Item(new Item.Settings().registryKey(ARABICA_KEY)),
            ARABICA_KEY
    );

    public static final RegistryKey<Item> GREEN_COFFEE_KEY =
            RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Viridesce.MOD_ID, "green_coffee"));
    public static final Item GREEN_COFFEE = register(
            new Item(new Item.Settings().registryKey(GREEN_COFFEE_KEY)),
            GREEN_COFFEE_KEY
    );

    public static final RegistryKey<Item> ROASTED_COFFEE_KEY =
            RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Viridesce.MOD_ID, "roasted_coffee"));
    public static final Item ROASTED_COFFEE = register(
            new Item(new Item.Settings().registryKey(ROASTED_COFFEE_KEY)),
            ROASTED_COFFEE_KEY
    );

    public static final RegistryKey<Item> COFFEE_GROUNDS_KEY =
            RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Viridesce.MOD_ID, "coffee_grounds"));
    public static final Item COFFEE_GROUNDS = register(
            new Item(new Item.Settings().registryKey(COFFEE_GROUNDS_KEY)),
            COFFEE_GROUNDS_KEY
    );

    public static final RegistryKey<Item> BREWED_COFFEE_KEY =
            RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Viridesce.MOD_ID, "brewed_coffee"));
    public static final Item BREWED_COFFEE = register(
            new Item(new Item.Settings().registryKey(BREWED_COFFEE_KEY)),
            BREWED_COFFEE_KEY
    );

    public static final RegistryKey<ItemGroup> VIRIDESCE_ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Viridesce.MOD_ID, "item_group"));
    public static final ItemGroup VIRIDESCE_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ROASTED_COFFEE))
            .displayName(Text.translatable("itemGroup.viridesce"))
            .build();

    public static Item register(Item item, RegistryKey<Item> registryKey) {
       Item registeredItem = Registry.register(Registries.ITEM, registryKey.getValue(), item);
       return registeredItem;
    }

    public static void initialize() {
        CompostingChanceRegistry.INSTANCE.add(COFFEE_GROUNDS, 0.9f);
        CompostingChanceRegistry.INSTANCE.add(ROASTED_COFFEE, 0.7f);
        CompostingChanceRegistry.INSTANCE.add(GREEN_COFFEE, 0.3f);

        Registry.register(Registries.ITEM_GROUP, VIRIDESCE_ITEM_GROUP_KEY, VIRIDESCE_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(VIRIDESCE_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.GREEN_COFFEE);
            itemGroup.add(ModItems.ROASTED_COFFEE);
            itemGroup.add(ModItems.BREWED_COFFEE);
            itemGroup.add(ModItems.COFFEE_GROUNDS);
            itemGroup.add(ModItems.ARABICA);
        });
    }


}
