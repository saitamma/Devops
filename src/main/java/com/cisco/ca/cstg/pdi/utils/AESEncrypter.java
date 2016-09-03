package com.cisco.ca.cstg.pdi.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.utils.Constants;
 
public class AESEncrypter
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AESEncrypter.class);

	private Cipher ecipher;
	private Cipher dcipher;
	private String encryptionKeyFile;
	
	public String getEncryptionKeyFile() {
		return encryptionKeyFile;
	}

	public void setEncryptionKeyFile(String encryptionKeyFile) {
		this.encryptionKeyFile = encryptionKeyFile;
	}

	public AESEncrypter()
	{
		init();
	}

	
	public void encrypt(String inFilename, String outFileName)
	{
		InputStream in = null;
		OutputStream out = null;
		
		try
		{
			in = new FileInputStream(inFilename);

			// Bytes written to out will be encrypted
			out = new CipherOutputStream(new FileOutputStream(outFileName), ecipher);
			
			// Read in the cleartext bytes and write to out to encrypt
			int numRead = 0;
			
			// Buffer used to transport the bytes from one stream to another
			byte[] buf = new byte[1024];
			
			while ((numRead = in.read(buf)) >= 0)
			{
				out.write(buf, 0, numRead);
			}
		}
		catch (IOException e)
		{
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if(null != in) { in.close(); }
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
			try {
				if(null != out) { out.close(); }
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
	
	public void decrypt(String inFilename, String outFileName)
	{
		InputStream in = null;
		OutputStream out = null;
		try
		{
			// Bytes read from in will be decrypted
			in = new CipherInputStream(new FileInputStream(inFilename), dcipher);
			out = new FileOutputStream(outFileName);
			
			// Read in the decrypted bytes and write the cleartext to out
			int numRead = 0;
			
			// Buffer used to transport the bytes from one stream to another
			byte[] buf = new byte[1024];
			
			while ((numRead = in.read(buf)) >= 0)
			{
				out.write(buf, 0, numRead);
			}
		}
		catch (java.io.IOException e)
		{
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (null != in) { in.close(); }
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
			try {
				if (null != out) { out.close(); }
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
	
	private void init() {

		byte[] bytes = new byte[16];
		
		if (encryptionKeyFile == null || encryptionKeyFile.isEmpty())
		{
			encryptionKeyFile = PdiConfig.getProperty(Constants.PDI_HOME).concat(File.separator).concat(Constants.LICENSE_FILENAME);
		}
		
		InputStream in = null;
		SecretKeySpec spec = null;

		try
		{
			
			in = new BufferedInputStream(new FileInputStream(new File(encryptionKeyFile)));
			int result = in.read(bytes);
			if(result == 0) {
				//bytes are empty // do check for bytes input
			}
			spec = new SecretKeySpec(bytes,"AES");
			
			// Create an 8-byte initialization vector
			byte[] iv = new byte[]
			{
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
			};
			
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);

			ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			ecipher.init(Cipher.ENCRYPT_MODE, spec, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, spec, paramSpec);
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (null != in) { in.close(); }
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	public void generateKey(String filename) throws NoSuchAlgorithmException
	{
		// Generate a temporary key. In practice, you would save this key.
		// See also e464 Encrypting with DES Using a Pass Phrase.
		File f = new File(filename);
		KeyGenerator	kgen	=	KeyGenerator.getInstance("AES");
		kgen.init(128);
		SecretKey key			=	kgen.generateKey();
		byte[] bytes = key.getEncoded();

		OutputStream out = null;
		try {
			out = new FileOutputStream(f);
			out.write(bytes);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (null != out) { out.close(); }
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		
		LOGGER.info("{} bytes written to {}", bytes.length, filename);
	}
}
