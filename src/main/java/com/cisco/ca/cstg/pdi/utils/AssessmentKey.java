package com.cisco.ca.cstg.pdi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.Ostermiller.util.Base64;

public class AssessmentKey {
	
	private static final String ALGORITHIM = "Blowfish";
	private static final String DIGEST = "MD5";
	private static final int RADIX_RANGE = 16;
	private static final String CHARSET_NAME = "UTF-8";
	private String password;
	private String metaData;
	private String key;
	private String assessmentKeyFile;


	public AssessmentKey() {
		password = "dcaf";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMetaData(String metadata) throws NoSuchAlgorithmException{
		/**
		 * Prefix supplied metadata with MD5 digest of the data (for validation
		 * purposes) prior to saving it
		 **/
		MessageDigest digest = MessageDigest.getInstance(DIGEST);

		digest.update(metadata.getBytes(Charset.forName(CHARSET_NAME)));
		String b64hash = Base64.encodeToString(digest.digest()) + ":" + metadata;

		metaData = b64hash;
	}

	public String getMetaData() {
		return metaData;

	}

	public void setAssessmentKey(String inkey) {
		key = inkey;

	}

	public void resetAssessmentKey() {
		key = "";
		metaData = "";

	}

	public String getAssessmentKey() {
		return key;

	}

	public String getAssessmentKeyFileName() {
		return assessmentKeyFile;
	}

	public void setAssessmentKeyFileName(String name) {
		assessmentKeyFile = name;
	}

	public static String encrypt(String input, String pwd) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		byte[] raw = pwd.getBytes(Charset.forName(CHARSET_NAME));
		SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHIM);
		Cipher cipher = Cipher.getInstance(ALGORITHIM);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] output = cipher.doFinal(input.getBytes(Charset.forName(CHARSET_NAME)));

		BigInteger n = new BigInteger(output);

		String b64hidden = Base64.encodeToString(n.toString(RADIX_RANGE).getBytes(Charset.forName(CHARSET_NAME)));

		return b64hidden;
	}

	public static String decrypt(String assessmentKey, String pwd) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] raw = pwd.getBytes(Charset.forName(CHARSET_NAME));
		SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHIM);
		Cipher cipher = Cipher.getInstance(ALGORITHIM);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);

		String input = Base64.decode(assessmentKey);

		BigInteger n = new BigInteger(input, RADIX_RANGE);
		byte[] encoding = n.toByteArray();

		byte[] output = cipher.doFinal(encoding);
		return new String(output, Charset.forName(CHARSET_NAME));
	}

	public String encrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] raw = password.getBytes(Charset.forName(CHARSET_NAME));
		SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHIM);
		Cipher cipher = Cipher.getInstance(ALGORITHIM);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] output = cipher.doFinal(metaData.getBytes(Charset.forName(CHARSET_NAME)));

		BigInteger n = new BigInteger(output);

		String b64hidden = Base64.encodeToString(n.toString(RADIX_RANGE).getBytes(Charset.forName(CHARSET_NAME)));

		key = b64hidden;

		return b64hidden;
	}

	public String encryptToFile() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		BufferedWriter out = null;
		try {
			byte[] raw = password.getBytes(Charset.forName(CHARSET_NAME));
			SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHIM);
			Cipher cipher = Cipher.getInstance(ALGORITHIM);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] output = cipher.doFinal(metaData.getBytes(Charset.forName(CHARSET_NAME)));
			BigInteger n = new BigInteger(output);
			String b64hidden = Base64.encodeToString(n.toString(RADIX_RANGE).getBytes(Charset.forName(CHARSET_NAME)));
			key = b64hidden;

			out = new BufferedWriter(new FileWriterWithEncoding(assessmentKeyFile, CHARSET_NAME));
			out.write(b64hidden);
			return b64hidden;
		} finally {
			if(out != null) {
				out.close();
			}				
		}
	}

	public String decryptFromFile() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		BufferedReader in = null;
		try {
			StringBuilder b64input = new StringBuilder();
			in = new BufferedReader(new InputStreamReader(new FileInputStream(assessmentKeyFile), Charset.forName(CHARSET_NAME)));
			String line;
			while ((line = in.readLine()) != null) {
				b64input.append(line);
			}

			String input = Base64.decode(new String(b64input));
			BigInteger n = new BigInteger(input, RADIX_RANGE);
			byte[] encoding = n.toByteArray();

			byte[] raw = password.getBytes(Charset.forName(CHARSET_NAME));
			SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHIM);
			Cipher cipher = Cipher.getInstance(ALGORITHIM);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] output = cipher.doFinal(encoding);

			String clear = new String(output, Charset.forName(CHARSET_NAME));

			metaData = clear;
			return metaData;

		} finally {
			if(in != null) {
				in.close();
			}				
		}
	}

	public String decrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] raw = password.getBytes(Charset.forName(CHARSET_NAME));
		SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHIM);
		Cipher cipher = Cipher.getInstance(ALGORITHIM);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);

		String input = Base64.decode(key);
		BigInteger n = new BigInteger(input, RADIX_RANGE);
		byte[] encoding = n.toByteArray();

		byte[] output = cipher.doFinal(encoding);
		String clear = new String(output, Charset.forName(CHARSET_NAME));
		metaData = clear;

		return clear.indexOf(':') > 0 ? clear.substring(clear.lastIndexOf(':') + 1): clear;
	}

	public boolean validate() throws NoSuchAlgorithmException {
		StringTokenizer tok = new StringTokenizer(metaData, ":");
		StringBuilder rest = new StringBuilder();
		String digest = null;

		digest = tok.nextToken();
		rest.append(":");
		rest.append(tok.nextToken());
		while (tok.hasMoreTokens()) {
			rest.append( ":");
			rest.append(tok.nextToken());
		}
		rest.append(":");
		MessageDigest digester = MessageDigest.getInstance(DIGEST);

		digester.update(rest.toString().getBytes(Charset.forName(CHARSET_NAME)));

		String b64hash = Base64.encodeToString(digester.digest());


		if (b64hash.equals(digest)) {
			return true;
		}

		return false;
	}
}