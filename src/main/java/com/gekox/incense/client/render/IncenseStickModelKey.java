package com.gekox.incense.client;

import com.gekox.incense.util.IncenseType;
import net.minecraft.client.resources.model.ModelState;

import javax.annotation.Nullable;

public record IncenseStickModelKey(IncenseType incenseType, int burnHeight, @Nullable ModelState modelState) {
	
	@Override
	public String toString() {
		return String.format("IncenseStickModelKey (%s, %d)", incenseType, burnHeight);
	}
	
}
