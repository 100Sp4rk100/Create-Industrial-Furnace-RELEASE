package fr.spark.cif.compatibility.JEI;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import fr.spark.cif.Cif;
import fr.spark.cif.compatibility.JEI.categories.CIF_Category;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.stream.Collectors;

import static fr.spark.cif.blocks.industrial_furnace.controller.ControllerEntity.COOKING_TYPES;

@JeiPlugin
public class JEI_CIF_Plugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return Cif.id("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CIF_Category(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<AbstractCookingRecipe> allCookingRecipes = COOKING_TYPES.stream()
                .flatMap(type -> recipeManager.getAllRecipesFor(type).stream())
                .collect(Collectors.toList());

        registration.addRecipes(CIF_Category.CIF_RECIPES_TYPE, allCookingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        IModPlugin.super.registerGuiHandlers(registration);
    }
}
