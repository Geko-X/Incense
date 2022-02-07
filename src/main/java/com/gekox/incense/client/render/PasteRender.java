package com.gekox.incense.client.render;

import com.gekox.incense.common.item.PasteItem;
import com.gekox.incense.util.Color;
import com.gekox.incense.util.IncenseType;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

public class PasteRender implements ItemColor {
	
	@Override
	public int getColor(ItemStack pStack, int pTintIndex) {
		if(!(pStack.getItem() instanceof PasteItem))
			return 0;
		
		if(pTintIndex != 0)
			return 0;
		
		PasteItem paste = (PasteItem)pStack.getItem();
		
		IncenseType incenseType = paste.getIncenseType();
	//	ModEntry.LOGGER.info(incenseType);
		
		return Color.getColorFromType(incenseType).toInt();
	}
}
