package com.gekox.incense.datagen;

import com.gekox.incense.Constants;
import com.gekox.incense.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTags extends BlockTagsProvider {

	public ModBlockTags(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, Constants.MODID, helper);
	}

	@Override
	protected void addTags() {
		// Vallina uses BlockTags class
//		tag(BlockTags.CAMPFIRES)
//				.add(Registration.BLOCK_INCENSE_STICK.get());
		
		
		// Forge uses Tags.Blocks
//		tag(Tags.Blocks.STONE)
//				.add(Registration.BLOCK_TEST_BLOCK.get());
	}

	@Override
	public String getName() {
		return Constants.MODID + " Tags";
	}
}