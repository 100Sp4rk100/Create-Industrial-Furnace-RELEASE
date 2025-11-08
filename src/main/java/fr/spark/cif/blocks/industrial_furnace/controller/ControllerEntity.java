package fr.spark.cif.blocks.industrial_furnace.controller;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import fr.spark.cif.blocks.industrial_furnace.control_panel.ControlPanelEntity;
import fr.spark.cif.blocks.industrial_furnace.diamound_alloy.DiamondAlloyBlock;
import fr.spark.cif.blocks.industrial_furnace.mechanical_port.MechanicalPortBlock;
import fr.spark.cif.blocks.industrial_furnace.mechanical_port.MechanicalPortEntity;
import fr.spark.cif.blocks.industrial_furnace.fluid_port.FluidPortBlock;
import fr.spark.cif.blocks.industrial_furnace.fluid_port.FluidPortEntity;
import fr.spark.cif.blocks.industrial_furnace.furnace.FurnaceBlock;
import fr.spark.cif.client.ClientSounds;
import fr.spark.cif.init.CIF_fluids_Register;
import fr.spark.cif.init.CIF_recipes_Register;
import fr.spark.cif.init.CIF_sound_Event_Register;
import fr.spark.cif.sounds.AlertExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ControllerEntity extends SmartBlockEntity {

    public static final int FUEL_CONSUMER = 200;
    public static final int WATER_CONSUMER = 500;
    public static final int COOK_TIME = 4;

    public int slot1Tick;
    public int slot2Tick;
    public int slot3Tick;
    public int slot4Tick;
    public int cookTime1;
    public int cookTime2;
    public int cookTime3;
    public int cookTime4;
    public int numbeforeExplode;
    private int tick;
    private int beforeExplode;
    private int isHotTick;

    public ItemStack slot1;
    public ItemStack slot2;
    public ItemStack slot3;
    public ItemStack slot4;
    public ItemStack slot5;
    public ItemStack slot6;
    public ItemStack slot7;
    public ItemStack slot8;

    public boolean explode;
    public boolean on;
    public boolean isHot;
    private boolean soundPlay;
    private AlertExplosion alertExplosion;

    protected final ItemStackHandler itemHandler = new ItemStackHandler(8) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
            setCookTime(slot);
            notifyUpdate();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return slot != 4 && slot != 5 && slot != 6 && slot != 7 && !FurnaceBlock.getAnyCookingResult(level, stack).isEmpty() && checkStructure();
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot == 4 || slot == 5 || slot == 6 || slot == 7) {
                if (!itemHandler.getStackInSlot(slot).isEmpty()) {
                    return super.extractItem(slot, amount, simulate);
                } else {
                    int toExtract = -1;
                    switch (slot) {
                        case 4 -> toExtract = 0;
                        case 5 -> toExtract = 1;
                        case 6 -> toExtract = 2;
                        case 7 -> toExtract = 3;
                    }
                    if (toExtract != -1) {
                        return super.extractItem(toExtract, amount, simulate);
                    }
                }

            }
            return ItemStack.EMPTY;
        }
    };

    protected LazyOptional<IItemHandler> handlerCap = LazyOptional.of(() -> itemHandler);

    public static final List<RecipeType<? extends AbstractCookingRecipe>> COOKING_TYPES = List.of(
            CIF_recipes_Register.CIF_RECIPES.get(),
            RecipeType.SMELTING,
            RecipeType.BLASTING,
            RecipeType.SMOKING,
            RecipeType.CAMPFIRE_COOKING
    );



    public ControllerEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);

        this.slot1Tick = 0;
        this.slot2Tick = 0;
        this.slot3Tick = 0;
        this.slot4Tick = 0;
        this.cookTime1 = 0;
        this.cookTime2 = 0;
        this.cookTime3 = 0;
        this.cookTime4 = 0;
        this.tick = 0;
        this.numbeforeExplode = 0;
        this.beforeExplode = 0;
        this.isHotTick = 0;

        this.slot1 = ItemStack.EMPTY;
        this.slot2 = ItemStack.EMPTY;
        this.slot3 = ItemStack.EMPTY;
        this.slot4 = ItemStack.EMPTY;
        this.slot5 = ItemStack.EMPTY;
        this.slot6 = ItemStack.EMPTY;
        this.slot7 = ItemStack.EMPTY;
        this.slot8 = ItemStack.EMPTY;

        this.soundPlay = true;
        this.explode = false;
        this.on = false;
        this.isHot = false;
    }



    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);

        compound.put("Inventory", itemHandler.serializeNBT());

        compound.putInt("slot1Tick", slot1Tick);
        compound.putInt("slot2Tick", slot2Tick);
        compound.putInt("slot3Tick", slot3Tick);
        compound.putInt("slot4Tick", slot4Tick);

        compound.putInt("cookTime1", cookTime1);
        compound.putInt("cookTime2", cookTime2);
        compound.putInt("cookTime3", cookTime3);
        compound.putInt("cookTime4", cookTime4);

        compound.putInt("numbeforeExplode", numbeforeExplode);
        compound.putInt("beforeExplode", beforeExplode);

        compound.putBoolean("on", on);
        compound.putBoolean("isHot", isHot);
        compound.putInt("isHotTick", isHotTick);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);

        if (compound.contains("Inventory")) {
            itemHandler.deserializeNBT(compound.getCompound("Inventory"));
        }

        slot1Tick = compound.getInt("slot1Tick");
        slot2Tick = compound.getInt("slot2Tick");
        slot3Tick = compound.getInt("slot3Tick");
        slot4Tick = compound.getInt("slot4Tick");

        cookTime1 = slot1Tick = compound.getInt("cookTime1");
        cookTime2 = slot2Tick = compound.getInt("cookTime2");
        cookTime3 = slot3Tick = compound.getInt("cookTime3");
        cookTime4 = slot4Tick = compound.getInt("cookTime4");

        numbeforeExplode = compound.getInt("numbeforeExplode");
        beforeExplode = compound.getInt("beforeExplode");

        on = compound.getBoolean("on");
        isHot = compound.getBoolean("isHot");
        isHotTick = compound.getInt("isHotTick");
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handlerCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        super.tick();

        updateVirtualTick();
        updateForEachPanel();

        if (checkStructure()) {

            on = isOn();
            startChecker();
            explosionChecker();

            if (checkRequirement()){
                cookUpdate();
            }

        } else {
            for (int i = 0; i < 8; i++) {
                dropItemIfValid(itemHandler.getStackInSlot(i));
                itemHandler.setStackInSlot(i, ItemStack.EMPTY);
            }
            slot1Tick = slot2Tick = slot3Tick = slot4Tick = 0;
        }
    }



    protected int getSlotTick(int slot) {
        return switch (slot) {
            case 0 -> slot1Tick;
            case 1 -> slot2Tick;
            case 2 -> slot3Tick;
            case 3 -> slot4Tick;
            default -> 0;
        };
    }

    protected int getSlotOut(int slot) {
        return switch (slot) {
            case 0 -> 4;
            case 1 -> 5;
            case 2 -> 6;
            case 3 -> 7;
            default -> 4;
        };
    }

    protected void setSlotTick(int slot, int value) {
        switch (slot) {
            case 0 -> slot1Tick = value;
            case 1 -> slot2Tick = value;
            case 2 -> slot3Tick = value;
            case 3 -> slot4Tick = value;
        }
        notifyUpdate();
    }



    public Boolean checkStructure(){
        return ((getBlockInstanceCheck(0, -1, 0, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(1, -1, 0, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(-1, -1, 0, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(0, -1, 1, FluidPortBlock.class)
                && getBlockInstanceCheck(0, -1, -1, FluidPortBlock.class))
                ||(getBlockInstanceCheck(0, -1, 0, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(1, -1, 0, FluidPortBlock.class)
                && getBlockInstanceCheck(-1, -1, 0, FluidPortBlock.class)
                && getBlockInstanceCheck(0, -1, 1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(0, -1, -1, DiamondAlloyBlock.class)))
                && getBlockInstanceCheck(1, -1, 1, FluidPortBlock.class)
                && getBlockInstanceCheck(1, -1, -1, FluidPortBlock.class)
                && getBlockInstanceCheck(-1, -1, 1, FluidPortBlock.class)
                && getBlockInstanceCheck(-1, -1, -1, FluidPortBlock.class)
                //2
                && getBlockInstanceCheck(1, 0, 0, FurnaceBlock.class)
                && getBlockInstanceCheck(-1, 0, 0, FurnaceBlock.class)
                && getBlockInstanceCheck(0, 0, 1, FurnaceBlock.class)
                && getBlockInstanceCheck(0, 0, -1, FurnaceBlock.class)
                && getBlockInstanceCheck(0, 0, 0, ControllerBlock.class)
                && getBlockInstanceCheck(1, 0, 1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(1, 0, -1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(-1, 0, 1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(-1, 0, -1, DiamondAlloyBlock.class)
                //3
                && getBlockInstanceCheck(0, 1, 0, MechanicalPortBlock.class)
                && getBlockInstanceCheck(1, 1, 0, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(-1, 1, 0, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(0, 1, 1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(0, 1, -1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(1, 1, 1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(1, 1, -1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(-1, 1, 1, DiamondAlloyBlock.class)
                && getBlockInstanceCheck(-1, 1, -1, DiamondAlloyBlock.class)
                ;
    }

    public boolean checkRequirement() {
        return !isOverstressed() && isSpeedRequirementFulfilled() && isWaterInput() && isFuelInput() && isWaterOut()
                && canTakeFuel(ControllerEntity.FUEL_CONSUMER) && isHot
        ;
    }

    public boolean isOverstressed(){
        if (checkStructure()){
            MechanicalPortEntity mpe = (MechanicalPortEntity) getBlockEntityUp();
            return mpe.isOverStressed();
        }
        return true;
    }

    public boolean isSpeedRequirementFulfilled(){
        if (checkStructure()){
            MechanicalPortEntity mpe = (MechanicalPortEntity) getBlockEntityUp();
            return mpe.isSpeedRequirementFulfilled();
        }
        return false;
    }

    public boolean isWaterInput(){
        if (!checkStructure()) return false;

        List<FluidPortEntity> waterports = getFluidPortType(FluidPortEntity.WATER_TYPE);

        return waterports.size() == 2;
    }

    public boolean isFuelInput(){
        if (!checkStructure()) return false;

        List<FluidPortEntity> fuelports = getFluidPortType(FluidPortEntity.FUEL_TYPE);

        return fuelports.size() == 2;
    }

    public boolean isWaterOut(){
        if (!checkStructure()) return false;

        List<FluidPortEntity> outports = getFluidPortType(FluidPortEntity.OUT_TYPE);

        return outports.size() == 2;
    }

    public boolean canPutHotWater(int capacity){
        if (!isWaterOut()) return false;

        List<FluidPortEntity> outports = getFluidPortType(FluidPortEntity.OUT_TYPE);
        List<Boolean> bool = new ArrayList<>();

        if (outports.size() == 2){
            for (int i = 0; i<2; i++){
                FluidStack fluid = outports.get(i).getTank().getPrimaryHandler().getFluidInTank(0);

                if (fluid.isEmpty() && capacity <= outports.get(i).getTank().getPrimaryHandler().getCapacity()) bool.add(true);
                else if (capacity <= outports.get(i).getTank().getPrimaryHandler().getSpace()) bool.add(true);
                else bool.add(false);
            }

            return bool.get(0) && bool.get(1);
        }

        return false;
    }

    public boolean canTakeFuel(int capacity){
        if (!isFuelInput()) return false;

        List<FluidPortEntity> fuelports = getFluidPortType(FluidPortEntity.FUEL_TYPE);
        List<Boolean> bool = new ArrayList<>();

        if (fuelports.size() == 2){
            for (int i = 0; i<2; i++){
                FluidStack fluid = fuelports.get(i).getTank().getPrimaryHandler().getFluidInTank(0);

                if (fluid.isEmpty()) return false;
                if (capacity <= fuelports.get(i).getTank().getPrimaryHandler().getFluidAmount()
                        && fluid.getFluid().getFluidType().equals(CIF_fluids_Register.FUEL.getType())
                ) bool.add(true);
                else bool.add(false);
            }

            return bool.get(0) && bool.get(1);
        }

        return false;
    }

    public boolean canTakeWater(int capacity){
        if (!isWaterInput()) return false;

        List<FluidPortEntity> waterports = getFluidPortType(FluidPortEntity.WATER_TYPE);
        List<Boolean> bool = new ArrayList<>();

        if (waterports.size() == 2){
            for (int i = 0; i<2; i++){
                FluidStack fluid = waterports.get(i).getTank().getPrimaryHandler().getFluidInTank(0);

                if (fluid.isEmpty()) return false;
                if (capacity <= waterports.get(i).getTank().getPrimaryHandler().getFluidAmount()
                        && fluid.isFluidEqual(new FluidStack(Fluids.WATER, fluid.getAmount()))
                ) bool.add(true);
                else bool.add(false);
            }

            return bool.get(0) && bool.get(1);
        }

        return false;
    }

    public void putHotWater(int capacity){
        if (!isWaterOut()) return;

        List<FluidPortEntity> outports = getFluidPortType(FluidPortEntity.OUT_TYPE);

        if (outports.size() == 2){
            for (int i = 0; i<2; i++){
                outports.get(i).getTank().getPrimaryHandler().fill(new FluidStack(CIF_fluids_Register.HOT_WATER.get(), capacity), IFluidHandler.FluidAction.EXECUTE);
            }
        }
    }

    public void takeFuel(int capacity){
        if (!isFuelInput()) return;

        List<FluidPortEntity> fuelports = getFluidPortType(FluidPortEntity.FUEL_TYPE);

        if (fuelports.size() == 2){
            for (int i = 0; i<2; i++){
                fuelports.get(i).getTank().getPrimaryHandler().drain(capacity, IFluidHandler.FluidAction.EXECUTE);
            }
        }
    }

    public void takeWater(int capacity){
        if (!isWaterInput()) return;

        List<FluidPortEntity> waterports = getFluidPortType(FluidPortEntity.WATER_TYPE);

        if (waterports.size() == 2){
            for (int i = 0; i<2; i++){
                int content = waterports.get(i).getTank().getPrimaryHandler().getFluidAmount();
                waterports.get(i).getTank().getPrimaryHandler().setFluid(new FluidStack(Fluids.WATER, content - capacity));
            }
        }
    }

    public void notifyUpdate() {
        if (!level.isClientSide)
            sendData();
    }

    public ItemStackHandler getItemhandler(){
        return itemHandler;
    }

    public AbstractCookingRecipe getAllCookInfo(Container container, Level level) {
        for (RecipeType<? extends AbstractCookingRecipe> type : COOKING_TYPES) {
            var recipeOpt = level.getRecipeManager().getRecipeFor(type, container, level);
            if (recipeOpt.isPresent()) {
                return recipeOpt.get();
            }
        }
        return null;
    }



    private void dropItemIfValid(ItemStack stack) {
        if (stack != null && !stack.isEmpty()) {
            double x = worldPosition.getX() + 0.5;
            double y = worldPosition.getY() + 1.0;
            double z = worldPosition.getZ() + 0.5;
            if (level != null && !level.isClientSide) {
                Entity entity = new ItemEntity(level, x, y, z, stack.copy());
                level.addFreshEntity(entity);
            }
        }
    }

    private void updateVirtualSlots(){

        if (!level.isClientSide) return;

        for (int i = 0; i<8; i++){
            ItemStack slotStack = itemHandler.getStackInSlot(i);

            switch (i){
                case 0 -> slot1 = slotStack;
                case 1 -> slot2 = slotStack;
                case 2 -> slot3 = slotStack;
                case 3 -> slot4 = slotStack;
                case 4 -> slot5 = slotStack;
                case 5 -> slot6 = slotStack;
                case 6 -> slot7 = slotStack;
                case 7 -> slot8 = slotStack;
            }
        }
    }

    private void setCookTime(int slot){

        if (slot == 4 || slot == 5 || slot == 6 || slot == 7) return;

        ItemStack slotStack = itemHandler.getStackInSlot(slot);
        SimpleContainer container = new SimpleContainer(slotStack);
        AbstractCookingRecipe recipe = getAllCookInfo(container, level);
        int recipeTime = recipe!=null ? recipe.getCookingTime() / COOK_TIME : 0;

        switch (slot){
            case 0 -> cookTime1 = recipeTime;
            case 1 -> cookTime2 = recipeTime;
            case 2 -> cookTime3 = recipeTime;
            case 3 -> cookTime4 = recipeTime;
        }
    }

    private void updateVirtualTick(){
        if (tick >= 10){

            updateVirtualSlots();

            explode = numbeforeExplode >= 4;

            tick = 0;
        }else {
            tick ++;
        }
    }

    private void startChecker(){
        if (isHotTick >= 400 && on && !isHot){
            isHot= true;
        } else if (isHotTick >= 100 && !on && isHot) {
            isHot= false;
        }

        if ((!isHot && on) || (isHot && !on)){
            isHotTick ++;
        }else {
            isHotTick = 0;
        }
    }

    private void explosionChecker(){

        if (beforeExplode >= 200){
            if (level.isClientSide() && alertExplosion != null){
                ClientSounds.stopAlertSound(alertExplosion);
            }

            level.explode(null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 30, Level.ExplosionInteraction.TNT);
        }

        if (numbeforeExplode >= 4){
            beforeExplode ++;
            if (soundPlay && isThereBell()){
                if (level.isClientSide()){
                    alertExplosion = ClientSounds.playAlertSound(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ());
                }
                soundPlay = false;
            }
        }else {
            beforeExplode = 0;
            if (!soundPlay || !isThereBell()){
                if (level.isClientSide() && alertExplosion != null) {
                    ClientSounds.stopAlertSound(alertExplosion);
                }
            }
            soundPlay = true;
        }
    }

    private void cookUpdate(){
        for (int i = 0; i < 4; i++) {

            int recipeTime = 0;
            switch (i){
                case 0 -> recipeTime = cookTime1;
                case 1 -> recipeTime = cookTime2;
                case 2 -> recipeTime = cookTime3;
                case 3 -> recipeTime = cookTime4;
                default -> recipeTime = 0;
            }

            ItemStack slotStack = itemHandler.getStackInSlot(i);

            int slotTick = getSlotTick(i);
            ItemStack outputSlot = itemHandler.getStackInSlot(getSlotOut(i));

            if (slotStack != null && !slotStack.isEmpty() && recipeTime != 0) {
                if (slotTick >= recipeTime) {

                    ItemStack out = FurnaceBlock.getAnyCookingResult(level, slotStack);

                    if (!out.isEmpty() && canTakeFuel(ControllerEntity.FUEL_CONSUMER)) {

                        setSlotTick(i, 0);

                        SimpleContainer container = new SimpleContainer(slotStack);
                        AbstractCookingRecipe recipe = getAllCookInfo(container, level);

                        int count = recipe.getResultItem(RegistryAccess.EMPTY).getCount();

                        ItemStack cooked = out.copyWithCount(count);

                        boolean recipeEnd = false;

                        if (outputSlot.isEmpty()) {
                            itemHandler.setStackInSlot(getSlotOut(i), cooked);
                            recipeEnd = true;
                        } else if (ItemStack.isSameItemSameTags(outputSlot, cooked) && outputSlot.getCount() + count <= outputSlot.getMaxStackSize()) {
                            outputSlot.grow(count);
                            itemHandler.setStackInSlot(getSlotOut(i), outputSlot);
                            recipeEnd = true;
                        }

                        if (recipeEnd) {

                            if(isThereBell()){
                                level.playSound(null,
                                        getBlockPos(),
                                        CIF_sound_Event_Register.SMELT_END.get(),
                                        SoundSource.BLOCKS,
                                        1.0f,
                                        1.0f);
                            }

                            if (canTakeWater(WATER_CONSUMER) && canPutHotWater(ControllerEntity.WATER_CONSUMER)){
                                numbeforeExplode = 0;
                            }else {
                                if (!canTakeWater(WATER_CONSUMER)){
                                    numbeforeExplode ++;
                                }
                                if (!canPutHotWater(ControllerEntity.WATER_CONSUMER)){
                                    numbeforeExplode ++;

                                }
                            }

                            takeWater(ControllerEntity.WATER_CONSUMER);
                            takeFuel(ControllerEntity.FUEL_CONSUMER);
                            putHotWater(ControllerEntity.WATER_CONSUMER);

                            itemHandler.setStackInSlot(i, slotStack.copyWithCount(slotStack.getCount() - 1));
                        }
                    }
                }
            }
            if (recipeTime == 0 || slotStack.isEmpty()) {
                setSlotTick(i, 0);
            }else if (outputSlot.getCount() < outputSlot.getMaxStackSize()){
                setSlotTick(i, getSlotTick(i) + 1);
            }
        }
    }

    private boolean isThereBell(){
        for (int x = -2; x<3; x++){
            for (int z = -2; z<3; z++){
                for (int y = -2; y<3; y++){
                    if (
                            !((x==-2 || x==2) && (y==-2 || y==2))
                                    &&!((z==-2 || z==2) && (y==-2 || y==2))
                                    &&!((x==-2 || x==2) && (z==-2 || z==2))
                    ){

                        BlockPos pos = new BlockPos(getBlockPos().getX() + x, getBlockPos().getY() + y, getBlockPos().getZ() + z);
                        BlockState state = level.getBlockState(pos);
                        Block block = state.getBlock();
                        if ((block instanceof BellBlock)) return true;

                    }
                }
            }
        }

        return false;
    }

    private void updateForEachPanel(){
        for (int x = -2; x<3; x++){
            for (int z = -2; z<3; z++){
                for (int y = -1; y<2; y++){
                    if (
                            !((x==-2 || x==2) && (y==-2 || y==2))
                            &&!((z==-2 || z==2) && (y==-2 || y==2))
                            &&!((x==-2 || x==2) && (z==-2 || z==2))
                    ){

                        BlockPos pos = new BlockPos(getBlockPos().getX() + x, getBlockPos().getY() + y, getBlockPos().getZ() + z);
                        BlockEntity be = level.getBlockEntity(pos);
                        if ((be instanceof ControlPanelEntity cpbe)){
                            List<ItemStack> stacks = new ArrayList<>();
                            if (cpbe.isInputView()){
                                stacks.add(slot1);
                                stacks.add(slot2);
                                stacks.add(slot3);
                                stacks.add(slot4);
                                cpbe.setVirtualSlot(stacks);
                            }else if (cpbe.isOutputView()) {
                                stacks.add(slot5);
                                stacks.add(slot6);
                                stacks.add(slot7);
                                stacks.add(slot8);
                                cpbe.setVirtualSlot(stacks);
                            }
                        }

                    }
                }
            }
        }
    }

    private boolean isOn(){
        for (int x = -1; x<2; x++){
            for (int z = -1; z<2; z++) {
                for (int y = -1; y<2; y++) {
                    BlockPos offset = new BlockPos(getBlockPos().getX() + x, getBlockPos().getY() +y, getBlockPos().getZ() + z);
                    BlockState state = level.getBlockState(offset);
                    Block block = state.getBlock();
                    if (block instanceof DiamondAlloyBlock diamondAlloyBlock) {
                        if (state.getValue(BlockStateProperties.POWERED)) return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean getBlockInstanceCheck(int dx, int dy, int dz, Class<? extends Block> instance) {
        return instance.isInstance(getBlockInstance(dx, dy, dz));
    }

    private BlockEntity getBlockEntityUp(){
        return level.getBlockEntity(worldPosition.above());
    }

    private List<FluidPortEntity> getFluidPortType(String type) {
        List<FluidPortEntity> list = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                BlockPos offset = new BlockPos(getBlockPos().getX() + x, getBlockPos().getY() - 1, getBlockPos().getZ() + z);
                BlockEntity entity = getLevel().getBlockEntity(offset);
                if (entity instanceof FluidPortEntity fluidPortEntity) {

                    if (fluidPortEntity.block_Type.equals(type)) {
                        list.add(fluidPortEntity);
                    }
                }
            }
        }
        return list;
    }

    private Block getBlockInstance(int dx, int dy, int dz) {
        BlockPos offset = new BlockPos(getBlockPos().getX() + dx, getBlockPos().getY() + dy, getBlockPos().getZ() + dz);
        Block block = getLevel().getBlockState(offset).getBlock();
        return block;
    }

}