package com.gekox.incense.common.item;

import com.gekox.incense.Constants;
import com.gekox.incense.setup.Registration;
import com.gekox.incense.util.Color;
import com.gekox.incense.util.IncenseType;
import com.google.common.collect.Maps;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class PasteItem extends Item {
	
	private static final Map<IncenseType, PasteItem> ITEM_BY_INCENSE = Maps.newEnumMap(IncenseType.class);
	protected IncenseType _incenseType = IncenseType.NONE;
	
	public PasteItem(Properties pProperties) {
		super(pProperties);
	}
	
	public PasteItem(Properties pProperties, IncenseType incenseType) {
		super(pProperties);
		this._incenseType = incenseType;
		ITEM_BY_INCENSE.put(incenseType, this);
	}

	public IncenseType getIncenseType() {
		return this._incenseType;
	}

	public static PasteItem byIncenseType(IncenseType incenseType) {
		return ITEM_BY_INCENSE.get(incenseType);
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

		PasteItem paste = (PasteItem)pStack.getItem();
		IncenseType incenseType = paste.getIncenseType();

		// "USE on a placed %s"
		//var line = Constants.Lang.ACTION_USE.copy();
		//line.append(
		var line = new TranslatableComponent(Constants.Lang.TOOLTIP_PASTE_INSTRUCTIONS, 
					Constants.Lang.ACTION_USE,
					Registration.BLOCK_INCENSE_STICK.get().getName())
				.withStyle(ChatFormatting.BLUE);
		pTooltipComponents.add(line);
		
		if(incenseType == IncenseType.SOOTY) {
			line = new TranslatableComponent(Constants.Lang.TOOLTIP_PASTE_NONE).append(new TranslatableComponent(Constants.Lang.TOOLTIP_PASTE_NONE2));
			pTooltipComponents.add(line);
			return;
		}
		
		else {

			Style style = Style.EMPTY;
			style.withColor(Color.getColorFromType(incenseType).toInt());
			var typeComponent = new TranslatableComponent("incense.type." + incenseType.toString()).withStyle(style);
			
			// This paste will attract %s type cretures
			pTooltipComponents.add(new TranslatableComponent(Constants.Lang.TOOLTIP_PASTE_SHORT_DESC, typeComponent));
			
			
			if (Screen.hasShiftDown()) {
//				pTooltipComponents.add(String.format("%sWill attract the following mobs:", Color.CODE_YELLOW));
//				IncenseMobSpawn.getMobListFromType(type).forEach(name -> tooltip.add(String.format("%s", name)));
				pTooltipComponents.add(new TranslatableComponent(Constants.Lang.TOOLTIP_PASTE_HEADER));
			}

			else {
				pTooltipComponents.add(new TranslatableComponent(Constants.Lang.TOOLTIP_HOLD_SHIFT, Constants.Lang.ACTION_SHIFT)
					.withStyle(ChatFormatting.BLUE));
			}
		}

		super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
	}
	
}
