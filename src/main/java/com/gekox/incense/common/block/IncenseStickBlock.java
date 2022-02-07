package com.gekox.incense.common.block;

import com.gekox.incense.Constants;
import com.gekox.incense.common.item.PasteItem;
import com.gekox.incense.setup.Registration;
import com.gekox.incense.util.IncenseType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class IncenseStickBlock extends Block implements EntityBlock {

	private static final VoxelShape RENDER_SHAPE = Shapes.box(0.4, 0, 0.4, 0.6, 0.8, 0.6);
	
	protected static final ParticleOptions flameParticle = ParticleTypes.FLAME;
	
	public IncenseStickBlock() {
		super(Properties.of(Material.WOOD)
				.strength(0)
				.sound(SoundType.WOOD)
		);
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter reader, BlockPos pos) {
		return RENDER_SHAPE;
	}

	public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

		if(pState.getValue(Registration.BLOCKSTATE_BURN_HEIGHT) > 0) {
			int h = pState.getValue(Registration.BLOCKSTATE_BURN_HEIGHT);
			return Block.box(6.0D, 0.0D, 6.0D, 10.0D, (h + 1) * 2, 10.0D);
		}
		
		return super.getShape(pState, pLevel, pPos, pContext);
		
	}

	// Modified from vanilla torch
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRand) {
		
		if(!pState.getValue(BlockStateProperties.LIT))
			return;
		
		if(pState.getValue(Registration.BLOCKSTATE_BURN_HEIGHT) > 0) {
			double h = pState.getValue(Registration.BLOCKSTATE_BURN_HEIGHT);
			
			double x = (double) pPos.getX() + 0.5D;
			double y = (double) pPos.getY() + h * (1d/8) + (1d/8);
			double z = (double) pPos.getZ() + 0.5D;
			
			pLevel.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			pLevel.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
		}
		
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
		String pasteName = new TranslatableComponent(Constants.Names.NAME_INCENSE_PASTE).getString();
		//  "Place in world and %s %s on it, then %s"
		list.add(
			new TranslatableComponent(Constants.Lang.TOOLTIP_STICK_INSTRUCTIONS,
				Constants.Lang.ACTION_USE, 
				new TranslatableComponent("incense." + Constants.Names.NAME_INCENSE_PASTE),
				Constants.Lang.ACTION_BURN
			).withStyle(ChatFormatting.BLUE));
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new IncenseStickBE(pPos, pState);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		if (level.isClientSide()) {
			return null;
		}
		return (lvl, pos, blockState, t) -> {
			if (t instanceof IncenseStickBE tile) {
				tile.tickServer();
			}
		};
	}

	@SuppressWarnings("deprecated")
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
	
		if(level.isClientSide())
			return InteractionResult.PASS;

		BlockEntity be = level.getBlockEntity(pos);
		if(be instanceof IncenseStickBE stickBE) {

			ItemStack stack = player.getItemInHand(hand);
			if(stack.getItem() instanceof PasteItem paste) {
				stickBE.SetIncenseType(paste.getIncenseType());
				return InteractionResult.SUCCESS;
			}
			
			
			
			if(stack.getItem() instanceof FlintAndSteelItem) {

				IncenseType incenseType = IncenseType.NONE;
				if(state.hasProperty(Registration.BLOCKSTATE_INCENSE_TYPE)) {
					incenseType = state.getValue(Registration.BLOCKSTATE_INCENSE_TYPE);
				}
				
				if(incenseType != IncenseType.NONE) {
					stickBE.SetBurning(true);
					return InteractionResult.SUCCESS;
				}
				
				return InteractionResult.FAIL;
			}
		}
		
		return InteractionResult.PASS;
		
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(BlockStateProperties.LIT); 			// If burning or not
		builder.add(Registration.BLOCKSTATE_INCENSE_TYPE);
		builder.add(Registration.BLOCKSTATE_BURN_HEIGHT);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context)
				.setValue(BlockStateProperties.LIT, false)
				.setValue(Registration.BLOCKSTATE_INCENSE_TYPE, IncenseType.NONE)
				.setValue(Registration.BLOCKSTATE_BURN_HEIGHT, Constants.MAX_BURN_HEIGHT);
	}
}
