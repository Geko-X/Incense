package com.gekox.incense.network;

import com.gekox.incense.ModEntry;
import com.gekox.incense.common.block.IncenseStickBE;
import com.gekox.incense.util.IncenseType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateIncenseStickMessage {

	private final BlockPos pos;
	private final int maxBurnTime;
	private final int currentBurnTime;
	private final IncenseType incenseType;
	
	public UpdateIncenseStickMessage(FriendlyByteBuf buf) {
		this.pos = buf.readBlockPos();
		this.maxBurnTime = buf.readInt();
		this.currentBurnTime = buf.readInt();
		this.incenseType = IncenseType.fromInt(buf.readInt());
	}
	
	public UpdateIncenseStickMessage(BlockPos pos, int maxBurnTime, int currentBurnTime, IncenseType incenseType) {
		this.pos = pos;
		this.maxBurnTime = maxBurnTime;
		this.currentBurnTime = currentBurnTime;
		this.incenseType = incenseType;
	}
	
	private void handleClient(Supplier<NetworkEvent.Context> ctx) {
		
		// Should only ever be on the client
		if(ctx.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {

			Level level = Minecraft.getInstance().level;
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if(blockEntity instanceof IncenseStickBE incenseStickBE) {
				incenseStickBE.onUpdateFromServer(maxBurnTime, currentBurnTime, incenseType);
			}
			
		}
		
	}
	
	public static void toBytes(UpdateIncenseStickMessage msg, FriendlyByteBuf buf) {
		buf.writeBlockPos(msg.pos);
		buf.writeInt(msg.maxBurnTime);
		buf.writeInt(msg.currentBurnTime);
		buf.writeInt(msg.incenseType.ordinal());
	}
	
	public static void handle(UpdateIncenseStickMessage msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> msg.handleClient(ctx));
		});
		ctx.get().setPacketHandled(true);
		
	}
	
}
