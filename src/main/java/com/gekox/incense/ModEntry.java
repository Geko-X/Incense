package com.gekox.incense;

import com.gekox.incense.api.IncenseAPI;
import com.gekox.incense.setup.ClientSetup;
import com.gekox.incense.setup.ModSetup;
import com.gekox.incense.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Constants.MODID)
public class ModEntry {
	
	public static ModEntry INSTANCE;
	
	public static final Logger LOGGER = LogManager.getLogger();

	public ModEntry() {
//		// Register the setup method for modloading
//		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
//		// Register the enqueueIMC method for modloading
//		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
//		// Register the processIMC method for modloading
//		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
//
//		// Register ourselves for server and other game events we are interested in
//		MinecraftForge.EVENT_BUS.register(this);
		
		INSTANCE = this;

		// Register the deferred registry
		Registration.init();

		// Register the setup method for modloading
		IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
		// Register 'ModSetup::init' to be called at mod setup time (server and client)
		modbus.addListener(ModSetup::init);
		// Register 'ClientSetup::init' to be called at mod setup time (client only)
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
		
		// IMC
		modbus.addListener(this::enqueueIMC);
		modbus.addListener(this::processIMC);
		
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
//		InterModComms.sendTo("incense", "helloworld", () -> {
//			LOGGER.info("Hello world from the MDK");
//			return "Hello world";
//		});

		InterModComms.sendTo("incense", "register", () -> "passive minecraft:pig");
		InterModComms.sendTo("incense", "register", () -> "passive minecraft:cow");
		InterModComms.sendTo("incense", "register", () -> "passive minecraft:sheep");
		InterModComms.sendTo("incense", "register", () -> "passive minecraft:chicken");
		InterModComms.sendTo("incense", "register", () -> "passive minecraft:rabbit");

		InterModComms.sendTo("incense", "register", () -> "hostile minecraft:skeleton");
		InterModComms.sendTo("incense", "register", () -> "hostile minecraft:zombie");
		InterModComms.sendTo("incense", "register", () -> "hostile minecraft:creeper");
		InterModComms.sendTo("incense", "register", () -> "hostile minecraft:spider");
		InterModComms.sendTo("incense", "register", () -> "hostile minecraft:witch");
		InterModComms.sendTo("incense", "register", () -> "hostile minecraft:cavespider");
		InterModComms.sendTo("incense", "register", () -> "hostile minecraft:slime");
		
		InterModComms.sendTo("incense", "register", () -> "neutral minecraft:ozelot");
		InterModComms.sendTo("incense", "register", () -> "neutral minecraft:wolf");
		InterModComms.sendTo("incense", "register", () -> "neutral minecraft:bat");
		InterModComms.sendTo("incense", "register", () -> "neutral minecraft:polarbear");
		
		InterModComms.sendTo("incense", "register", () -> "nether minecraft:ghast");
		InterModComms.sendTo("incense", "register", () -> "nether minecraft:pigzombie");
		InterModComms.sendTo("incense", "register", () -> "nether minecraft:blaze");
		InterModComms.sendTo("incense", "register", () -> "nether minecraft:lavaslime");
		InterModComms.sendTo("incense", "register", () -> "nether minecraft:skeleton");
		
		InterModComms.sendTo("incense", "register", () -> "ender minecraft:enderman");
		InterModComms.sendTo("incense", "register", () -> "ender minecraft:endermite");
		InterModComms.sendTo("incense", "register", () -> "ender minecraft:shulker");
		
		InterModComms.sendTo("incense", "register", () -> "village minecraft:villager");
		InterModComms.sendTo("incense", "register", () -> "village minecraft:villagergolem");
		
		InterModComms.sendTo("incense", "register", () -> "water minecraft:squid");
		InterModComms.sendTo("incense", "register", () -> "water minecraft:guardian");
		
		InterModComms.sendTo("incense", "register", () -> "golem minecraft:snowman");
		InterModComms.sendTo("incense", "register", () -> "golem minecraft:shulker");
		InterModComms.sendTo("incense", "register", () -> "golem minecraft:villagergolem");


	}

	private void processIMC(final InterModProcessEvent event) {
//		// some example code to receive and process InterModComms from other mods
//		LOGGER.info("Got IMC {}", event.getIMCStream().
//				map(m -> m.messageSupplier().get()).
//				collect(Collectors.toList()));
		
		LOGGER.info("Process IMC");
		
		for(final InterModComms.IMCMessage msg : event.getIMCStream().toList()) {
		//	LOGGER.info("Mod {} sent message {}:{}", msg.modId(), msg.method(), msg.messageSupplier().get());
			
			String msgMethod = msg.method().toLowerCase();
			if(msgMethod.equalsIgnoreCase("register")) {
				String msgValue = msg.messageSupplier().get().toString().toLowerCase();
				String[] msgParts = msgValue.split("\\s+");
				
				if(msgParts.length != 2) {
					// Error, continue
					continue;
				}
				
				String incenceString = msgParts[0];
				String mobId = msgParts[1];

				IncenseAPI.AddSpawnForIncense(incenceString, mobId);
				
			}
			
		}
		
	}
}
