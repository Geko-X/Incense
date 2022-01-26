package com.gekox.incense;

import com.gekox.incense.setup.ClientSetup;
import com.gekox.incense.setup.ModSetup;
import com.gekox.incense.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		
	}
//
//	private void setup(final FMLCommonSetupEvent event) {
//		// some preinit code
//		_logger.info("HELLO FROM PREINIT");
//	}
//
//	private void enqueueIMC(final InterModEnqueueEvent event) {
//		// some example code to dispatch IMC to another mod
//		InterModComms.sendTo("incense", "helloworld", () -> {
//			_logger.info("Hello world from the MDK");
//			return "Hello world";
//		});
//	}
//
//	private void processIMC(final InterModProcessEvent event) {
//		// some example code to receive and process InterModComms from other mods
//		_logger.info("Got IMC {}", event.getIMCStream().
//				map(m -> m.messageSupplier().get()).
//				collect(Collectors.toList()));
//	}
//
//	// You can use SubscribeEvent and let the Event Bus discover methods to call
//	@SubscribeEvent
//	public void onServerStarting(ServerStartingEvent event) {
//		// do something when the server starts
//		_logger.info("HELLO from server starting");
//	}
//
//	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
//	// Event bus for receiving Registry Events)
//	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
//	public static class RegistryEvents {
//
//		@SubscribeEvent
//		public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
//			// register a new block here
//			_logger.info("Registering blocks");
//		}
//		
//		@SubscribeEvent
//		public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
//			
//		}
//	}
}
