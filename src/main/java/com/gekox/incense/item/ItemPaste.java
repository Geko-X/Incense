package com.gekox.incense.item;

import com.gekox.incense.util.Color;
import com.gekox.incense.util.IncenseType;
import com.google.common.collect.Maps;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class ItemPaste extends Item {
	
	private static final Map<IncenseType, ItemPaste> ITEM_BY_INCENSE = Maps.newEnumMap(IncenseType.class);
	protected IncenseType _incenseType = IncenseType.NONE;
	
	public ItemPaste(Properties pProperties) {
		super(pProperties);
	}
	
	public ItemPaste(Properties pProperties, IncenseType incenseType) {
		super(pProperties);
		this._incenseType = incenseType;
		ITEM_BY_INCENSE.put(incenseType, this);
	}

	public IncenseType getIncenseType() {
		return this._incenseType;
	}

	public static ItemPaste byIncenseType(IncenseType incenseType) {
		return ITEM_BY_INCENSE.get(incenseType);
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

		ItemPaste paste = (ItemPaste)pStack.getItem();
		IncenseType incenseType = paste.getIncenseType();
		
		if(incenseType == IncenseType.NONE) {
			pTooltipComponents.add(new TranslatableComponent("tooltip.incense.paste.none"));
			pTooltipComponents.add(new TranslatableComponent("tooltip.incense.paste.none2"));
			return;
		}
		
		else {

			Style style = Style.EMPTY;
			style.withColor(Color.getColorFromType(incenseType).toInt());
			
			String colorStr = String.format("#%06X", Color.getColorFromType(incenseType).toInt());
			
			String translatedType = new TranslatableComponent("incense.type." + incenseType.toString()).getString();
			String finalString = String.format("%s%s\u00A7r", colorStr, translatedType);

		//	TextComponent textComponent = new TextComponent()
			
			pTooltipComponents.add(new TranslatableComponent("tooltip.incense.paste.short_desc", finalString));
			
			
			if (Screen.hasShiftDown()) {
//				pTooltipComponents.add(String.format("%sWill attract the following mobs:", Color.CODE_YELLOW));
//				IncenseMobSpawn.getMobListFromType(type).forEach(name -> tooltip.add(String.format("%s", name)));
				pTooltipComponents.add(new TranslatableComponent("tooltip.incense.paste.header"));
			}

			else {
				pTooltipComponents.add(new TranslatableComponent("tooltip.incense.holdshift"));
			}
		}

		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
	}
	
}
