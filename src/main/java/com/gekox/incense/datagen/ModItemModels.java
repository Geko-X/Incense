package com.gekox.incense.datagen;

import com.gekox.incense.Constants;
import com.gekox.incense.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModels extends ItemModelProvider {

	public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Constants.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		registerModelForItem(Registration.ITEM_INCENSE_STICK, Constants.NAME_INCENSE_STICK);
//		registerModelForItem(Registration.ITEM_TEST_BLOCK, Constants.NAME_TEST_BLOCK);

		registerItemTexture(Registration.ITEM_MORTAR_PESTLE, Constants.NAME_MORTAR_PESTLE);

//		registerItemTexture(Registration.ITEM_INCENSE_PASTE, Constants.NAME_INCENSE_PASTE);
//		
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_NONE, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_PASSIVE, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_HOSTILE, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_NEUTRAL, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_ENDER, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_NETHER, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_VILLAGE, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_ILLAGE, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_WATER, Constants.NAME_INCENSE_PASTE);
		registerItemTexture(Registration.ITEM_INCENSE_PASTE_GOLEM, Constants.NAME_INCENSE_PASTE);


	}
	
	private void registerModelForItem(RegistryObject<Item> item, String name) {
		withExistingParent(item.get().getRegistryName().getPath(), modLoc("block/" + name));
	}
	
	private void registerItemTexture(RegistryObject<Item> item, String name) {
		singleTexture(item.get().getRegistryName().getPath(),
				mcLoc("item/generated"), "layer0", modLoc("item/" + name));
	}
}