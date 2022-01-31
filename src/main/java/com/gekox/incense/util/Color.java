package com.gekox.incense.util;

public class Color {

	// Color used by vanilla minecraft for guis and whatnot
	public static final int MAGIC_COLOR = 4210752;

	// Minecraft colors
	public static Color WHITE = new Color(221, 221, 221);
	public static Color ORANGE = new Color(219, 125, 62);
	public static Color MAGENTA = new Color(179, 80, 188);
	public static Color LIGHT_BLUE = new Color(107, 138, 201);
	public static Color YELLOW = new Color(177, 166, 39);
	public static Color LIME = new Color(65, 174, 56);
	public static Color PINK = new Color(208, 132, 153);
	public static Color GRAY = new Color(64, 64, 64);
	public static Color LIGHT_GRAY = new Color(154, 161, 161);
	public static Color CYAN = new Color(46, 110, 137);
	public static Color PURPLE = new Color(126, 61, 181);
	public static Color BLUE = new Color(46, 56, 141);
	public static Color BROWN = new Color(79, 50, 31);
	public static Color GREEN = new Color(53, 70, 27);
	public static Color RED = new Color(150, 52, 48);
	public static Color BLACK = new Color(25, 22, 22);
	public static Color CLEAR = new Color(255, 255, 255, 0);

	// Text colors
	public static final String CODE_BLACK = (char) 167 + "0";
	public static final String CODE_BLUE = (char) 167 + "1";
	public static final String CODE_GREEN = (char) 167 + "2";
	public static final String CODE_CYAN = (char) 167 + "3";
	public static final String CODE_RED = (char) 167 + "4";
	public static final String CODE_PURPLE = (char) 167 + "5";
	public static final String CODE_ORANGE = (char) 167 + "6";
	public static final String CODE_LIGHT_GRAY = (char) 167 + "7";
	public static final String CODE_GRAY = (char) 167 + "8";
	public static final String CODE_LIGHT_BLUE = (char) 167 + "9";
	public static final String CODE_LIME = (char) 167 + "a";
	public static final String CODE_BRIGHT_BLUE = (char) 167 + "b";
	public static final String CODE_PINK = (char) 167 + "c";
	public static final String CODE_MAGENTA = (char) 167 + "d";
	public static final String CODE_YELLOW = (char) 167 + "e";
	public static final String CODE_WHITE = (char) 167 + "f";
	public static final String CODE_OBFUSCATED = (char) 167 + "k";
	public static final String CODE_CODE_BOLD = (char) 167 + "l";
	public static final String CODE_STRIKETHROUGH = (char) 167 + "m";
	public static final String CODE_UNDERLINE = (char) 167 + "n";
	public static final String CODE_ITALIC = (char) 167 + "o";
	public static final String CODE_END = (char) 167 + "r";
	
	public static String getColorCodeFromType(IncenseType type) {
		return switch (type) {
			case SOOTY -> CODE_GRAY;
			case PASSIVE -> CODE_GREEN;
			case HOSTILE -> CODE_PINK;
			case NEUTRAL -> CODE_LIGHT_BLUE;
			case ENDER -> CODE_WHITE;
			case NETHER -> CODE_RED;
			case VILLAGE -> CODE_LIME;
			case ILLAGE -> CODE_GRAY;
			case WATER -> CODE_BLUE;
			case GOLEM -> CODE_LIGHT_GRAY;
			default -> CODE_WHITE;
		};

	}

	// Particle colors
	public static Color INCENSE_SOOTY = new Color(100, 100, 100);
	public static Color INCENSE_PASSIVE = new Color(40, 130, 40);
	public static Color INCENSE_HOSTILE = new Color(200, 40, 40);
	public static Color INCENSE_NEUTRAL = new Color(122, 169, 250);
	public static Color INCENSE_NETHER = new Color(145, 10, 10);
	public static Color INCENSE_ENDER = new Color(247, 241, 153);
	public static Color INCENSE_VILLAGE = new Color(0, 190, 25);
	public static Color INCENSE_ILLAGE = new Color(116, 116, 116);
	public static Color INCENSE_WATER = new Color(100, 100, 230);
	public static Color INCENSE_GOLEM = new Color(200, 200, 200);

	public static Color getColorFromType(IncenseType type) {
		return switch (type) {
			case SOOTY -> INCENSE_SOOTY;
			case PASSIVE -> INCENSE_PASSIVE;
			case HOSTILE -> INCENSE_HOSTILE;
			case NEUTRAL -> INCENSE_NEUTRAL;
			case ENDER -> INCENSE_ENDER;
			case NETHER -> INCENSE_NETHER;
			case VILLAGE -> INCENSE_VILLAGE;
			case ILLAGE -> INCENSE_ILLAGE;
			case WATER -> INCENSE_WATER;
			case GOLEM -> INCENSE_GOLEM;
			
			default -> new Color(255, 255, 255);
			
		};
	}

	public int r;
	public int g;
	public int b;
	public int a;


	public Color(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public Color(int r, int g, int b) {
		this(r, g, b, 255);
	}

	public Color() {
		this(0, 0, 0);
	}

	// http://stackoverflow.com/questions/4801366/convert-rgb-values-into-integer-pixel
	public int toInt() {
		//return ((this.r&0x0ff)<<16)|((this.g&0x0ff)<<8)|(this.b&0x0ff);
		
		int rgb = this.r;
		rgb = (rgb << 8) + this.g;
		rgb = (rgb << 8) + this.b;
		
		return rgb;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d, %d, %d)", r, g, b, a);
	}

	@Override
	public boolean equals(Object other) {

		if(other instanceof Color c2) {
			return (this.r == c2.r && this.g == c2.g && this.b == c2.b && this.a == c2.a);
		}

		return false;
	}

	public Color copy() {
		return new Color(this.r, this.g, this.b, this.a);
	}

}