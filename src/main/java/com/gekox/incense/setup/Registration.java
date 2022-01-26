package com.gekox.incense.setup;

import com.gekox.incense.ModEntry;
import com.gekox.incense.item.ItemPaste;
import com.gekox.incense.util.IncenseType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.gekox.incense.Constants;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

	private static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MODID);
	private static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

	public static void init() {

		ModEntry.LOGGER.info("Init");
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ModEntry.LOGGER.info("Registering blocks...");
		MOD_BLOCKS.register(bus);

		ModEntry.LOGGER.info("Registering items...");
		MOD_ITEMS.register(bus);
	}

	public static final BlockBehaviour.Properties BLOCK_PROPERTIES_MOD_DEFAULT = BlockBehaviour.Properties.of(Material.WOOD).strength(0);
	public static final Item.Properties ITEM_PROPERTIES_MOD_DEFAULT = new Item.Properties().tab(ModSetup.ITEM_GROUP);
	
	public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, Item.Properties properties) {
		return MOD_ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
	
	// Blocks (+ BlockItems)
	
//	public static final RegistryObject<Block> BLOCK_TEST_BLOCK = MOD_BLOCKS.register(Constants.NAME_TEST_BLOCK,
//			() -> new Block(
//					BLOCK_PROPERTIES_MOD_DEFAULT));
//	public static final RegistryObject<Item> ITEM_TEST_BLOCK = fromBlock(BLOCK_TEST_BLOCK, ITEM_PROPERTIES_MOD_DEFAULT);
	
	public static final RegistryObject<Block> BLOCK_INCENSE_STICK = MOD_BLOCKS.register(Constants.NAME_INCENSE_STICK,
			() -> new Block(
					BlockBehaviour.Properties.of(Material.WOOD).strength(0)));
	public static final RegistryObject<Item> ITEM_INCENSE_STICK = fromBlock(BLOCK_INCENSE_STICK, ITEM_PROPERTIES_MOD_DEFAULT);
	
	// Items
	
//	public static final RegistryObject<Item> ITEM_INCENSE_PASTE = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE,
//			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT));

	public static final RegistryObject<Item> ITEM_MORTAR_PESTLE = MOD_ITEMS.register(Constants.NAME_MORTAR_PESTLE,
			() -> new Item(ITEM_PROPERTIES_MOD_DEFAULT));

//	public static final RegistryObject<Item> ITEM_INCENSE_PASTE = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE,
//			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT));
	
	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_NONE = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.NONE.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.NONE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_PASSIVE = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.PASSIVE.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.PASSIVE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_HOSTILE = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.HOSTILE.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.HOSTILE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_NEUTRAL = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.NEUTRAL.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.NEUTRAL));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_ENDER = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.ENDER.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.ENDER));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_NETHER = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.NETHER.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.NETHER));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_VILLAGE = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.VILLAGE.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.VILLAGE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_ILLAGE = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.ILLAGE.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.ILLAGE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_WATER = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.WATER.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.WATER));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_GOLEM = MOD_ITEMS.register(Constants.NAME_INCENSE_PASTE + "_" + IncenseType.GOLEM.toString(),
			() -> new ItemPaste(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.GOLEM));
	
	// Tags

	public static final Tags.IOptionalNamedTag<Item> TAG_INCENSE_PASTE = 
			ItemTags.createOptional(new ResourceLocation(Constants.MODID, Constants.NAME_INCENSE_PASTE));
}
