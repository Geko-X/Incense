package com.gekox.incense.common.item;

import com.gekox.incense.client.render.IncenseStickRender;
import com.gekox.incense.common.block.IncenseStickBE;
import com.gekox.incense.setup.Registration;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.util.NonNullLazy;

import java.util.function.Consumer;

public class IncenseStickItem extends BlockItem {

	public IncenseStickItem(Block block, Item.Properties pProperties) {
		super(block, pProperties);
	}
	
	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		if (Minecraft.getInstance() == null) return;
		
		consumer.accept(new IItemRenderProperties() {

			static final NonNullLazy<BlockEntityWithoutLevelRenderer> renderer = NonNullLazy.of(() -> new BlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()) {

				final IncenseStickBE stickBE = new IncenseStickBE(BlockPos.ZERO, Registration.BLOCK_INCENSE_STICK.get().defaultBlockState());

				@Override
				public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
					IncenseStickRender.INSTANCE.renderFromItem(itemStackIn, stickBE, transformType, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
				}
			});

			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				return renderer.get();
			}
		});
	}
}