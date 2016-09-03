package com.cisco.ca.cstg.pdi.services.license;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.cisco.ca.cstg.pdi.exceptions.LicenseParsingException;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.EncryptDecrypt;
import com.cisco.cstg.dcaf.encryptdecrypt.EncryptDecryptLicense;

@Component("licenseCryptoService")
public class LicenseCryptoServiceImpl extends EncryptDecrypt implements
		LicenseCryptoService {

	private static final String ALGORITHM_BLOWFISH = "Blowfish";	
	private static EncryptDecryptLicense encryptDecryptLicense = new EncryptDecryptLicense();
	
	private String metaData;

	public void setMetaData(String metadata) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(Constants.MD5);

		digest.update(metadata.getBytes(Charset.forName(Constants.UTF8)));

		String b64hash = Base64.encodeBase64String(digest.digest());

		this.metaData = b64hash + ":" + metadata;
	}

	public void encryptToFile() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, IOException {
		BufferedWriter out = null;
		try {
			byte[] raw = getPassword().getBytes(Charset.forName(Constants.UTF8));
			SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM_BLOWFISH);
			Cipher cipher = null;
			cipher = Cipher.getInstance(ALGORITHM_BLOWFISH);
			cipher.init(1, skeySpec);
			byte[] output = cipher.doFinal(getMetaData().getBytes());
			BigInteger n = new BigInteger(output);

			String b64hidden = Base64.encodeBase64String(n.toString(16).getBytes());
			setAssessmentKey(b64hidden);

			out = new BufferedWriter(new FileWriter(
					this.getAssessmentKeyFileName()));
			out.write(b64hidden);
		} finally {
			if(out != null) {
				out.close();
			}				
		}
	}

	public String decryptFromFile() throws IOException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		BufferedReader in = null;
		try {
			StringBuilder b64input = new StringBuilder();
			in = new BufferedReader(new FileReader(this.getAssessmentKeyFileName()));
			String line;
			while ((line = in.readLine()) != null) {
				b64input.append( line );
			}
			
			byte[] encoding = Base64.decodeBase64(new String(b64input));
			byte[] raw = getPassword().getBytes(Charset.forName(Constants.UTF8));

			SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM_BLOWFISH);
			Cipher cipher = Cipher.getInstance(ALGORITHM_BLOWFISH);
			cipher.init(2, skeySpec);

			byte[] output = cipher.doFinal(encoding);
			String clear = new String(output,Charset.forName(Constants.UTF8));
			this.metaData = clear;
			
			return this.metaData;
		} finally {
			if(in != null) {
				in.close();
			}				
		}
	}

	public String encrypt() throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		byte[] raw = getPassword().getBytes(Charset.forName(Constants.UTF8));
		SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM_BLOWFISH);
		Cipher cipher = Cipher.getInstance(ALGORITHM_BLOWFISH);
		cipher.init(1, skeySpec);
		byte[] output = cipher.doFinal(this.metaData.getBytes());

		String b64hidden = Base64.encodeBase64String(output);
		setAssessmentKey(b64hidden);

		return b64hidden;
	}

	public String decrypt(String key) {
		return encryptDecryptLicense.decrypt(key, null);
	}

	@Override
	public String decrypt(String key, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		setPassword(password);
		return decrypt(key);
	}

	@Override
	public boolean validate(String licenseValues) {
		int separatorIndex = licenseValues.indexOf(':');
		String digest = licenseValues.substring(0, separatorIndex);
		String rest = licenseValues.substring(separatorIndex + 1);

		String b64hash = null;
		try {
			MessageDigest digester = MessageDigest.getInstance(Constants.MD5);
			digester.update(rest.getBytes(Charset.forName(Constants.UTF8)));
			b64hash = Base64.encodeBase64String(digester.digest());
		} catch (NoSuchAlgorithmException nsae) {
			throw new LicenseParsingException("No such algorithm exception while trying to validate", nsae);
		}
		return b64hash.trim().equals(digest);
	}
}