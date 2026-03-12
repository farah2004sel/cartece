package com.mc.icmc.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;

@StaticMetamodel(Users.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Users_ {

	
	/**
	 * @see com.mc.icmc.domain.Users#userPassword
	 **/
	public static volatile SingularAttribute<Users, String> userPassword;
	
	/**
	 * @see com.mc.icmc.domain.Users#role
	 **/
	public static volatile SingularAttribute<Users, Roles> role;
	
	/**
	 * @see com.mc.icmc.domain.Users#userPhone
	 **/
	public static volatile SingularAttribute<Users, String> userPhone;
	
	/**
	 * @see com.mc.icmc.domain.Users#userConfirmedAt
	 **/
	public static volatile SingularAttribute<Users, LocalDateTime> userConfirmedAt;
	
	/**
	 * @see com.mc.icmc.domain.Users#userFirstName
	 **/
	public static volatile SingularAttribute<Users, String> userFirstName;
	
	/**
	 * @see com.mc.icmc.domain.Users#userDateAdded
	 **/
	public static volatile SingularAttribute<Users, LocalDate> userDateAdded;
	
	/**
	 * @see com.mc.icmc.domain.Users#userName
	 **/
	public static volatile SingularAttribute<Users, String> userName;
	
	/**
	 * @see com.mc.icmc.domain.Users#userCodeDateExp
	 **/
	public static volatile SingularAttribute<Users, LocalDateTime> userCodeDateExp;
	
	/**
	 * @see com.mc.icmc.domain.Users#userDateEdit
	 **/
	public static volatile SingularAttribute<Users, LocalDate> userDateEdit;
	
	/**
	 * @see com.mc.icmc.domain.Users#userKey
	 **/
	public static volatile SingularAttribute<Users, String> userKey;
	
	/**
	 * @see com.mc.icmc.domain.Users#userCode
	 **/
	public static volatile SingularAttribute<Users, String> userCode;
	
	/**
	 * @see com.mc.icmc.domain.Users#userCancel
	 **/
	public static volatile SingularAttribute<Users, Integer> userCancel;
	
	/**
	 * @see com.mc.icmc.domain.Users#userSortOrder
	 **/
	public static volatile SingularAttribute<Users, Integer> userSortOrder;
	
	/**
	 * @see com.mc.icmc.domain.Users#userParentId
	 **/
	public static volatile SingularAttribute<Users, Long> userParentId;
	
	/**
	 * @see com.mc.icmc.domain.Users#userLastName
	 **/
	public static volatile SingularAttribute<Users, String> userLastName;
	
	/**
	 * @see com.mc.icmc.domain.Users#userPublished
	 **/
	public static volatile SingularAttribute<Users, Integer> userPublished;
	
	/**
	 * @see com.mc.icmc.domain.Users#userFocus
	 **/
	public static volatile SingularAttribute<Users, Integer> userFocus;
	
	/**
	 * @see com.mc.icmc.domain.Users#userEmail
	 **/
	public static volatile SingularAttribute<Users, String> userEmail;
	
	/**
	 * @see com.mc.icmc.domain.Users#userLockUntil
	 **/
	public static volatile SingularAttribute<Users, LocalDateTime> userLockUntil;
	
	/**
	 * @see com.mc.icmc.domain.Users#id
	 **/
	public static volatile SingularAttribute<Users, Long> id;
	
	/**
	 * @see com.mc.icmc.domain.Users
	 **/
	public static volatile EntityType<Users> class_;
	
	/**
	 * @see com.mc.icmc.domain.Users#userFailedLoginAttempts
	 **/
	public static volatile SingularAttribute<Users, Integer> userFailedLoginAttempts;
	
	/**
	 * @see com.mc.icmc.domain.Users#userDateConnect
	 **/
	public static volatile SingularAttribute<Users, LocalDateTime> userDateConnect;

	public static final String USER_PASSWORD = "userPassword";
	public static final String ROLE = "role";
	public static final String USER_PHONE = "userPhone";
	public static final String USER_CONFIRMED_AT = "userConfirmedAt";
	public static final String USER_FIRST_NAME = "userFirstName";
	public static final String USER_DATE_ADDED = "userDateAdded";
	public static final String USER_NAME = "userName";
	public static final String USER_CODE_DATE_EXP = "userCodeDateExp";
	public static final String USER_DATE_EDIT = "userDateEdit";
	public static final String USER_KEY = "userKey";
	public static final String USER_CODE = "userCode";
	public static final String USER_CANCEL = "userCancel";
	public static final String USER_SORT_ORDER = "userSortOrder";
	public static final String USER_PARENT_ID = "userParentId";
	public static final String USER_LAST_NAME = "userLastName";
	public static final String USER_PUBLISHED = "userPublished";
	public static final String USER_FOCUS = "userFocus";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_LOCK_UNTIL = "userLockUntil";
	public static final String ID = "id";
	public static final String USER_FAILED_LOGIN_ATTEMPTS = "userFailedLoginAttempts";
	public static final String USER_DATE_CONNECT = "userDateConnect";

}

