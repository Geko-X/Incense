package com.gekox.incense.datagen;

import com.gekox.incense.Constants;
import com.gekox.incense.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTags extends ItemTagsProvider {

	public ModItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
		super(generator, blockTags, Constants.MODID, helper);
	}

	@Override
	protected void addTags() {
		tag(Registration.TAG_INCENSE_PASTE)
//				.add(Registration.ITEM_INCENSE_PASTE.get());
				.add(Registration.ITEM_INCENSE_PASTE_SOOTY.get())
				.add(Registration.ITEM_INCENSE_PASTE_PASSIVE.get())
				.add(Registration.ITEM_INCENSE_PASTE_HOSTILE.get())
				.add(Registration.ITEM_INCENSE_PASTE_NEUTRAL.get())
				.add(Registration.ITEM_INCENSE_PASTE_ENDER.get())
				.add(Registration.ITEM_INCENSE_PASTE_NETHER.get())
				.add(Registration.ITEM_INCENSE_PASTE_VILLAGE.get())
				.add(Registration.ITEM_INCENSE_PASTE_ILLAGE.get())
				.add(Registration.ITEM_INCENSE_PASTE_WATER.get())
				.add(Registration.ITEM_INCENSE_PASTE_GOLEM.get());
	}

	@Override
	public String getName() {
		return Constants.MODID + " Tags";
	}
}