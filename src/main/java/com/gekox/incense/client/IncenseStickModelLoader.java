package com.gekox.incense.client;

import com.gekox.incense.Constants;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class IncenseStickModelLoader implements IModelLoader<IncenseStickModelLoader.IncenseStickModelGeometry> {

	public static final ResourceLocation INCENSE_STICK_LOADER = new ResourceLocation(Constants.MODID, "incense_stick_loader");

	public static final ResourceLocation INCENSE_STICE_TEXTURE = new ResourceLocation(Constants.MODID, "block/incense_stick");
	public static final Material MATERIAL_INCENSE_STICK = ForgeHooksClient.getBlockMaterial(INCENSE_STICE_TEXTURE);

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
	}

	@Override
	public IncenseStickModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
		return new IncenseStickModelGeometry();
	}

	public static class IncenseStickModelGeometry implements IModelGeometry<IncenseStickModelGeometry> {

		@Override
		public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
			return new IncenseStickBakedModel(modelTransform, spriteGetter, overrides, owner.getCameraTransforms());
		}

		@Override
		public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
			return List.of(MATERIAL_INCENSE_STICK);
		}
	}
	
}
