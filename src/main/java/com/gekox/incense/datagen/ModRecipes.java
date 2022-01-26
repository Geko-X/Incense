package com.gekox.incense.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static com.gekox.incense.setup.Registration.*;

public class ModRecipes extends RecipeProvider {

	public ModRecipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(BLOCK_INCENSE_STICK.get())
				.define('P', TAG_INCENSE_PASTE)
				.define('S', Tags.Items.RODS_WOODEN)
				.pattern("P")
				.pattern("S")
				.unlockedBy("has_item", has(TAG_INCENSE_PASTE))
				.save(consumer);
		
		ShapedRecipeBuilder.shaped(ITEM_MORTAR_PESTLE.get())
				.define('#', Tags.Items.STONE)
				.define('S', ItemTags.SLABS)
				.pattern(" # ")
				.pattern("S#S")
				.pattern(" S ")
				.unlockedBy("has_item", has(ItemTags.SLABS))
				.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_NONE.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(ItemTags.COALS)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_PASSIVE.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.LEATHER)
				.requires(ItemTags.WOOL)
				.requires(Tags.Items.FEATHERS)
				.requires(Items.PORKCHOP)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_HOSTILE.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Items.ROTTEN_FLESH)
				.requires(Items.SPIDER_EYE)
				.requires(Items.BONE)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_NEUTRAL.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(ItemTags.FISHES)
				.requires(Items.BONE)
				.requires(Items.SNOW_BLOCK)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_NETHER.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.RODS_BLAZE)
				.requires(Items.GHAST_TEAR)
				.requires(Tags.Items.INGOTS_GOLD)
				.requires(Items.MAGMA_CREAM)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_ENDER.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.ENDER_PEARLS)
				.requires(Items.CHORUS_FRUIT)
				.requires((Items.SHULKER_SHELL))
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_VILLAGE.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.GEMS_EMERALD)
				.requires(Tags.Items.CROPS_WHEAT)
				.requires(Tags.Items.CROPS_CARROT)
				.requires(Tags.Items.CROPS_POTATO)
				.requires(Tags.Items.CROPS_BEETROOT)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_ILLAGE.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.GEMS_EMERALD)
				.requires(Items.CROSSBOW)
				.requires(ItemTags.ARROWS)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);
		
		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_WATER.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.DYES_BLACK)
				.requires(ItemTags.FISHES)
				.requires(Items.GLOW_INK_SAC)
				.requires(Tags.Items.GEMS_PRISMARINE)
				.requires(Items.SCUTE)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);

		ShapelessRecipeBuilder.shapeless(ITEM_INCENSE_PASTE_GOLEM.get())
				.requires(ITEM_MORTAR_PESTLE.get())
				.requires(Tags.Items.STORAGE_BLOCKS_IRON)
				.requires(Items.SNOW_BLOCK)
				.unlockedBy("has_item", has(ITEM_MORTAR_PESTLE.get()))
				.save(consumer);
	}
}
