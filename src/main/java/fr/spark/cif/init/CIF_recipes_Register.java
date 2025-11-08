package fr.spark.cif.init;

import fr.spark.cif.Cif;
import fr.spark.cif.recipes.CIF_Recipes;
import fr.spark.cif.recipes.CIF_Recipes_Serializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CIF_recipes_Register {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Cif.MODID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Cif.MODID);

    public static final RegistryObject<RecipeSerializer<CIF_Recipes>> CIF_RECIPES_SERIALIZER =
            SERIALIZERS.register("cif", CIF_Recipes_Serializer::new);

    public static final RegistryObject<RecipeType<CIF_Recipes>> CIF_RECIPES =
            TYPES.register("cif", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return Cif.id("cif").toString();
                }
            });

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
        TYPES.register(bus);
    }
}
