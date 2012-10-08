package com.ubs.openspace.utils;

public class StackUtils {

	public static String getMethodName(){
		return getMethodName(1);
	}
	
	public static String getMethodName(final int depth) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[ste.length - 1 - depth].getMethodName();
	}
}
