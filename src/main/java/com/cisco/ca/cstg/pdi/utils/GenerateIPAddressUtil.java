package com.cisco.ca.cstg.pdi.utils;

/*
 * author akasaxen 25th june 2014 4:46 pm
 * following class is utility class for generating Mac-Address values from a given start range + number of Mac-Addresses values required
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GenerateIPAddressUtil {
	private static final int FOUR = 4;
	private static final int THREE = 3;
	private static final int TWO = 2;
	private static final int ONE = 1;
	final static int constant_255 = 255;
	
	private GenerateIPAddressUtil(){
		
	}

	public static String addToAddressValue(String startValue,
			String numberOfaddress)

	{
		String toString = "";

		if (Integer.parseInt(numberOfaddress) <= constant_255) {
			Matcher m = Pattern.compile(
					"(\\d{1,3}).(\\d+{1,3}).(\\d+{1,3}).(\\d+{1,3})").matcher(
					startValue);
			if (m.find() && m.group(ONE) != null && m.group(TWO) != null
						&& m.group(THREE) != null && m.group(FOUR) != null) {
				toString = Integer.toString(Integer
						.parseInt(numberOfaddress)
						+ Integer.parseInt(m.group(4)) - 1);

				if (Integer.parseInt(toString) <= constant_255) {
					return m.group(ONE) + "." + m.group(TWO) + "."
							+ m.group(THREE) + "." + toString;
				}

			}

			return numberOfaddress;
		}
		return numberOfaddress;
	}
}
