package com.gekox.incense;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class Constants {

	public static final String MODID = "incense";
	public static final int MAX_BURN_HEIGHT = 7;
	
	public static class Names {
		public static final String NAME_TEST_BLOCK = "block";
		public static final String NAME_INCENSE_STICK = "incense_stick";
		public static final String NAME_MORTAR_PESTLE = "mortar_pestle";
		public static final String NAME_INCENSE_PASTE = "incense_paste";
	}
	
	public static class NBT {
		public static final String INCENSE_TYPE = "incense.type";
		public static final String COLOR_R = "incense.color.r";
		public static final String COLOR_G = "incense.color.g";
		public static final String COLOR_B = "incense.color.b";
		public static final String IS_BURNING = "incense.burning";
	}
	
	public static class Lang {
		public static final String TOOLTIP_HOLD_SHIFT = "tooltip.incense.holdshift";
		public static final String TOOLTIP_PASTE_INSTRUCTIONS = "tooltip.incense.paste.instructions";
		public static final String TOOLTIP_PASTE_SHORT_DESC = "tooltip.incense.paste.short_desc";
		public static final String TOOLTIP_PASTE_HEADER = "tooltip.incense.paste.header";
		public static final String TOOLTIP_PASTE_NONE = "tooltip.incense.paste.none";
		public static final String TOOLTIP_PASTE_NONE2 = "tooltip.incense.paste.none2";
		public static final String TOOLTIP_STICK_INSTRUCTIONS = "tooltip.incense.stick.instructions";

		public static final String SHIFT = "tooltip.shift";
		public static final String USE = "tooltip.use";
		public static final String BURN = "tooltip.burn";

		public static final MutableComponent ACTION_SHIFT = new TranslatableComponent(SHIFT).withStyle(ChatFormatting.YELLOW);
		public static final MutableComponent ACTION_USE = new TranslatableComponent(USE).withStyle(ChatFormatting.YELLOW);
		public static final MutableComponent ACTION_BURN = new TranslatableComponent(BURN).withStyle(ChatFormatting.GOLD);
		
	}
	
	
}
