package com.cisco.ca.cstg.pdi.utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class EncryptDecrypt
{
  private String password;
  private String metaData;
  private String key;
  private String assessmentKeyFile;

  protected EncryptDecrypt()
  {
    this.password = "sanhc";
  }
  public abstract void setMetaData(String paramString) throws NoSuchAlgorithmException;

  public String getMetaData() {
    return this.metaData;
  }
  public void setAssessmentKey(String inkey) {
    this.key = inkey;
  }

  public void resetAssessmentKey()
  {
    this.key = "";
    this.metaData = "";
  }

  public String getAssessmentKey()
  {
    return this.key;
  }

  public String getAssessmentKeyFileName()
  {
    return this.assessmentKeyFile;
  }

  public void setAssessmentKeyFileName(String name) {
    this.assessmentKeyFile = name;
  }

  public abstract void encryptToFile()
    throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException;

  public abstract String decryptFromFile()
    throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

  public abstract String encrypt()
    throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

  public abstract String decrypt(String paramString)
    throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;

  public String getPassword()
  {
    return this.password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }
}