package com.gekox.incense.setup;

import com.gekox.incense.Constants;
import com.gekox.incense.api.IncenseAPI;
import com.gekox.incense.network.ModPacketHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {
	
	public static void init(final FMLCommonSetupEvent event) {
		IncenseAPI.Init();
		ModPacketHandler.init();
	}

	public static final String TAB_NAME = Constants.MODID;

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Registration.ITEM_INCENSE_STICK.get());
		}
	};
}
