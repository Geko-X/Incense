package com.gekox.incense.datagen;

import com.gekox.incense.Constants;
import com.gekox.incense.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
	public ModBlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, Constants.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(Registration.BLOCK_INCENSE_STICK.get());
//		simpleBlock(Registration.BLOCK_TEST_BLOCK.get());
	}
}
