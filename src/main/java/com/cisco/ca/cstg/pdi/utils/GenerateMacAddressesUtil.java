package com.cisco.ca.cstg.pdi.utils;

/*
 * author akasaxen 25th june 2014 4:46 pm
 * following class is utility class for generating Mac-Address values from a given start range + number of Mac-Addresses values required
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GenerateMacAddressesUtil {

	private static final int SIXTEEN = 16;
	private static final int ZERO = 0;
	private static final int SIX = 6;
	private static final int FIVE = 5;
	private static final int FOUR = 4;
	private static final int THREE = 3;
	private static final int TWO = 2;
	private static final int ONE = 1;
	
	private GenerateMacAddressesUtil(){
		
	}

	public static String addToPoolValue(String startValue, String numberOfPools)

	{
		Matcher m = Pattern
				.compile(
						"([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2})")
				.matcher(startValue);
		if (m.find() && m.group(ONE) != null && m.group(TWO) != null && m.group(THREE) != null
					&& m.group(FOUR) != null && m.group(FIVE) != null
					&& m.group(SIX) != null) {

			int count = Integer.parseInt(numberOfPools);

			if (count != 0) {
				int decimal = Integer.parseInt(
						m.group(FOUR) + m.group(FIVE) + m.group(SIX), SIXTEEN);

				decimal = decimal + count - 1;

				String hex = Integer.toHexString(decimal);
				hex = hex.toUpperCase();
				if (hex.matches("[A-F0-9]{1}")) {
					return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE)
							+ ":" + m.group(FOUR) + ":" + m.group(FIVE) + ":"
							+ m.group(SIX).charAt(ZERO) + hex;
				} else if (hex.matches("[A-F0-9]{2}")) {
					return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE)
							+ ":" + m.group(FOUR) + ":" + m.group(FIVE) + ":"
							+ hex;
				} else if (hex.matches("[A-F0-9]{3}")) {

					return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE)
							+ ":" + m.group(FOUR) + ":" + m.group(FIVE).charAt(ZERO)
							+ hex.charAt(ZERO) + ":" + hex.charAt(ONE)
							+ hex.charAt(TWO);

				} else if (hex.matches("[A-F0-9]{4}")) {

					return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE)
							+ ":" + m.group(FOUR) + ":" + hex.charAt(ZERO)
							+ hex.charAt(ONE) + ":" + hex.charAt(TWO)
							+ hex.charAt(THREE);

				} else if (hex.matches("[A-F0-9]{5}")) {

					return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE)
							+ ":"

							+ m.group(FOUR).charAt(ZERO) + hex.charAt(ZERO) + ":"
							+ hex.charAt(ONE) + hex.charAt(TWO) + ":"
							+ hex.charAt(THREE) + hex.charAt(FOUR);

				} else if (hex.matches("[A-F0-9]{6}")) {

					return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE)

					+ ":" + hex.charAt(ZERO) + hex.charAt(ONE) + ":"
							+ hex.charAt(TWO) + hex.charAt(THREE) + ":"
							+ hex.charAt(FOUR) + hex.charAt(FIVE);
				}
			}
		}
		return null;
	}
}
