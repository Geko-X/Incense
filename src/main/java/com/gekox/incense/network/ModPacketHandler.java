package com.gekox.incense.network;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class ModPacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Constants.MODID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);
	
	public static void init() {

		ModEntry.LOGGER.info("ModPacketHandler init");
		
		int id = 0;
		INSTANCE.registerMessage(
				id++,
				SpawnParticlesMessage.class,
				SpawnParticlesMessage::toBytes,
				SpawnParticlesMessage::new,
				SpawnParticlesMessage::handle
		);
	}
	
	public static void Send(ServerPlayer serverPlayer, Object msgObj) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), msgObj);
	}

	public static void SendToNear(LevelChunk chunk, Object msgObj) {
		INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), msgObj);
	}

	public static void SendToAll( Object msgObj) {
		INSTANCE.send(PacketDistributor.ALL.noArg(), msgObj);
	}
	
}
