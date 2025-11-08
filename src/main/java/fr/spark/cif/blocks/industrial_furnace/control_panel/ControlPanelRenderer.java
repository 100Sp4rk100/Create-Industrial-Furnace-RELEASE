package fr.spark.cif.blocks.industrial_furnace.control_panel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import java.util.List;

public class ControlPanelRenderer implements BlockEntityRenderer<ControlPanelEntity> {

    public ControlPanelRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(ControlPanelEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        //poseStack.translate(x, y, z)
        // Déplace l’origine du rendu de (x, y, z)

        //poseStack.scale(x, y, z)
        //Agrandit ou rétrécit le rendu

        //poseStack.mulPose(rotation)
        //Applique une rotation
        //Axis.XP → axe X positif (de gauche à droite)
        //Axis.YP → axe Y positif (vertical)
        //Axis.ZP → axe Z positif (profondeur)
        //XP = axe positif
        //XN = axe négatif

        //X: east-west position.
        //Positive values => east
        // Negative values => west

        //Z: north-south position.
        //Positive values => south
        //Negative values => north

        //Y: vertical position

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        List<ItemStack> itemStacks = entity.getRenderStack();

        float pixelSize = 0.0625f; // 1/16
        float slotSize = 5 * pixelSize;
        float border = 2 * pixelSize;
        float start = border;

        for (int i = 0; i<itemStacks.size(); i++){
            poseStack.pushPose();

            int col = i % 2;
            int row = i / 2;

            float x = start + col * (slotSize + border);
            float y = start + row * (slotSize + border);

            float centerX = x + slotSize / 2f;
            float centerY = y + slotSize / 2f;

            Direction face = entity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);

            switch (face) {
                case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180));
                case WEST  -> poseStack.mulPose(Axis.YP.rotationDegrees(90));
                case EAST  -> poseStack.mulPose(Axis.YP.rotationDegrees(270));
            }

            switch (face) {
                case NORTH -> poseStack.translate(centerX, 1.0f - centerY, 1.0f -3.0f*pixelSize);
                case SOUTH -> poseStack.translate(-1.0f + centerX, 1.0f - centerY, -3.0f*pixelSize);
                case WEST  -> poseStack.translate(-1.0f + centerX, 1.0f - centerY, 1.0f -3.0f*pixelSize);
                case EAST  -> poseStack.translate(centerX, 1.0f - centerY, -3.0f*pixelSize);
            }

            poseStack.scale(0.3f, 0.3f, 0.3f);

            itemRenderer.renderStatic(
                    itemStacks.get(i),
                    ItemDisplayContext.FIXED,
                    getLightLevel(entity.getLevel(),
                    entity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    buffer,
                    entity.getLevel(),
                    1
            );
            poseStack.popPose();
        }

    }

    private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);

        return LightTexture.pack(bLight, sLight);
    }
}
