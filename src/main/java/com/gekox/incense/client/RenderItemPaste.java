package com.gekox.incense.client;

import com.gekox.incense.ModEntry;
import com.gekox.incense.item.ItemPaste;
import com.gekox.incense.util.Color;
import com.gekox.incense.util.IncenseType;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;

public class RenderItemPaste implements ItemColor {
	
	@Override
	public int getColor(ItemStack pStack, int pTintIndex) {
		if(!(pStack.getItem() instanceof ItemPaste))
			return 0;
		
		if(pTintIndex != 0)
			return 0;
		
		ItemPaste paste = (ItemPaste)pStack.getItem();
		
		IncenseType incenseType = paste.getIncenseType();
	//	ModEntry.LOGGER.info(incenseType);
		
		return Color.getColorFromType(incenseType).toInt();
	}
}
