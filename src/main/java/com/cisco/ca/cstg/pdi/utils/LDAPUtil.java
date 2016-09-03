package com.cisco.ca.cstg.pdi.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author avinasin
 * 
 */

public final class LDAPUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(LDAPUtil.class);
	private static final String UNKNOWN = "unknown";
	
	/* Adding for PROD */
	private static final String PROVIDER_URL = "ldap://dsx.cisco.com:389/OU=ccoentities,O=cco.cisco.com";
	
	private LDAPUtil() {
	}

	private static String getPropertiesValue(String uid, String property)
			throws Exception {
		String value = UNKNOWN;
		Hashtable<String, String> env = new Hashtable<String, String>();
		DirContext ctx = null;
		try {
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");

			/*
			 * Adding for Global since PROD is not working for LDAP active
			 * directory
			 */
			env.put(Context.PROVIDER_URL, "ldap://ds-global.cisco.com:389");


			env.put(Context.SECURITY_AUTHENTICATION, "simple");

			/* Adding for PROD */
			env.put(Context.SECURITY_PRINCIPAL,
					"cn=ada.gen,ou=Generics,ou=Cisco Users,dc=cisco,dc=com");


			env.put(Context.SECURITY_CREDENTIALS, "CiscoADA123");
			ctx = new InitialDirContext(env);
			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			/* Adding for PROD */
			String baseDN = "OU=Cisco Users,DC=cisco,DC=com";

			String filter = "(&(objectclass=user)(cn=" + uid + "))";
			NamingEnumeration<SearchResult> answer = ctx.search(baseDN, filter,
					ctls);
			if (answer.hasMore()) {
				SearchResult sr = answer.next();
				Attributes attrs = sr.getAttributes();
				value = attrs.get(property).get().toString();
			}			
		} catch (Exception e) {
			throw e;
		} finally {
			if (ctx != null){
				ctx.close();}
		}
		return value;
	}

	private static String getPropertiesValue(String url, String uid,
			String property) throws NamingException {
		String value = UNKNOWN;
		Hashtable<String, String> env = new Hashtable<String, String>();
		DirContext ctx = null;
		try {
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, url);
			ctx = new InitialDirContext(env);
			Attributes attrs = ctx.getAttributes("uid=" + uid);
			value = attrs.get(property).get().toString();
		} catch (NamingException ne) {
			throw ne;
		} finally {
			if (ctx != null){
				ctx.close();}
		}
		return value;
	}

	public static boolean validateUserByCec(String userCec) {
		boolean isValid = false;
		try {
			String cec = getPropertiesValue(userCec, "cn");
			if (!UNKNOWN.equals(cec)) {
				isValid = true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured in validateUserByCec method : ", e);
		}
		return isValid;
	}

	public static boolean validateUserByCdc(String userCec) {
		boolean isValid = false;
		try {
			String cec = getPropertiesValue(PROVIDER_URL, userCec, "cn");
			if (!UNKNOWN.equals(cec)) {
				isValid = true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured in validateUserByCdc method : ", e);
		}
		return isValid;
	}
	
	/**
	 * This method is used to validate user id
	 * @param userId
	 * @return true if valid else false
	 */
	public static boolean isUserIdValid(String userId) {
		boolean isValid = validateUserByCec(userId);
		if (!isValid) {
			isValid = validateUserByCdc(userId);
		}
		return isValid;
	}		
}
