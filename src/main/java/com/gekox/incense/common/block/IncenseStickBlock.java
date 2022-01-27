package com.gekox.incense.common.block;

import com.gekox.incense.Constants;
import com.gekox.incense.setup.Registration;
import com.mojang.datafixers.kinds.Const;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IncenseStickBlock extends Block implements EntityBlock {

	public IncenseStickBlock() {
		super(Properties.of(Material.WOOD)
				.strength(0)
				.sound(SoundType.WOOD)
		);
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
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
		return EntityBlock.super.getTicker(pLevel, pState, pBlockEntityType);
	}
}
