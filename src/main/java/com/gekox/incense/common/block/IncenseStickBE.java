package com.gekox.incense.common.block;

import com.gekox.incense.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IncenseStickBE extends BlockEntity {

	public IncenseStickBE(BlockPos pos, BlockState state) {
		super(Registration.BE_INCENSE_STICK.get(), pos, state);
	}
}
