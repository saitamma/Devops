package com.cisco.ca.cstg.pdi.services.license;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface LicenseCryptoService {

	String decrypt(String key) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException;

	String decrypt(String key, String string) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException;

	boolean validate(String licenseValues);

}
