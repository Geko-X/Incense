package com.gekox.incense.util;

public class TextUtils {

	// Formats an int into a time format
	public static String secondsToTime(int sec) {
		return String.format("%02dm %02ds", sec / 60, sec % 60);
	}

}