package com.gekox.incense.network;

import com.gekox.incense.ModEntry;
import com.gekox.incense.util.IncenseType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnParticlesMessage {

	private final IncenseType incenseType;
	private final BlockPos pos;
	
	public SpawnParticlesMessage(FriendlyByteBuf buff) {
		this.incenseType = IncenseType.fromInt(buff.readInt());
		this.pos = buff.readBlockPos();
	}
	
	public SpawnParticlesMessage(IncenseType incenseType, BlockPos pos) {
		this.incenseType = incenseType;
		this.pos = pos;
	}

	public String toString() {
		return String.format("SpawnParticlesMessage: i:%s pos:%s", incenseType, pos);
	}
	
	private void doParticles(Supplier<NetworkEvent.Context> ctx) {
		ModEntry.LOGGER.info("SpawnParticlesMessage doParticles " + this);
	}
	
	public static void toBytes(SpawnParticlesMessage msg, FriendlyByteBuf buff) {
		buff.writeInt(msg.incenseType.ordinal());
		buff.writeBlockPos(msg.pos);
	}
	
	public static void handle(SpawnParticlesMessage msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> msg.doParticles(ctx));
		});
		ctx.get().setPacketHandled(true);
	}
	
}
