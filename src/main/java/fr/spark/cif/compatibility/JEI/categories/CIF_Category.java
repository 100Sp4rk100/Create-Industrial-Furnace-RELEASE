package fr.spark.cif.compatibility.JEI.categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import fr.spark.cif.Cif;
import fr.spark.cif.init.CIF_blocks_Register;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;

public class CIF_Category implements IRecipeCategory<AbstractCookingRecipe> {

    public static final ResourceLocation UID = Cif.id("cif_processing");
    public static final ResourceLocation TEXTURE = Cif.id("textures/gui/jei/cif_processing_gui.png");

    public static final RecipeType<AbstractCookingRecipe> CIF_RECIPES_TYPE = new RecipeType<>(UID, AbstractCookingRecipe.class);


    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable block;

    public CIF_Category(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 0, 176, 81) ;
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(CIF_blocks_Register.FURNACE_BLOCK.get()));

        this.block = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(CIF_blocks_Register.FURNACE_BLOCK.get())
        );
    }

    @Override
    public RecipeType<AbstractCookingRecipe> getRecipeType() {
        return CIF_RECIPES_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.jei.category.cif_processing") ;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AbstractCookingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 43, 30).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 117, 30).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(AbstractCookingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        block.draw(guiGraphics, 80, 25);
    }

}
