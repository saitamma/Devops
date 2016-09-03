package com.cisco.ca.cstg.pdi.utils;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySqlDialect extends MySQL5InnoDBDialect {
	@SuppressWarnings("deprecation")
	public MySqlDialect() {
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}
}
