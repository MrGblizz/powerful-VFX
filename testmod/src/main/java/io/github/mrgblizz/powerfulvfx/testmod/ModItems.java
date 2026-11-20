package io.github.mrgblizz.powerfulvfx.testmod;

// imports ...
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item BANANA_PEEL = register("banana_peel");

    private static Item register(String name) {
        // 1. fabriquer l'identifiant et la clé
        Identifier id = Identifier.fromNamespaceAndPath("powerful_vfx_test",name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);


        // 2. fabriquer l'item avec ses propriétés (qui portent la clé)
        Item item = new Item(
                new Item.Properties().setId(key)
        );


        // 3. l'inscrire dans le registre et le renvoyer
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void initialize() { }   // sert à forcer le chargement de la classe
}