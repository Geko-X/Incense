package com.gekox.incense.common.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class IncenseStickItem extends Item {

	public IncenseStickItem(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {

			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
				return myBEWLRInstance;
			}
		});
	}
}
