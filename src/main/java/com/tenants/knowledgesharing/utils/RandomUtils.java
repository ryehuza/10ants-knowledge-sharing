package com.tenants.knowledgesharing.utils;

import java.util.Random;

public class RandomUtils {
	public static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		return (new Random()).nextInt((max - min) + 1) + min;
	}
}
