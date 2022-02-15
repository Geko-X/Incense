package com.gekox.incense.client.render;

//public class IncenseStickModelLoader implements IModelLoader<IncenseStickModelLoader.IncenseStickModelGeometry> {
//
//	public static final ResourceLocation INCENSE_STICK_LOADER = new ResourceLocation(Constants.MODID, "incense_stick_loader");
//
//	public static final ResourceLocation INCENSE_STICE_TEXTURE_SIDE = new ResourceLocation(Constants.MODID, "block/incense_stick_side");
//	public static final ResourceLocation INCENSE_STICE_TEXTURE_TOP = new ResourceLocation(Constants.MODID, "block/incense_stick_top");
//
//	public static final Material MATERIAL_INCENSE_STICK_SIDE = ForgeHooksClient.getBlockMaterial(INCENSE_STICE_TEXTURE_SIDE);
//	public static final Material MATERIAL_INCENSE_STICK_TOP = ForgeHooksClient.getBlockMaterial(INCENSE_STICE_TEXTURE_TOP);
//
//	@Override
//	public void onResourceManagerReload(ResourceManager resourceManager) {
//	}
//
//	@Override
//	public IncenseStickModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
//		return new IncenseStickModelGeometry();
//	}
//
//	public static class IncenseStickModelGeometry implements IModelGeometry<IncenseStickModelGeometry> {
//
//		@Override
//		public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
//			return new IncenseStickBakedModel(modelTransform, spriteGetter, overrides, owner.getCameraTransforms());
//		}
//
//		@Override
//		public Collection<Material> getTextures(IModelConfiguration owner, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
//			return List.of(MATERIAL_INCENSE_STICK_SIDE, MATERIAL_INCENSE_STICK_TOP);
//		}
//	}
//	
//}
