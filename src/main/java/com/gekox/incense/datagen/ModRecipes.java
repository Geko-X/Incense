package com.gekox.incense.datagen;

import com.gekox.incense.item.ItemPaste;
import com.gekox.incense.setup.Registration;
import com.gekox.incense.util.IncenseType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static com.gekox.incense.setup.Registration.*;

public class ModRecipes extends RecipeProvider {

	public ModRecipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(BLOCK_INCENSE_STICK.get())
				.define('P', TAG_INCENSE_PASTE)
				.define('S', Tags.Items.RODS_WOODEN)
				.pattern("P")
				.pattern("S")
				.unlockedBy("has_item", has(TAG_INCENSE_PASTE))
				.save(consumer);

		ItemPaste paste;
		
//		paste = (ItemPaste)(ITEM_INCENSE_PASTE.get());
//		ItemPaste.setIncenseType(paste.getDefaultInstance(), IncenseType.NONE);
//		ShapelessRecipeBuilder.shapeless(paste)
//				.requires(ITEM_MORTAR_PESTLE.get())
//				.requires(ItemTags.COALS)
//				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
//				.save(consumer);
//
//		paste = (ItemPaste)(ITEM_INCENSE_PASTE.get());
//		ItemPaste.setIncenseType(paste.getDefaultInstance(), IncenseType.PASSIVE);
//		ShapelessRecipeBuilder.shapeless(paste)
//				.requires(ITEM_MORTAR_PESTLE.get())
//				.requires(Tags.Items.LEATHER)
//				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
//				.save(consumer);

		paste = (ItemPaste)(ITEM_INCENSE_PASTE_NONE.get());
		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_NONE.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(ItemTags.COALS)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_PASSIVE.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.LEATHER)
				.requires(ItemTags.WOOL)
				.requires(Tags.Items.EGGS)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);
	}
}
