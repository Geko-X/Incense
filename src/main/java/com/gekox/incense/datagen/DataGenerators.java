package com.gekox.incense.datagen;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Constants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {

		ModEntry.LOGGER.info("Running data gen");
		
		DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			generator.addProvider(new ModRecipes(generator));
			generator.addProvider(new ModLootTables(generator));
			
			ModBlockTags blockTags = new ModBlockTags(generator, event.getExistingFileHelper());
			generator.addProvider(blockTags);
			generator.addProvider(new ModItemTags(generator, blockTags, event.getExistingFileHelper()));
		}
		if (event.includeClient()) {
			generator.addProvider(new ModBlockStates(generator, event.getExistingFileHelper()));
			generator.addProvider(new ModItemModels(generator, event.getExistingFileHelper()));
			generator.addProvider(new ModLanguageProvider(generator, "en_us"));
		}

		ModEntry.LOGGER.info("Done");
		
	}
	
}
