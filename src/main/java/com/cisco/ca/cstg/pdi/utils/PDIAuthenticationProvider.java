package com.cisco.ca.cstg.pdi.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cisco.ca.cstg.pdi.pojos.Credentials;
import com.cisco.ca.cstg.pdi.services.login.PDILoginService;

@Component("pdiAuthenticationProvider")
public class PDIAuthenticationProvider implements UserDetailsService {

	@Autowired
	private PDILoginService pdiLoginService;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		Credentials userCredentials = pdiLoginService.findByUserName(userName);
		
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority("user"));

		List<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>(setAuths);
		return new User(userCredentials.getUsername(), userCredentials.getPassword(), grantedAuth);
	}
}