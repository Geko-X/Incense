package com.gekox.incense.util;

import net.minecraft.util.StringRepresentable;

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

	private String text;
	
	IncenseType(String string) {
		text = string;
	}

	@Override
	public String toString() {
		return text;
	}
	
	public static IncenseType fromString(String text) {
		for(IncenseType type : IncenseType.values()) {
			if (type.text.equalsIgnoreCase(text)) {
				return type;
			}
		}
		throw new IllegalArgumentException("");
	}

	@Override
	public String getSerializedName() {
		return toString();
	}
}
