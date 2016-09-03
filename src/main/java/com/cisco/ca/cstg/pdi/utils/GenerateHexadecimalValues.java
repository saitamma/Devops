package com.cisco.ca.cstg.pdi.utils;

/*
 * author akasaxen 25th june 2014 4:46 pm
 * following class is utility class for generating hexadecimal values from a given start range + number of hexadecial values required
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GenerateHexadecimalValues {

	private static final int EIGHT = 8;
	private static final int SEVEN = 7;
	private static final int SIX = 6;
	private static final int FIVE = 5;
	private static final int FOUR = 4;
	private static final int THREE = 3;
	private static final int TWO = 2;
	private static final int ONE = 1;
	private static final String MACADDRESS_REGEX = "([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2}):([0-9A-F]{2})";

	private GenerateHexadecimalValues() {

	}

	public static List<String> macAddressSplit(String startValue,
			String endValue) {
		String numberOfPools = "";
		String end = "";
		String start = "";
		Matcher mend = Pattern.compile(MACADDRESS_REGEX).matcher(endValue);
		if (mend.find()
				&& (mend.group(ONE) != null && mend.group(TWO) != null
						&& mend.group(THREE) != null && mend.group(FOUR) != null
						&& mend.group(FIVE) != null && mend.group(SIX) != null
						&& mend.group(SEVEN) != null && mend.group(EIGHT) != null)) {
			end = mend.group(SIX) + mend.group(SEVEN) + mend.group(EIGHT);
		}
		mend = Pattern.compile(MACADDRESS_REGEX).matcher(startValue);
		if (mend.find()
				&& (mend.group(ONE) != null && mend.group(TWO) != null
						&& mend.group(THREE) != null && mend.group(FOUR) != null
						&& mend.group(FIVE) != null && mend.group(SIX) != null
						&& mend.group(SEVEN) != null && mend.group(EIGHT) != null)) {
			start = mend.group(SIX) + mend.group(SEVEN) + mend.group(EIGHT);
		}

		int diff = Integer.parseInt(end, 16) - Integer.parseInt(start, 16);
		numberOfPools = Integer.toString(diff);

		List<String> result = new ArrayList<String>();
		result.add(startValue);
		Matcher m = Pattern.compile(MACADDRESS_REGEX).matcher(startValue);
		if (m.find()
				&& (m.group(ONE) != null && m.group(TWO) != null
						&& m.group(THREE) != null && m.group(FOUR) != null
						&& m.group(FIVE) != null && m.group(SIX) != null
						&& m.group(SEVEN) != null && m.group(EIGHT) != null)) {

			int count = Integer.parseInt(numberOfPools);
			int decimal = Integer.parseInt(
					m.group(SIX) + m.group(SEVEN) + m.group(EIGHT), 16);

			while (count > 0) {

				decimal = decimal + ONE;

				String hex = Integer.toHexString(decimal);
				hex = hex.toUpperCase();

				result.add(addMacPoolList(hex, m));

				count = count - ONE;

			}
		}

		return result;

	}

	private static String addMacPoolList(String hex, Matcher m) {

		if (hex.matches("[A-F0-9]{1}")) {
			return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE) + ":"
					+ m.group(FOUR) + ":" + m.group(FIVE) + ":" + m.group(SIX) + ":"
					+ m.group(SEVEN) + ":" + m.group(EIGHT).charAt(0) + hex;
		} else if (hex.matches("[A-F0-9]{2}")) {
			return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE) + ":"
					+ m.group(FOUR) + ":" + m.group(FIVE) + ":" + m.group(SIX) + ":"
					+ m.group(SEVEN) + ":" + hex;
		} else if (hex.matches("[A-F0-9]{3}")) {

			return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE) + ":"
					+ m.group(FOUR) + ":" + m.group(FIVE) + ":" + m.group(SIX) + ":"
					+ m.group(SEVEN).charAt(0) + hex.charAt(0) + ":"
					+ hex.charAt(ONE) + hex.charAt(TWO);

		} else if (hex.matches("[A-F0-9]{4}")) {

			return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE) + ":"
					+ m.group(FOUR) + ":" + m.group(FIVE) + ":" + m.group(SIX) + ":"
					+ hex.charAt(0) + hex.charAt(ONE) + ":" + hex.charAt(TWO)
					+ hex.charAt(THREE);

		} else if (hex.matches("[A-F0-9]{5}")) {

			return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE) + ":"
					+ m.group(FOUR) + ":" + m.group(FIVE) + ":"
					+ m.group(SIX).charAt(0) + hex.charAt(0) + ":"
					+ hex.charAt(ONE) + hex.charAt(TWO) + ":" + hex.charAt(THREE)
					+ hex.charAt(FOUR);

		} else if (hex.matches("[A-F0-9]{6}")) {

			return m.group(ONE) + ":" + m.group(TWO) + ":" + m.group(THREE) + ":"
					+ m.group(FOUR) + ":" + m.group(FIVE) + ":" + hex.charAt(0)
					+ hex.charAt(ONE) + ":" + hex.charAt(TWO) + hex.charAt(THREE)
					+ ":" + hex.charAt(FOUR) + hex.charAt(FIVE);

		}
		return hex;

	}

	public static String addToPoolValue(String startValue, String numberOfPools)

	{
		Matcher m = Pattern.compile(MACADDRESS_REGEX).matcher(startValue);
		if (m.find()
				&& (m.group(ONE) != null && m.group(TWO) != null
						&& m.group(THREE) != null && m.group(FOUR) != null
						&& m.group(FIVE) != null && m.group(SIX) != null
						&& m.group(SEVEN) != null && m.group(EIGHT) != null)) {

			int count = Integer.parseInt(numberOfPools);

			if (count != 0) {
				int decimal = Integer.parseInt(
						m.group(SIX) + m.group(SEVEN) + m.group(EIGHT), 16);

				decimal = decimal + count - ONE;

				String hex = Integer.toHexString(decimal);
				hex = hex.toUpperCase();
				return addMacPoolList(hex, m);
			}

		}
		return null;
	}
}
