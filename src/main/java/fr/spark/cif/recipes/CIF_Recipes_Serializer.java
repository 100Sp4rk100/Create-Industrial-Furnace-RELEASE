package fr.spark.cif.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CIF_Recipes_Serializer implements RecipeSerializer<CIF_Recipes> {

    @Override
    public CIF_Recipes fromJson(ResourceLocation id, JsonObject json) {
        String group = json.has("group") ? json.get("group").getAsString() : "";
        CookingBookCategory category = CookingBookCategory.MISC;

        Ingredient input = Ingredient.fromJson(json.get("input"));
        ItemStack output = net.minecraftforge.common.crafting.CraftingHelper.getItemStack(json.getAsJsonObject("output"), true);

        float experience = 0.0F;
        int cookingTime = json.has("cookingtime") ? json.get("cookingtime").getAsInt() : 200;

        return new CIF_Recipes(id, group, category, input, output, experience, cookingTime);
    }


    @Override
    public CIF_Recipes fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
        String group = buffer.readUtf();
        CookingBookCategory category = buffer.readEnum(CookingBookCategory.class);
        Ingredient input = Ingredient.fromNetwork(buffer);
        ItemStack output = buffer.readItem();
        float experience = buffer.readFloat();
        int cookingTime = buffer.readVarInt();

        return new CIF_Recipes(id, group, category, input, output, experience, cookingTime);
    }


    @Override
    public void toNetwork(FriendlyByteBuf buffer, CIF_Recipes recipe) {
        buffer.writeUtf(recipe.getGroup());
        buffer.writeEnum(recipe.getCategory());
        recipe.getIngredients().get(0).toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem(null));
        buffer.writeFloat(recipe.getExperience());
        buffer.writeVarInt(recipe.getCookingTime());
    }

}
