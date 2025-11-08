package fr.spark.cif.fluids.fuel;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.simibubi.create.AllFluids;
import fr.spark.cif.Cif;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class FuelAttributes extends AllFluids.TintedFluidType {

    public static final ResourceLocation STILL = Cif.id("fluid/fuel_still");
    public static final ResourceLocation FLOW = Cif.id("fluid/fuel_flow");

    public FuelAttributes(Properties properties, ResourceLocation stillTexture,
                          ResourceLocation flowingTexture) {
        super(properties, stillTexture, flowingTexture);
    }

    @Override
    protected int getTintColor(FluidStack stack) {
        return NO_TINT;
    }

    @Override
    public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
        return NO_TINT;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return new Vector3f(0.4f, 0.05f, 0.05f);
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(0.0f);
                RenderSystem.setShaderFogEnd(2.0f);
            }

            @Override
            public ResourceLocation getStillTexture() {
                return STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return FLOW;
            }

        });
    }
}
