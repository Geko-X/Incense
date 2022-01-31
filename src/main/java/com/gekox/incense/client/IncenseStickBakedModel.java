package com.gekox.incense.client;

import com.gekox.incense.Constants;
import com.gekox.incense.ModEntry;
import com.gekox.incense.common.block.IncenseStickBE;
import com.gekox.incense.setup.Registration;
import com.gekox.incense.util.IncenseType;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

import com.gekox.incense.util.ClientTools;

import static com.gekox.incense.util.ClientTools.*;

public class IncenseStickBakedModel implements IDynamicBakedModel {

	private final ModelState modelState;
	private final Function<Material, TextureAtlasSprite> spriteGetter;
	private final Map<IncenseStickModelKey, List<BakedQuad>> quadCache = new HashMap<>();
	private final ItemOverrides overrides;
	private final ItemTransforms itemTransforms;

	public IncenseStickBakedModel(ModelState modelState, Function<Material, TextureAtlasSprite> spriteGetter,
							   ItemOverrides overrides, ItemTransforms itemTransforms) {
		this.modelState = modelState;
		this.spriteGetter = spriteGetter;
		this.overrides = overrides;
		this.itemTransforms = itemTransforms;
		generateQuadCache();
	}
	
	@NotNull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull Random rand, @NotNull IModelData extraData) {
		RenderType layer = MinecraftForgeClient.getRenderType();
		if (side != null || (layer != null && !layer.equals(RenderType.solid()))) {
			return Collections.emptyList();
		}
		
		IncenseType incenseType = IncenseType.NONE;
		int burnHeight = Constants.MAX_BURN_HEIGHT;
		
		if(state != null) {
//
//			if(state.hasProperty(Registration.BLOCKSTATE_INCENSE_TYPE)) {
//				incenseType = state.getValue(Registration.BLOCKSTATE_INCENSE_TYPE);
//			}
//			
			if(state.hasProperty(Registration.BLOCKSTATE_BURN_HEIGHT)) {
				burnHeight = state.getValue(Registration.BLOCKSTATE_BURN_HEIGHT);
			}
		}
		
		// Validate
		if(burnHeight < 0 || burnHeight > Constants.MAX_BURN_HEIGHT) {
			ModEntry.LOGGER.error("[Incense Render Stick]: burn height was invalid - using default instead");
			burnHeight = Constants.MAX_BURN_HEIGHT;
		}
			
		
		IncenseStickModelKey key = new IncenseStickModelKey(incenseType, burnHeight, modelState);
	//	ModEntry.LOGGER.info("getQuads " + key);
	//	return quadCache.get(key);
		
		return getQuadsNotCached(burnHeight);
		
	}
	
	private static final float ONE_EIGTH = 1 / 8f;
	
	private List<BakedQuad> getQuadsNotCached(int burnHeight) {

		var quads = new ArrayList<BakedQuad>();

		Transformation rotation = modelState.getRotation();
		TextureAtlasSprite texture = spriteGetter.apply(IncenseStickModelLoader.MATERIAL_INCENSE_STICK);
		
		// Bounds
		float w = ONE_EIGTH;
		float h = ONE_EIGTH * burnHeight + ONE_EIGTH;
		
		float p0 = 0.5f - (w / 2);
		float p1 = p0 + w;
		
		// Top
		//quads.add(ClientTools.createQuad(v(x1, y2, z1), v(x1, y2, z2), v(x2, y2, z2), v(x2, y2, z1), rotation, texture));
		quads.add(ClientTools.createQuad(v(p1, h, p1), v(p1, h, p0), v(p0, h, p0), v(p0, h, p1), rotation, texture));

		// Sides
		//quads.add(ClientTools.createQuad(v(p1, h, p1), v(p1, 0, p1), v(p0, 0, p0), v(p1, h, p1), rotation, texture));
		quads.add(ClientTools.createQuad(v(p1, h, p1), v(p1, 0, p1), v(p1, 0, p0), v(p1, h, p0), rotation, texture)); // L
		quads.add(ClientTools.createQuad(v(p0, h, p1), v(p0, h, p0), v(p0, 0, p0), v(p0, 0, p1), rotation, texture)); // R
		
		quads.add(ClientTools.createQuad(v(p0, h, p0), v(p1, h, p0), v(p1, 0, p0), v(p0, 0, p0), rotation, texture)); // F
		quads.add(ClientTools.createQuad(v(p0, h, p1), v(p0, 0, p1), v(p1, 0, p1), v(p1, h, p1), rotation, texture)); // B

		// Base
		quads.add(ClientTools.createQuad(v(p0, 0, p0), v(p1, 0, p1), v(p0, 0, p1), v(p0, 0, p0), DIRECTION_UP, rotation, texture));
		
		return quads;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean isGui3d() {
		return true;
	}

	@Override
	public boolean usesBlockLight() {
		return false;
	}

	@Override
	public boolean isCustomRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return spriteGetter.apply(IncenseStickModelLoader.MATERIAL_INCENSE_STICK);
	}

	@Override
	public ItemOverrides getOverrides() {
		return overrides;
	}

	@Override
	public ItemTransforms getTransforms() {
		return itemTransforms;
	}
	
	private void generateQuadCache() {

		for (IncenseType incenseType : IncenseType.values()) {
			for(int i = 0; i <= Constants.MAX_BURN_HEIGHT; i++) {
				quadCache.put(new IncenseStickModelKey(incenseType, i, modelState), generateQuads(incenseType, i));
			}
		}

		ModEntry.LOGGER.info("Put " + quadCache.size() + " quad caches");
		
	}
	
	private List<BakedQuad> generateQuads(IncenseType incenseType, int burnHeight) {

		ModEntry.LOGGER.info("Generating quads for: " + incenseType + " at height " + burnHeight);
		
		var quads = new ArrayList<BakedQuad>();

		Transformation rotation = modelState.getRotation();

		TextureAtlasSprite texture = spriteGetter.apply(IncenseStickModelLoader.MATERIAL_INCENSE_STICK);

		// Bounds
		float w = 0.1f;
		float h = 0.8f * burnHeight;

		float x1 = 0.5f - (w / 2);
		float y1 = 0.0f;
		float z1 = 0.5f - (w / 2);

		float x2 = x1 + w;
		float y2 = y1 + h;
		float z2 = z1 + w;

		// Top
		//quads.add(ClientTools.createQuad(v(x1, y2, z1), v(x1, y2, z2), v(x2, y2, z2), v(x2, y2, z1), rotation, texture));
		quads.add(ClientTools.createQuad(v(1, 1, 0), v(1, 1, 1), v(0, 1, 1), v(0, 1, 0), DIRECTION_UP, rotation, texture));
//		quads.add(ClientTools.createQuad(v(0, 0, 0), v(1, 0, 0), v(1, 0, 1), v(0, 0, 1), rotation, texture));
		
		// Sides
		
		// Base
		
		return quads;
		
	}
	
}
