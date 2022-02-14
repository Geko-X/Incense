package com.gekox.incense.common.block;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import com.gekox.incense.api.IncenseAPI;
import com.gekox.incense.config.ConfigValues;
import com.gekox.incense.network.ModPacketHandler;
import com.gekox.incense.network.UpdateIncenseStickMessage;
import com.gekox.incense.setup.Registration;
import com.gekox.incense.util.Color;
import com.gekox.incense.util.IncenseType;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class IncenseStickBE extends BlockEntity {
	
	public static final ModelProperty<BlockState> INCENSE_STICK_BLOCK = new ModelProperty<>();
	private BlockState incenseBlockState;
	
	protected IncenseType incenseType = IncenseType.NONE;
	protected int burnHeight = Constants.MAX_BURN_HEIGHT;
	protected boolean isBurning = false;
	
	private Random random = new Random();
	
	private int totalBurnTime;
	private int burnTick;
	private int secondsPerBurn;

	private int ticks;
	private final int ticksPerSecond = 20;
	
	private int spawnsThisBurnTick = 0;
	
	public IncenseStickBE(BlockPos pos, BlockState state) {
		super(Registration.BE_INCENSE_STICK.get(), pos, state);
		incenseBlockState = state;
		
		totalBurnTime = ConfigValues.Values.TOTAL_BURN_TIME;
		secondsPerBurn = totalBurnTime / Constants.MAX_BURN_HEIGHT;
	}
	
	public void SetIncenseType(IncenseType incenseType) {
		this.incenseType = incenseType;

		ModEntry.LOGGER.info("[IncenseStickBE]: SetIncenseType to " + this.incenseType);
		
		setChanged();
		level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
		
	}
	
	public IncenseType GetIncenseType() {
		return incenseType;
	}
	
	public float getBurnPercent() {
		return 1f - ((float) burnTick / totalBurnTime);
	}
	
	
	public void SetBurning(boolean isBurning) {
		ModEntry.LOGGER.debug("[IncenseStickBE]: Set burning to " + isBurning);

		ModEntry.LOGGER.info(String.format("[IncenseStickBE]: Burning for %ds with %ds per burn", ConfigValues.Values.TOTAL_BURN_TIME, secondsPerBurn));

		setChanged();
		level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
		
	}
	
	public void onUpdateFromServer(int totalBurnTime, int currentBurnTime, IncenseType incenseType) {
		this.totalBurnTime = totalBurnTime;
		this.burnTick = currentBurnTime;
		this.incenseType = incenseType;
		
		// Only will be updated if is burning
		this.isBurning = true;
	}
	
	public void tickServer() {

		if(level.isClientSide())
			return;
		
		if(isBurning) {

			ticks++;
			ticks %= ticksPerSecond;
			if(ticks == 0) {

				// Once a second

				burnTick++;
			//	burnTick %= secondsPerBurn;

				UpdateIncenseStickMessage msg = new UpdateIncenseStickMessage(worldPosition, totalBurnTime, burnTick, incenseType);
				ModPacketHandler.SendToNear(level.getChunkAt(worldPosition), msg);

				if(burnTick % secondsPerBurn == 0) {
					decrementBurn();
				}

				if(ConfigValues.Values.BASE_SPAWN_CHANCE >= random.nextInt(100)) {
					handleSpawn();
				}

				spawnParticles();

			}
		}
	}
	
	private void decrementBurn() {

		if(level.isClientSide())
			return;
		
		// Force a spawn if there have been none this burn stage
		if(spawnsThisBurnTick == 0) {
			handleSpawn();
		}
		
		spawnsThisBurnTick = 0;
		burnHeight--;
		
		if(burnHeight == 0) {
			level.destroyBlock(worldPosition, false);
			return;
		}

//		BlockState state = getBlockState().setValue(Registration.BLOCKSTATE_BURN_HEIGHT, burnHeight);
//		level.setBlockAndUpdate(worldPosition, state);
	}
	
	private void handleSpawn() {

		if(level.isClientSide())
			return;
		
		spawnsThisBurnTick++;
		
		if(incenseType == IncenseType.NONE || incenseType == IncenseType.SOOTY)
			return;
		
		int radius = ConfigValues.Values.SPAWN_RADIUS;
		
		int startX = worldPosition.getX() - radius + 1;
		int startZ = worldPosition.getZ() - radius + 1;

		int endX = worldPosition.getX() + radius;
		int endZ = worldPosition.getZ() + radius;
		
		int x = random.nextInt((endX - startX) + 1) + startX;
		int z = random.nextInt((endZ - startZ) + 1) + startZ;
		int y = worldPosition.getY();
		
		BlockPos targetPos = new BlockPos(x, y, z);
		BlockState state = level.getBlockState(targetPos);
		
		if(state.isAir()) {
			
			String mobId = IncenseAPI.GetRandomSpawnForIncense(incenseType);
			ModEntry.LOGGER.info("Attempt spawn: {}", mobId);
			
			if(mobId == null || mobId.isBlank())
				return;
			
			EntityType entity = EntityType.byString(mobId).get();
			Mob mob = (Mob) entity.create(level);
			mob.setPos(targetPos.getX(), targetPos.getY(), targetPos.getZ());
			mob.finalizeSpawn((ServerLevelAccessor) level, level.getCurrentDifficultyAt(targetPos), MobSpawnType.SPAWNER, null, null);
			level.addFreshEntity(mob);
		}
	}
	
	private void spawnParticles() {
//		SpawnParticlesMessage msg = new SpawnParticlesMessage(_incenseType, worldPosition);
//		LevelChunk chunk = level.getChunkAt(worldPosition);
//		ModPacketHandler.SendToNear(chunk, msg);
		
		/*
		
		the parameters are actually serverLevel.sendParticles(particle, x, y, z, count, xDist, yDist, zDist, particleSpeed);
if you want to have specific color you need to make the particle argument custom and set it there.  eg. this is how a spawn black dust new DustParticleOptions(new Vector3f(Vec3.fromRGB24(0)), 4)
		
		 */
		
		if(level.isClientSide())
			return;

		int radius = ConfigValues.Values.SPAWN_RADIUS;
		ServerLevel sLevel = (ServerLevel) level;

		Color color = Color.getColorFromType(incenseType);
		float scale = 1;
		var particle = new DustParticleOptions(new Vector3f(color.r / 255f, color.g / 255f, color.b / 255f), scale);
		sLevel.sendParticles(particle, 
				worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(),
				50,
				radius - 1, 1, radius - 1, 
				0);
		
	}
	

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();
		saveToNBT(tag);
		return tag;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		if(tag != null)
			loadFromNBT(tag);
	}

	@Nullable
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		// This is called client side: remember the current state of the values that we're interested in
		IncenseType oldIncenseType = incenseType;
		int oldBurnTick = burnTick;
		boolean oldBurning = isBurning;

		CompoundTag tag = pkt.getTag();
		handleUpdateTag(tag);
		
		if(oldIncenseType != incenseType || oldBurnTick != burnTick || oldBurning != isBurning) {
			level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
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
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		saveToNBT(tag);
	}

	private void saveToNBT(CompoundTag tag) {
		CompoundTag incenseTag = new CompoundTag();

		incenseTag.putString(Constants.NBT.INCENSE_TYPE, incenseType.name());
		incenseTag.putInt(Constants.NBT.INCENSE_BURN_TICK, burnTick);
		incenseTag.putBoolean(Constants.NBT.IS_BURNING, isBurning);

		tag.put(Constants.NBT.TAG_BASE, incenseTag);

		ModEntry.LOGGER.info("saveToNBT: " + tag);

	}
	
	@Override
	public void load(CompoundTag tag) {
		
		super.load(tag);
		loadFromNBT(tag);
		
	}

	private void loadFromNBT(CompoundTag tag) {
		CompoundTag incenseTag = tag.getCompound(Constants.NBT.TAG_BASE);

		if(incenseTag.contains(Constants.NBT.INCENSE_TYPE)) {
			incenseType = IncenseType.fromString(incenseTag.getString(Constants.NBT.INCENSE_TYPE));
		}
		
		else {
			ModEntry.LOGGER.warn("[IncenseStickBE]: NBT did not have key {}", Constants.NBT.INCENSE_TYPE);
		}

		if(incenseTag.contains(Constants.NBT.INCENSE_BURN_TICK)) {
			burnTick = incenseTag.getInt(Constants.NBT.INCENSE_BURN_TICK);
		}

		else {
			ModEntry.LOGGER.warn("[IncenseStickBE]: NBT did not have key {}", Constants.NBT.INCENSE_BURN_TICK);
		}

		if(incenseTag.contains(Constants.NBT.IS_BURNING)) {
			isBurning = incenseTag.getBoolean(Constants.NBT.IS_BURNING);
		}

		else {
			ModEntry.LOGGER.warn("[IncenseStickBE]: NBT did not have key {}", Constants.NBT.IS_BURNING);
		}

		ModEntry.LOGGER.info("loadFromNBT: " + tag);
		ModEntry.LOGGER.info("loadFromNBT: " + incenseTag);
	}
}
