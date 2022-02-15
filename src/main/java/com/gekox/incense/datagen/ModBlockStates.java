package com.gekox.incense.datagen;

import com.gekox.incense.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
	public ModBlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, Constants.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
//		simpleBlock(Registration.BLOCK_INCENSE_STICK.get());

//		BlockModelBuilder incenseStickModel = models().getBuilder(Registration.BLOCK_INCENSE_STICK.get().getRegistryName().getPath())
//				.parent(models().getExistingFile(mcLoc("cube")))
//				.customLoader((blockModelBuilder, helper) -> 
//						new CustomLoaderBuilder<BlockModelBuilder>(IncenseStickModelLoader.INCENSE_STICK_LOADER, blockModelBuilder, helper)
//				{ }
//				)
//				.end();
//		
//		simpleBlock(Registration.BLOCK_INCENSE_STICK.get(), incenseStickModel);

//		BlockModelBuilder incenseStickModel = models().getBuilder(Registration.BLOCK_INCENSE_STICK.get().getRegistryName().getPath())
//				.parent(models().getExistingFile(mcLoc("air")));
//		simpleBlock(Registration.BLOCK_INCENSE_STICK.get(), incenseStickModel);
	}
}
