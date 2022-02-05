package com.gekox.incense.config;

public class ConfigValues {

	public class Defaults {
		public static final int TOTAL_BURN_TIME = 300; 			// Burn time is seconds
		public static final int BASE_SPAWN_CHANCE = 2;			// Chance in 100 for a thing to spawn
		public static final int SPAWN_RADIUS = 4; 				// 7x7 effective range
	}
	
	public class Values {
		public static final int TOTAL_BURN_TIME = Defaults.TOTAL_BURN_TIME;
		public static final int BASE_SPAWN_CHANCE = Defaults.BASE_SPAWN_CHANCE;
		public static final int SPAWN_RADIUS = Defaults.SPAWN_RADIUS;
	}
	
}
