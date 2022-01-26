package com.gekox.incense.setup;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import com.gekox.incense.client.RenderItemPaste;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
	public static void init(final FMLClientSetupEvent event) {

		ModEntry.LOGGER.info("Client setup");
		
	}
	
	@SubscribeEvent
	public static void onColorHandlerEvent(ColorHandlerEvent.Item event) {
		ModEntry.LOGGER.info("Registering color handlers");
//		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE.get());	
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_NONE.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_PASSIVE.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_HOSTILE.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_NEUTRAL.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_ENDER.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_NETHER.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_VILLAGE.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_ILLAGE.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_WATER.get());
		registerItemPasteHandler(event, Registration.ITEM_INCENSE_PASTE_GOLEM.get());
	}
	
	private static void registerItemPasteHandler(ColorHandlerEvent.Item event, Item item) {
		event.getItemColors().register(new RenderItemPaste(), item);
	}
}
