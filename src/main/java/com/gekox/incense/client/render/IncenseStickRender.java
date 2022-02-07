package com.gekox.incense.client.render;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import com.gekox.incense.common.block.IncenseStickBE;
import com.gekox.incense.setup.Registration;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

public class IncenseStickRender implements BlockEntityRenderer<IncenseStickBE> {

	public static final ResourceLocation TEXTURE_SIDE = new ResourceLocation(Constants.MODID, "block/incense_stick_side");
	public static final ResourceLocation TEXTURE_TOP = new ResourceLocation(Constants.MODID, "block/incense_stick_top");
	
	public IncenseStickRender(BlockEntityRendererProvider.Context context) {
		
	}
	
	public static void Register() {
		BlockEntityRenderers.register(Registration.BE_INCENSE_STICK.get(), IncenseStickRender::new);
		ModEntry.LOGGER.info("Registered IncenseStickRender");
	}
	
	@Override
	public void render(IncenseStickBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		
		pPoseStack.pushPose();
		
		renderStick(pBlockEntity, pPoseStack, pBufferSource);
		
		pPoseStack.popPose();
	}
	
	private void renderStick(IncenseStickBE stickBE, PoseStack poseStack, MultiBufferSource bufferSource) {
		
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(TEXTURE_SIDE);
		VertexConsumer buffer = bufferSource.getBuffer(RenderType.translucent());
		Matrix4f matrix4f = poseStack.last().pose();
		
		// Bounds
		float w = 0.1f;
		float h = 0.8f;// * burnPerc;

		float x1 = 0.5f - (w / 2);
		float y1 = 0.0f;
		float z1 = 0.5f - (w / 2);

		float x2 = x1 + w;
		float y2 = y1 + h;
		float z2 = z1 + w;

		poseStack.translate(0, 1.5, 0);
		
		// Top
		buffer.vertex(matrix4f, x1, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0))   .uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0.25)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0.25)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(0))   .uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();

		// Sides
		buffer.vertex(matrix4f, x1, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		
		buffer.vertex(matrix4f, x2, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		
		buffer.vertex(matrix4f, x1, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		
		buffer.vertex(matrix4f, x1, y2, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(0)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y2, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(1)).uv2(LightTexture.FULL_BRIGHT).normal(0, 1, 0).endVertex();

		// Bottom
		buffer.vertex(matrix4f, x2, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(.25)).uv2(LightTexture.FULL_BRIGHT).normal(0, -1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y1, z2).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(.25)).uv2(LightTexture.FULL_BRIGHT).normal(0, -1, 0).endVertex();
		buffer.vertex(matrix4f, x1, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(0), sprite.getV(0))  .uv2(LightTexture.FULL_BRIGHT).normal(0, -1, 0).endVertex();
		buffer.vertex(matrix4f, x2, y1, z1).color(1f, 1f, 1f, 1f).uv(sprite.getU(1), sprite.getV(0))  .uv2(LightTexture.FULL_BRIGHT).normal(0, -1, 0).endVertex();

	}
}
