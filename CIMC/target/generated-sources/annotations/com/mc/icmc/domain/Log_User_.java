package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import java.util.UUID;

@StaticMetamodel(Log_User.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Log_User_ {

	
	/**
	 * @see com.mc.icmc.domain.Log_User#logUserIp
	 **/
	public static volatile SingularAttribute<Log_User, String> logUserIp;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#module
	 **/
	public static volatile SingularAttribute<Log_User, Modules> module;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#focus
	 **/
	public static volatile SingularAttribute<Log_User, Integer> focus;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#logUserDate
	 **/
	public static volatile SingularAttribute<Log_User, LocalDateTime> logUserDate;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#macVendeur
	 **/
	public static volatile SingularAttribute<Log_User, String> macVendeur;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#logUserId
	 **/
	public static volatile SingularAttribute<Log_User, Long> logUserId;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#action
	 **/
	public static volatile SingularAttribute<Log_User, Actions> action;
	
	/**
	 * @see com.mc.icmc.domain.Log_User
	 **/
	public static volatile EntityType<Log_User> class_;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#user
	 **/
	public static volatile SingularAttribute<Log_User, Users> user;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#logUserUuid
	 **/
	public static volatile SingularAttribute<Log_User, UUID> logUserUuid;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#logUserTitle
	 **/
	public static volatile SingularAttribute<Log_User, String> logUserTitle;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#logUserAgent
	 **/
	public static volatile SingularAttribute<Log_User, String> logUserAgent;
	
	/**
	 * @see com.mc.icmc.domain.Log_User#logUserAgentString
	 **/
	public static volatile SingularAttribute<Log_User, String> logUserAgentString;

	public static final String LOG_USER_IP = "logUserIp";
	public static final String MODULE = "module";
	public static final String FOCUS = "focus";
	public static final String LOG_USER_DATE = "logUserDate";
	public static final String MAC_VENDEUR = "macVendeur";
	public static final String LOG_USER_ID = "logUserId";
	public static final String ACTION = "action";
	public static final String USER = "user";
	public static final String LOG_USER_UUID = "logUserUuid";
	public static final String LOG_USER_TITLE = "logUserTitle";
	public static final String LOG_USER_AGENT = "logUserAgent";
	public static final String LOG_USER_AGENT_STRING = "logUserAgentString";

}

