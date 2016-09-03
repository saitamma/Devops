package com.cisco.ca.cstg.pdi.utils;

/*
 * author akasaxen 25th june 2014 4:46 pm
 * following class is utility class for generating Mac-Address values from a given start range + number of Mac-Addresses values required
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GenerateUUIDPoolUtil {

	private static final int _13 = 13;
	private static final int _12 = 12;
	private static final int _11 = 11;
	private static final int _10 = 10;
	private static final int _9 = 9;
	private static final int _8 = 8;
	private static final int _7 = 7;
	private static final int _6 = 6;
	private static final int _5 = 5;
	private static final int _4 = 4;
	private static final int _3 = 3;
	private static final int _2 = 2;
	private static final int _1 = 1;

	private GenerateUUIDPoolUtil() {

	}

	public static String addToPoolValue(String startValue, String numberOfPools)

	{
		Matcher m = Pattern
				.compile(
						"([0-9A-F]{4})-([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])([0-9A-F])")
				.matcher(startValue);
		if (m.find() && m.group(_1) != null && m.group(_2) != null
					&& m.group(_3) != null && m.group(_4) != null
					&& m.group(_5) != null && m.group(_6) != null
					&& m.group(_7) != null && m.group(_8) != null
					&& m.group(_9) != null && m.group(_10) != null
					&& m.group(_11) != null && m.group(_12) != null
					&& m.group(_13) != null) {

			int count = Integer.parseInt(numberOfPools);
			if (count != 0) {
				String constructUuid = m.group(_2) + m.group(_3)
						+ m.group(_4) + m.group(_5) + m.group(_6)
						+ m.group(_7) + m.group(_8) + m.group(_9)
						+ m.group(_10) + m.group(_11) + m.group(_12)
						+ m.group(_13);
				Long decimal = Long.valueOf(constructUuid, 16);

				decimal = decimal + count - 1;

				String hex = Long.toHexString(decimal);

				hex = hex.toUpperCase();
				// 9300014
				char[] hexchar = hex.toCharArray();
				int newLength = hexchar.length;
				int difference = 12 - newLength;
				String value = "";

				for (int i = 2; i <= difference + 1; i++) {
					value = value.concat(m.group(i).toString());
				}

				return m.group(_1) + "-" + value + hex;

			}
		}
		return null;
	}
}
