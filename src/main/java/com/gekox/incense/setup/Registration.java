package com.gekox.incense.setup;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import com.gekox.incense.common.block.IncenseStickBE;
import com.gekox.incense.common.block.IncenseStickBlock;
import com.gekox.incense.common.item.PasteItem;
import com.gekox.incense.util.IncenseType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

	private static final DeferredRegister<Item> MOD_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);
	private static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MODID);
	private static final DeferredRegister<BlockEntityType<?>> MOD_BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Constants.MODID);

	public static void init() {

		ModEntry.LOGGER.info("Init");
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		MOD_ITEMS.register(bus);
		MOD_BLOCKS.register(bus);
		MOD_BLOCK_ENTITIES.register(bus);
	}

	public static final BlockBehaviour.Properties BLOCK_PROPERTIES_MOD_DEFAULT = BlockBehaviour.Properties.of(Material.WOOD).strength(0);
	public static final Item.Properties ITEM_PROPERTIES_MOD_DEFAULT = new Item.Properties().tab(ModSetup.ITEM_GROUP);
	
	public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block, Item.Properties properties) {
		return MOD_ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
	
	
	// Items

	public static final RegistryObject<Item> ITEM_MORTAR_PESTLE = MOD_ITEMS.register(Constants.Names.NAME_MORTAR_PESTLE,
			() -> new Item(ITEM_PROPERTIES_MOD_DEFAULT));
	
	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_NONE = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.NONE.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.NONE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_PASSIVE = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.PASSIVE.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.PASSIVE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_HOSTILE = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.HOSTILE.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.HOSTILE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_NEUTRAL = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.NEUTRAL.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.NEUTRAL));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_ENDER = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.ENDER.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.ENDER));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_NETHER = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.NETHER.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.NETHER));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_VILLAGE = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.VILLAGE.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.VILLAGE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_ILLAGE = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.ILLAGE.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.ILLAGE));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_WATER = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.WATER.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.WATER));

	public static final RegistryObject<Item> ITEM_INCENSE_PASTE_GOLEM = MOD_ITEMS.register(Constants.Names.NAME_INCENSE_PASTE + "_" + IncenseType.GOLEM.toString(),
			() -> new PasteItem(ITEM_PROPERTIES_MOD_DEFAULT, IncenseType.GOLEM));

	// Blocks (+ BlockItems)

//	public static final RegistryObject<Block> BLOCK_INCENSE_STICK = MOD_BLOCKS.register(Constants.NAME_INCENSE_STICK,
//			() -> new Block(
//					BlockBehaviour.Properties.of(Material.WOOD).strength(0)));
	public static final RegistryObject<IncenseStickBlock> BLOCK_INCENSE_STICK = MOD_BLOCKS.register(Constants.Names.NAME_INCENSE_STICK, IncenseStickBlock::new);
	public static final RegistryObject<Item> ITEM_INCENSE_STICK = fromBlock(BLOCK_INCENSE_STICK, ITEM_PROPERTIES_MOD_DEFAULT);
	public static final RegistryObject<BlockEntityType<IncenseStickBE>> BE_INCENSE_STICK = MOD_BLOCK_ENTITIES.register(Constants.Names.NAME_INCENSE_STICK,
			() -> BlockEntityType.Builder.of(IncenseStickBE::new, BLOCK_INCENSE_STICK.get()).build(null));
	
	// Tags

	public static final Tags.IOptionalNamedTag<Item> TAG_INCENSE_PASTE = 
			ItemTags.createOptional(new ResourceLocation(Constants.MODID, Constants.Names.NAME_INCENSE_PASTE));
}
