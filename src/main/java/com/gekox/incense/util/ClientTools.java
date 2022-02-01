package com.gekox.incense.util;

// https://github.com/McJty/TutorialV3/blob/main/src/main/java/com/example/tutorialv3/varia/ClientTools.java

import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

public class ClientTools {

	public static final Vector3f DIRECTION_UP 		= new Vector3f( 0,  1,  0);
	public static final Vector3f DIRECTION_DOWN 	= new Vector3f( 0, -1,  0);
	public static final Vector3f DIRECTION_LEFT 	= new Vector3f( 1,  0,  0);
	public static final Vector3f DIRECTION_RIGHT 	= new Vector3f(-1,  0,  0);
	public static final Vector3f DIRECTION_FORWARD 	= new Vector3f( 0,  0,  1);
	public static final Vector3f DIRECTION_BACK 	= new Vector3f( 0,  0, -1);
	
	private static void putVertex(BakedQuadBuilder builder, Vector3f normal, Vector4f vector,
								  float u, float v, TextureAtlasSprite sprite) {

		var elements = builder.getVertexFormat().getElements().asList();
		for (int j = 0 ; j < elements.size() ; j++) {
			var e = elements.get(j);
			switch (e.getUsage()) {
				case POSITION -> builder.put(j, vector.x(), vector.y(), vector.z(), 1.0f);
				case COLOR    -> builder.put(j, 1.0f, 1.0f, 1.0f, 1.0f);
				case UV       -> putVertexUV(builder, u, v, sprite, j, e);
				case NORMAL   -> builder.put(j, normal.x(), normal.y(), normal.z());
				default       -> builder.put(j);
			}
		}
	}

	private static void putVertexUV(BakedQuadBuilder builder, float u, float v, TextureAtlasSprite sprite, int j, VertexFormatElement e) {
		switch (e.getIndex()) {
			case 0  -> builder.put(j, sprite.getU(u), sprite.getV(v));
			case 2  -> builder.put(j, (short) 0, (short) 0);
			default -> builder.put(j);
		}
	}
	
	private static Vector3f getNormal(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4) {
		Vector3f normal = v3.copy();
		normal.sub(v2);
		Vector3f temp = v1.copy();
		temp.sub(v2);
		normal.cross(temp);
		normal.normalize();
		
		return normal;
	}

	public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Transformation rotation, TextureAtlasSprite sprite) {
		return createQuad(v1, v2, v3, v4, getNormal(v1, v2, v3, v4), rotation, sprite, 0, 0, sprite.getWidth(), sprite.getHeight());
	}

	public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Transformation rotation, TextureAtlasSprite sprite, int tw, int th) {
		return createQuad(v1, v2, v3, v4, getNormal(v1, v2, v3, v4), rotation, sprite, 0, 0, tw, th);
	}

	public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Transformation rotation, TextureAtlasSprite sprite, int tu0, int tv0, int tu1, int tv1) {
		return createQuad(v1, v2, v3, v4, getNormal(v1, v2, v3, v4), rotation, sprite, tu0, tv0, tu1, tv1);
	}
	
	public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Vector3f normal, Transformation rotation, TextureAtlasSprite sprite) {
		return createQuad(v1, v2, v3, v4, normal, rotation, sprite, 0, 0, sprite.getWidth(), sprite.getHeight());
	}

	public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Vector3f normal, Transformation rotation, TextureAtlasSprite sprite, int tu0, int tv0, int tu1, int tv1) {

		rotation = rotation.blockCenterToCorner();
		rotation.transformNormal(normal);

		Vector4f vv1 = new Vector4f(v1); rotation.transformPosition(vv1);
		Vector4f vv2 = new Vector4f(v2); rotation.transformPosition(vv2);
		Vector4f vv3 = new Vector4f(v3); rotation.transformPosition(vv3);
		Vector4f vv4 = new Vector4f(v4); rotation.transformPosition(vv4);

		var builder = new BakedQuadBuilder(sprite);
		builder.setQuadOrientation(Direction.getNearest(normal.x(), normal.y(), normal.z()));
		putVertex(builder, normal, vv1, tu0, tv0, sprite);
		putVertex(builder, normal, vv2, tu0, tv1, sprite);
		putVertex(builder, normal, vv3, tu1, tv1, sprite);
		putVertex(builder, normal, vv4, tu1, tv0, sprite);
		return builder.build();
	}

	public static Vector3f v(float x, float y, float z) {
		return new Vector3f(x, y, z);
	}
}