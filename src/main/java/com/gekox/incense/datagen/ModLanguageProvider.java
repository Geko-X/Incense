package com.gekox.incense.datagen;

import com.gekox.incense.Constants;
import com.gekox.incense.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static com.gekox.incense.setup.ModSetup.TAB_NAME;

public class ModLanguageProvider extends LanguageProvider {

	public ModLanguageProvider(DataGenerator gen, String locale) {
		super(gen, Constants.MODID, locale);
	}
	
	@Override
	protected void addTranslations() {
		
		add("incense." + Constants.Names.NAME_INCENSE_PASTE, "Incense Paste");
		add("incense.type.sooty", "Sooty");
		add("incense.type.passive", "Passive");
		add("incense.type.hostile", "Hostile");
		add("incense.type.neutral", "Neutral");
		add("incense.type.ender", "Ender");
		add("incense.type.nether", "Nether");
		add("incense.type.village", "Villager");
		add("incense.type.illage", "Illager");
		add("incense.type.water", "Water");
		add("incense.type.golem", "Golem");
		
		add("itemGroup." + TAB_NAME, "Incense");

		add(Constants.Lang.SHIFT, "SHIFT");
		add(Constants.Lang.USE, "USE");
		add(Constants.Lang.BURN, "BURN");
		
		add(Constants.Lang.TOOLTIP_HOLD_SHIFT, "Hold %s for more info");
		add(Constants.Lang.TOOLTIP_PASTE_INSTRUCTIONS, "%s on a placed %s");
		add(Constants.Lang.TOOLTIP_PASTE_SHORT_DESC, "This paste will attract %s type cretures");
		add(Constants.Lang.TOOLTIP_PASTE_HEADER, "Will attract the following mobs");
		add(Constants.Lang.TOOLTIP_PASTE_NONE, "This paste will not attact mobs. ");
		add(Constants.Lang.TOOLTIP_PASTE_NONE2, "Use it to plan out spawning spaces!");
		
		add(Constants.Lang.TOOLTIP_STICK_INSTRUCTIONS, "Place in world and %s %s on it, then %s");

		add(Registration.BLOCK_INCENSE_STICK.get(), "Incense Stick");
//		add(Registration.ITEM_INCENSE_STICK.get(), "Incense Stick");
//		add(Registration.BLOCK_TEST_BLOCK.get(), "Test Block");

		add(Registration.ITEM_MORTAR_PESTLE.get(), "Mortar and Pestle");
//		add(Registration.ITEM_INCENSE_PASTE.get(), "Incense Paste");
		
		add(Registration.ITEM_INCENSE_PASTE_SOOTY.get(), "Sooty Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_PASSIVE.get(), "Passive Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_HOSTILE.get(), "Hostile Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_NEUTRAL.get(), "Neutral Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_ENDER.get(), "Ender Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_NETHER.get(), "Nether Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_VILLAGE.get(), "Villager Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_ILLAGE.get(), "Illager Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_WATER.get(), "Water Incense Paste");
		add(Registration.ITEM_INCENSE_PASTE_GOLEM.get(), "Golem Incense Paste");
	}
}
