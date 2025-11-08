package fr.spark.cif.blocks.portable_tank;

import com.simibubi.create.foundation.utility.CreateLang;
import fr.spark.cif.Cif;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractPortableTankItem extends BlockItem {

    private Integer capacity;

    public AbstractPortableTankItem(Block block, Properties properties) {
        super(block, properties);
        this.capacity = getCapacity();
    }

    protected abstract int getCapacity();

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {

        return new FluidHandlerItemStack(stack, getCapacity()) {
            @Override
            public boolean canDrainFluidType(FluidStack fluid) {
                return true;
            }

            @Override
            public boolean canFillFluidType(FluidStack fluid) {
                return true;
            }
        };
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);

        CompoundTag tag = stack.getTag();

        if (tag != null && tag.contains("Fluid")) {

            FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag.getCompound("Fluid"));

            if (!fluid.isEmpty()) {

                tooltipComponents.add(CreateLang.builder(Cif.MODID).translate("gui.goggles.portable_tank.name").component().withStyle(ChatFormatting.AQUA));
                tooltipComponents.add(CreateLang.fluidName(fluid).component());

                ChatFormatting color;
                int space = getCapacity() - fluid.getAmount();

                if (space != 0){
                    if (space >= getCapacity()/2){
                        color = ChatFormatting.GREEN;
                    }else {
                        color = ChatFormatting.YELLOW;
                    }
                }else {
                    color =  ChatFormatting.RED;
                }

                tooltipComponents.add(CreateLang.text(fluid.getAmount() + "/ " + getCapacity() + "mb").component().withStyle(color));
            }
        }
    }

}
