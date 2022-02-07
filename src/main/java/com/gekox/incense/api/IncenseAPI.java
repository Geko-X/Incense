package com.gekox.incense.api;

import com.gekox.incense.util.IncenseType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class IncenseAPI {

	public static Map<IncenseType, List<String>> IncenseSpawns = new HashMap<>();
	private static final Random random = new Random();

	public static final Logger LOGGER = LogManager.getLogger("IncenseAPI");
	
	public static void Init() {
		for(IncenseType incenseType : IncenseType.values()) {
			IncenseSpawns.put(incenseType, new ArrayList<>());
		}
		
		LOGGER.info("IncenseAPI init");
		
	}
	
	private static IncenseType GetTypeFromString(String incenseType) {
		IncenseType type = IncenseType.NONE;
		try {
			type = IncenseType.fromString(incenseType);
		}

		catch (IllegalArgumentException e) {
			LOGGER.warn("Incense type is invalid!");
			return null;
		}
		return type;
	}
	
	public static void AddSpawnForIncense(String incenseType, String mobId) {
		
		IncenseType type = GetTypeFromString(incenseType);
		if(type == null)
			return;
		
		AddSpawnForIncense(type, mobId);
	}
	
	public static void AddSpawnForIncense(IncenseType incenseType, String mobId) {
		
		LOGGER.info("Adding {} to the {} list", mobId, incenseType);
		
		ResourceLocation resourceLocation = ResourceLocation.tryParse(mobId);
		if(!ForgeRegistries.ENTITIES.containsKey(resourceLocation)) {
			LOGGER.warn("MobID {} was not found in the entity registry!", mobId);
			return;
		}
		
		if(!IncenseSpawns.containsKey(incenseType)) {
			IncenseSpawns.put(incenseType, new ArrayList<>());
		}
		
		List<String> spawns = IncenseSpawns.get(incenseType);
		
		if(spawns.contains(mobId))
			return;
		
		spawns.add(mobId);
		
		IncenseSpawns.replace(incenseType, spawns);
	}
	
	public static List<String> GetSpawnsForIncense(String incenseType) {

		IncenseType type = GetTypeFromString(incenseType);
		if(type == null)
			return null;
		
		return IncenseSpawns.get(type);
	}
	
	public static List<String> GetSpawnsForIncense(IncenseType incenseType) {
		return IncenseSpawns.get(incenseType);
	}
	
	public static String GetRandomSpawnForIncense(IncenseType incenseType) {
		List<String> spawns = GetSpawnsForIncense(incenseType);
		if(spawns == null || spawns.size() == 0)
			return "";
		
		return spawns.get(random.nextInt(spawns.size()));
	}
}
