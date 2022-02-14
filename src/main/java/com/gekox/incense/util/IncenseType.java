package com.gekox.incense.util;

import com.gekox.incense.ModEntry;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum IncenseType implements StringRepresentable {
	
	NONE("none"),
	PASSIVE("passive"),
	HOSTILE("hostile"),
	NEUTRAL("neutral"),
	ENDER("ender"),
	NETHER("nether"),
	VILLAGE("village"),
	ILLAGE("illage"),
	WATER("water"),
	GOLEM("golem"),
	SOOTY("sooty");

	private final String text;
	
	IncenseType(String string) {
		text = string;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public @NotNull String getSerializedName() {
		return toString();
	}

	private static IncenseType[] enumvalues = IncenseType.values();
	
	public static IncenseType fromString(String text) {
		for(IncenseType type : enumvalues) {
			if (type.text.equalsIgnoreCase(text)) {
				return type;
			}
		}
		//throw new IllegalArgumentException("Given value (" + text + ") is not a valid IncenseType");
		
		// Silently return NONE if something failed.
		ModEntry.LOGGER.info("Given value (" + text + ") is not a valid IncenseType");
		return IncenseType.NONE;
	}
	
	public static IncenseType fromInt(int i) {
		if(i < 0 || i >= enumvalues.length)
			return IncenseType.NONE;
		
		return enumvalues[i];
	}
}
