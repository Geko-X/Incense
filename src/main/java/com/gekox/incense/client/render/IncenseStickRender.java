package com.gekox.incense.client.render;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import com.gekox.incense.common.block.IncenseStickBE;
import com.gekox.incense.setup.Registration;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IncenseStickRender <T extends IncenseStickBE> extends BlockEntityWithoutLevelRenderer implements BlockEntityRenderer<T>  {

	public static final ResourceLocation TEXTURE_SIDE = new ResourceLocation(Constants.MODID, "block/incense_stick_side");
	public static final ResourceLocation TEXTURE_TOP = new ResourceLocation(Constants.MODID, "block/incense_stick_top");

	public static final ResourceLocation TEXTURE = new ResourceLocation(Constants.MODID, "block/incense_stick");
	public static final ResourceLocation TEXTURE_UV = new ResourceLocation(Constants.MODID, "block/uv_test");

	private static final float ONE_EIGTH = 1 / 8f;
	private static final float ONE_SIXTEENTH = 1 / 16f;
	
	public static IncenseStickRender INSTANCE;
	
	public IncenseStickRender(BlockEntityRendererProvider.Context context) {
		super(context.getBlockEntityRenderDispatcher(), context.getModelSet());
		INSTANCE = this;
	}

	public IncenseStickRender(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
		super(pBlockEntityRenderDispatcher, pEntityModelSet);
	}

	public static void Register() {
		BlockEntityRenderers.register(Registration.BE_INCENSE_STICK.get(), IncenseStickRender::new);
		ModEntry.LOGGER.info("Registered IncenseStickRender");
	}

	public static RenderType RenderType() {
		return RenderType.solid();
	}

	public void renderFromItem(ItemStack stack, T blockEntity, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		renderStick(blockEntity, 0, poseStack, bufferSource, packedLight, packedOverlay);
	}
	
	@Override
	public void render(T pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		pPoseStack.pushPose();
		renderStick(pBlockEntity, pPartialTick, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
		pPoseStack.popPose();
	}
	
	
	private void renderStick(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		
		VertexConsumer buffer = bufferSource.getBuffer(RenderType());
		Matrix4f matrix = poseStack.last().pose();
		
		float burnPerc = blockEntity.getBurnPercent();
		
		// Bounds
		float w = ONE_EIGTH;
		float h = 0.8f * burnPerc;

		float x1 = 0.5f - w;
		float y1 = 0.0f;
		float z1 = 0.5f - w;

		float x2 = 0.5f + w;
		float y2 = y1 + h;
		float z2 = 0.5f + w;

		double u0 = 4;
		double v0 = 4;
		double u1 = 8;
		double v1 = 8;

	//	poseStack.translate(0, 1.5, 0);
		
		int brightness = LightTexture.FULL_BRIGHT;

		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(TEXTURE);
		
		// Top
		buffer.vertex(matrix, x1, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v0)).uv2(brightness).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix, x1, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v1)).uv2(brightness).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix, x2, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v1)).uv2(brightness).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix, x2, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v0)).uv2(brightness).normal(0, 1, 0).endVertex();

		// Bottom
		buffer.vertex(matrix, x2, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v1)).uv2(brightness).normal(0, -1, 0).endVertex();
		buffer.vertex(matrix, x1, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v1)).uv2(brightness).normal(0, -1, 0).endVertex();
		buffer.vertex(matrix, x1, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v0)).uv2(brightness).normal(0, -1, 0).endVertex();
		buffer.vertex(matrix, x2, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v0)).uv2(brightness).normal(0, -1, 0).endVertex();

//		sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(TEXTURE_SIDE);

		u0 = 6;
		v0 = 0;
		u1 = 10;
		v1 = 16;// - (Constants.MAX_BURN_HEIGHT - 0 + 1) * 2;;
		
		// Sides
		buffer.vertex(matrix, x1, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v0)).uv2(brightness).normal(0, 0, 1).endVertex();
		buffer.vertex(matrix, x1, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v1)).uv2(brightness).normal(0, 0, 1).endVertex();
		buffer.vertex(matrix, x2, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v1)).uv2(brightness).normal(0, 0, 1).endVertex();
		buffer.vertex(matrix, x2, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v0)).uv2(brightness).normal(0, 0, 1).endVertex();
		
		buffer.vertex(matrix, x2, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v0)).uv2(brightness).normal(1, 0, 0).endVertex();
		buffer.vertex(matrix, x2, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v1)).uv2(brightness).normal(1, 0, 0).endVertex();
		buffer.vertex(matrix, x2, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v1)).uv2(brightness).normal(1, 0, 0).endVertex();
		buffer.vertex(matrix, x2, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v0)).uv2(brightness).normal(1, 0, 0).endVertex();

		buffer.vertex(matrix, x1, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v1)).uv2(brightness).normal(0, 0, -1).endVertex();
		buffer.vertex(matrix, x1, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v0)).uv2(brightness).normal(0, 0, -1).endVertex();
		buffer.vertex(matrix, x2, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v0)).uv2(brightness).normal(0, 0, -1).endVertex();
		buffer.vertex(matrix, x2, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v1)).uv2(brightness).normal(0, 0, -1).endVertex();

		buffer.vertex(matrix, x1, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v1)).uv2(brightness).normal(-1, 0, 0).endVertex();
		buffer.vertex(matrix, x1, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(u0), sprite.getV(v0)).uv2(brightness).normal(-1, 0, 0).endVertex();
		buffer.vertex(matrix, x1, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v0)).uv2(brightness).normal(-1, 0, 0).endVertex();
		buffer.vertex(matrix, x1, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(u1), sprite.getV(v1)).uv2(brightness).normal(-1, 0, 0).endVertex();

		
	}

	private void renderFace(T pBlockEntity, Matrix4f pPose, VertexConsumer pConsumer, float pX0, float pX1, float pY0, float pY1, float pZ0, float pZ1, Direction pDirection) {
		pConsumer.vertex(pPose, pX0, pY0, pZ1).color(1, 1, 1, 1).uv(0, 0).uv2(LightTexture.FULL_BRIGHT).normal(pDirection.getStepX(), pDirection.getStepY(), pDirection.getStepZ()).endVertex();
		pConsumer.vertex(pPose, pX1, pY0, pZ0).color(1, 1, 1, 1).uv(0, 0).uv2(LightTexture.FULL_BRIGHT).normal(pDirection.getStepX(), pDirection.getStepY(), pDirection.getStepZ()).endVertex();
		pConsumer.vertex(pPose, pX1, pY0, pZ0).color(1, 1, 1, 1).uv(0, 0).uv2(LightTexture.FULL_BRIGHT).normal(pDirection.getStepX(), pDirection.getStepY(), pDirection.getStepZ()).endVertex();
		pConsumer.vertex(pPose, pX0, pY0, pZ1).color(1, 1, 1, 1).uv(0, 0).uv2(LightTexture.FULL_BRIGHT).normal(pDirection.getStepX(), pDirection.getStepY(), pDirection.getStepZ()).endVertex();
	}
}
