package com.gekox.incense.common.block;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import com.gekox.incense.setup.Registration;
import com.gekox.incense.util.IncenseType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class IncenseStickBE extends BlockEntity {
	
	public static final ModelProperty<BlockState> INCENSE_STICK_BLOCK = new ModelProperty<>();
	private BlockState incenseBlockState;
	
	protected IncenseType _incenseType = IncenseType.NONE;
	protected int burnHeight = Constants.MAX_BURN_HEIGHT;
	protected boolean isBurning = false;
	
	public IncenseStickBE(BlockPos pos, BlockState state) {
		super(Registration.BE_INCENSE_STICK.get(), pos, state);
		incenseBlockState = state;
	}
	
	public void SetIncenseType(IncenseType incenseType) {
		this._incenseType = incenseType;

		ModEntry.LOGGER.info("[IncenseStickBE]: SetIncenseType to " + this._incenseType);
		
		setChanged();
//		incenseBlockState = getBlockState().setValue(Registration.BLOCKSTATE_INCENSE_TYPE, this._incenseType);
		BlockState state = getBlockState().setValue(Registration.BLOCKSTATE_INCENSE_TYPE, this._incenseType);
//		level.sendBlockUpdated(worldPosition, getBlockState(), incenseBlockState, Block.UPDATE_ALL);
		level.setBlockAndUpdate(worldPosition, state);
	}
	
	public IncenseType GetIncenseType() {
		return _incenseType;
	}
	
	public float getBurnPercent() {
		return 1;
	}
	
	public void SetBurning(boolean isBurning) {
		ModEntry.LOGGER.info("[IncenseStickBE]: Set burning to " + isBurning);

		setChanged();
		BlockState state = getBlockState().setValue(BlockStateProperties.LIT, isBurning);
		level.setBlockAndUpdate(worldPosition, state);
	}
	
	private int ticks = 0;
	public void tickServer() {
		ticks++;
		ticks %= 20;
		
		BlockState state = getBlockState();
		
		if(state.getValue(BlockStateProperties.LIT)) {
			if(ticks == 0)
				decrementBurn();
		}
	}
	
	private void decrementBurn() {
		burnHeight--;
		if(burnHeight == 0) {
			level.destroyBlock(worldPosition, false);
//			burnHeight = Constants.MAX_BURN_HEIGHT;
			return;
		}

		BlockState state = getBlockState().setValue(Registration.BLOCKSTATE_BURN_HEIGHT, burnHeight);
		level.setBlockAndUpdate(worldPosition, state);
		
	}
	

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();
		saveClientData(tag);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		if (tag != null) {
			loadClientData(tag);
		}
	}

	@Nullable
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		// This is called client side: remember the current state of the values that we're interested in
		BlockState oldState = incenseBlockState;

		CompoundTag tag = pkt.getTag();
		// This will call loadClientData()
		handleUpdateTag(tag);

		// If any of the values was changed we request a refresh of our model data and send a block update (this will cause
		// the baked model to be recreated)
		if (incenseBlockState != oldState) {
			ModelDataManager.requestModelDataRefresh(this);
			BlockState state = getBlockState().setValue(Registration.BLOCKSTATE_INCENSE_TYPE, this._incenseType);
//			level.sendBlockUpdated(worldPosition, getBlockState(), incenseBlockState, Block.UPDATE_ALL);
			level.setBlockAndUpdate(worldPosition, state);
		}
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
	}

	@Nonnull
	@Override
	public IModelData getModelData() {
		return new ModelDataMap.Builder()
				.withInitial(INCENSE_STICK_BLOCK, incenseBlockState)
				.build();
	}
	
	@Override
	public void load(CompoundTag tag) {
		
		super.load(tag);
		loadClientData(tag);
		
		if(tag.contains(Constants.NBT.INCENSE_TYPE)) {
			_incenseType = IncenseType.fromString(tag.getString(Constants.NBT.INCENSE_TYPE));
		}
	}
	
	@Override
	public void saveAdditional(CompoundTag tag) {
		saveClientData(tag);
		tag.putString(Constants.NBT.INCENSE_TYPE, _incenseType.name());
	}

	private void saveClientData(CompoundTag tag) {
		CompoundTag infoTag = new CompoundTag();
	}

	private void loadClientData(CompoundTag tag) {
		
	}
	
}
