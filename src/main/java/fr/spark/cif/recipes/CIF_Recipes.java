package fr.spark.cif.recipes;

import fr.spark.cif.init.CIF_recipes_Register;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class CIF_Recipes extends AbstractCookingRecipe {

    public CIF_Recipes(ResourceLocation id, String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(CIF_recipes_Register.CIF_RECIPES.get(), id, group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CIF_recipes_Register.CIF_RECIPES_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return CIF_recipes_Register.CIF_RECIPES.get();
    }

    public CookingBookCategory getCategory() {
        return this.category();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return super.getIngredients();
    }
}